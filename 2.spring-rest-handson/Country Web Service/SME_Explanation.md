# SME Explanation — Country Web Service

This document explains, in simple language, what happens behind the
scenes when you call `GET http://localhost:8083/country`.

---

## 1. What Happens Inside the Controller Method?

When you hit the URL `http://localhost:8083/country` in a browser or
Postman, the following sequence takes place:

1. **Request reaches the controller** — The embedded Tomcat server
   (started by Spring Boot) receives the HTTP GET request. Spring's
   `DispatcherServlet` inspects the request URL (`/country`) and, using
   its handler mapping, determines that it matches the
   `@RequestMapping("/country")` annotation on the `CountryController`
   class's `getCountryIndia()` method.

2. **Spring calls `getCountryIndia()`** — The `DispatcherServlet`
   invokes this method on the `CountryController` bean that Spring has
   already created and is managing inside its application context.

3. **Bean is fetched** — Inside the controller, the `country` field
   already holds a reference to the `Country` object. This object was
   *not* created manually with `new Country()`; instead, it was created
   by Spring when the application started up, based on the `<bean>`
   definition in `country.xml`. Spring injected this bean into the
   controller's constructor (constructor injection) when the
   controller itself was created.

4. **Bean is returned** — The method simply returns this `Country`
   object. Because the class is annotated with `@RestController`
   (rather than plain `@Controller`), Spring knows that the return
   value should be written directly into the HTTP response body,
   instead of being resolved as the name of a view/template.

---

## 2. How Does Spring Convert the Java Object into JSON?

1. **DispatcherServlet** — After the controller method returns the
   `Country` object, control passes back to the `DispatcherServlet`.
   Since the method's return type is not `void` and the controller is
   a `@RestController`, Spring MVC treats the return value as the
   response body (this is handled internally by an
   `HttpMessageConverter`).

2. **Jackson ObjectMapper** — Spring Boot's Web starter automatically
   includes the Jackson library on the classpath. Spring MVC registers
   a `MappingJackson2HttpMessageConverter`, which internally uses
   Jackson's `ObjectMapper` to serialize the `Country` object. Jackson
   reads the object's getter methods (`getCode()`, `getName()`) via
   reflection and produces the following JSON:

   ```json
   {
       "code": "IN",
       "name": "India"
   }
   ```

3. **HTTP Response** — The generated JSON string becomes the body of
   the HTTP response that is sent back to the client (browser or
   Postman).

4. **Content-Type: application/json** — Because Jackson was used to
   produce JSON output, Spring automatically sets the response header
   `Content-Type: application/json`, so the client knows how to
   interpret the body.

---

## 3. HTTP Headers Visible in Browser Developer Tools

If you open your browser's Developer Tools (F12) → **Network** tab,
click on the `country` request, and look at the **Response Headers**
section, you will typically see headers such as:

| Header | Meaning |
|---|---|
| `Content-Type: application/json` | Tells the browser the response body is JSON, so it can be parsed/displayed correctly. |
| `Content-Length` | The size (in bytes) of the response body. |
| `Date` | The date and time at which the server generated the response. |
| `Connection: keep-alive` | Indicates the underlying TCP connection can be reused for further requests, avoiding the overhead of opening a new connection each time. |
| `Keep-Alive` | Provides parameters (timeout, max requests) for how long the connection will be kept open. |

These headers are metadata sent alongside the JSON body; they don't
appear in the response body itself, but they control how the browser
handles and interprets the data it receives.

---

## 4. HTTP Headers in Postman

In Postman, after sending the `GET /country` request, click on the
**Headers** tab underneath the response panel to see the same kind of
metadata, typically including:

| Header | Meaning |
|---|---|
| `Content-Type: application/json` | Confirms the response body format is JSON, so Postman automatically applies JSON syntax highlighting. |
| `Content-Length` | Number of bytes in the response body — useful for verifying the payload size. |
| `Date` | Timestamp (in GMT) of when the server processed and sent the response. |
| `Connection` | Shows whether the connection between client and server stays open (`keep-alive`) or is closed after the response. |
| `Transfer-Encoding` (if present) | Indicates the response is sent in chunks rather than with a fixed `Content-Length`, which can happen for streamed responses. |

Understanding these headers helps you verify:
- That the server correctly identified your response as JSON.
- How large the payload is.
- Whether the connection is being reused efficiently.
- Exactly when the server processed the request.

---

### Summary

The `/country` endpoint demonstrates a full round trip: an HTTP
request is routed by `DispatcherServlet` to `CountryController`, the
controller returns a `Country` object that was originally created from
XML bean configuration, Jackson serializes that object into JSON, and
the response — complete with appropriate HTTP headers — is sent back
to the client for the browser or Postman to display.
