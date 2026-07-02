# Hello World RestApi

A simple Spring Boot RESTful web service that exposes a `GET /hello` endpoint returning the text `Hello World!!`.

## Features

- Single GET REST endpoint (`/hello`)
- Returns plain text response `Hello World!!`
- SLF4J logging with start and end log statements inside the controller method
- Runs on a custom port (`8083`)
- No database, no security, no templating engine — pure REST API

## Technologies Used

- Java 17
- Spring Boot 3.3.2
- Spring Web (Spring MVC)
- Maven
- SLF4J Logging
- JUnit 5 (Spring Boot Starter Test)

## Project Structure

```
Hello World RestApi/
│
├── pom.xml
├── README.md
├── .gitignore
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── cognizant
│   │   │           └── spring_learn
│   │   │               ├── SpringLearnApplication.java
│   │   │               └── controller
│   │   │                   └── HelloController.java
│   │   │
│   │   └── resources
│   │       └── application.properties
│   │
│   └── test
│       └── java
│           └── com
│               └── cognizant
│                   └── spring_learn
│                       └── SpringLearnApplicationTests.java
```

## How to Run

### Prerequisites

- JDK 17 or higher
- Maven 3.6+ (or use the included Maven Wrapper if present)

### Maven Commands

Build the project:

```bash
mvn clean install
```

Run the application:

```bash
mvn spring-boot:run
```

The application will start on:

```
http://localhost:8083
```

## Testing the Endpoint

### Using a Browser

Open the following URL in Chrome (or any browser):

```
http://localhost:8083/hello
```

Expected response body:

```
Hello World!!
```

**Viewing HTTP response headers in Chrome:**

1. Open Chrome DevTools (`F12` or `Ctrl+Shift+I` / `Cmd+Option+I`).
2. Go to the **Network** tab.
3. Reload the page (`http://localhost:8083/hello`).
4. Click on the `hello` request in the list.
5. Select the **Headers** sub-tab. Under **Response Headers**, you will see details such as:
   - `Content-Type: text/plain;charset=UTF-8`
   - `Content-Length`
   - `Date`
   - `Connection`
   - `Keep-Alive`

### Using Postman

1. Create a new request.
2. Set the method to `GET`.
3. Set the URL to:

   ```
   http://localhost:8083/hello
   ```

4. Click **Send**.
5. In the response panel, click the **Headers** tab (next to Body, Cookies, Test Results) to view the HTTP response headers such as `Content-Type`, `Content-Length`, `Date`, `Keep-Alive`, and `Connection`.

Expected response body:

```
Hello World!!
```

Expected status code:

```
200 OK
```

## Application Logs

When `/hello` is invoked, the console will log:

```
INFO  ... HelloController : Start sayHello()
INFO  ... HelloController : End sayHello()
```
