# Country Web Service

A simple Spring Boot REST service that returns India's country details.
The `Country` bean is defined using **Spring XML Bean Configuration**
(not annotations), demonstrating how legacy XML-based configuration can
be integrated into a modern Spring Boot application.

## Technologies Used

- Java 17
- Spring Boot 3.x
- Maven
- Spring Web
- XML Bean Configuration
- REST API

## Project Structure

```
Country Web Service
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── cognizant
│   │   │           └── spring_learn
│   │   │               ├── controller
│   │   │               │   └── CountryController.java
│   │   │               ├── model
│   │   │               │   └── Country.java
│   │   │               ├── config
│   │   │               │   └── AppConfig.java
│   │   │               └── SpringLearnApplication.java
│   │   │
│   │   └── resources
│   │       ├── application.properties
│   │       └── country.xml
│   │
│   └── test
│       └── java/com/cognizant/spring_learn/SpringLearnApplicationTests.java
│
├── pom.xml
├── README.md
├── SME_Explanation.md
└── .gitignore
```

### Package Explanation

- **controller** — Contains `CountryController`, the REST controller that
  exposes the `/country` endpoint and returns the `Country` object as JSON.
- **model** — Contains the `Country` POJO with `code` and `name` fields,
  along with constructors, getters, setters, and `toString()`.
- **config** — Contains `AppConfig`, a `@Configuration` class annotated
  with `@ImportResource("classpath:country.xml")` that loads the bean
  defined in the XML file into the Spring application context.
- **resources/country.xml** — Spring XML bean definition file that
  declares the `country` bean with `code=IN` and `name=India`.
- **SpringLearnApplication.java** — The main class that bootstraps the
  Spring Boot application.

## API Endpoint

**GET** `/country`

### Sample Request

```
http://localhost:8083/country
```

### Sample Response

```json
{
    "code": "IN",
    "name": "India"
}
```

## How to Run

1. Extract the ZIP file.
2. Open the extracted `Country Web Service` folder in IntelliJ IDEA
   (or Eclipse / VS Code).
3. Let the IDE reload/import the Maven project (Maven Reload) so all
   dependencies are downloaded.
4. Run the `SpringLearnApplication` class (contains the `main` method).
5. Open your web browser (or Postman).
6. Visit:

```
http://localhost:8083/country
```

You should see the JSON response shown above.

## Additional Documentation

See [`SME_Explanation.md`](./SME_Explanation.md) for a detailed,
beginner-friendly explanation of how the controller works, how the
`Country` bean is converted into JSON, and how to read HTTP response
headers in both browser Developer Tools and Postman.
