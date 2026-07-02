# REST-Get-country-based-on-country-code

A Spring Boot REST service that returns country details based on a country code. Matching is case insensitive, and country data is loaded from an XML file using Jackson XML.

## Technology Stack

- Java 17
- Spring Boot 3.3.4
- Maven (with Maven Wrapper)
- Spring Web
- Jackson Dataformat XML
- Lombok (optional)
- JUnit 5 / Mockito

## Folder Structure

```
REST-Get-country-based-on-country-code
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com/cognizant/spring_learn
│   │   │       ├── controller
│   │   │       │   └── CountryController.java
│   │   │       ├── service
│   │   │       │   └── CountryService.java
│   │   │       ├── model
│   │   │       │   ├── Country.java
│   │   │       │   └── CountryList.java
│   │   │       ├── util
│   │   │       │   └── XmlReaderUtil.java
│   │   │       └── SpringLearnApplication.java
│   │   └── resources
│   │       ├── country.xml
│   │       └── application.properties
│   └── test
│       └── java
│           └── com/cognizant/spring_learn
│               ├── controller
│               │   └── CountryControllerTest.java
│               └── service
│                   └── CountryServiceTest.java
│
├── pom.xml
├── mvnw
├── mvnw.cmd
├── README.md
└── .gitignore
```

## How to Run

### Prerequisites

- Java 17 installed
- No local Maven installation required (Maven Wrapper included)

### Steps

1. Extract the ZIP file.
2. Open a terminal in the project root directory.
3. Build the project:

   ```
   ./mvnw clean install
   ```

4. Run the application:

   ```
   ./mvnw spring-boot:run
   ```

5. The application starts on port `8083`.

On Windows, use `mvnw.cmd` instead of `./mvnw`.

## Maven Commands

| Command | Description |
|---|---|
| `./mvnw clean install` | Cleans and builds the project, running all tests |
| `./mvnw spring-boot:run` | Runs the Spring Boot application |
| `./mvnw test` | Runs the unit and web layer tests |

## API Endpoint

| Method | Endpoint | Description |
|---|---|---|
| GET | `/countries/{code}` | Returns the country matching the given code (case insensitive) |

## Sample Requests

```
GET http://localhost:8083/countries/in
GET http://localhost:8083/countries/IN
GET http://localhost:8083/countries/In
GET http://localhost:8083/countries/Us
```

## Sample Responses

Success (200 OK):

```json
{
    "code": "IN",
    "name": "India"
}
```

Not Found (404):

```json
{
    "timestamp": "2026-07-02T10:15:30.000+00:00",
    "status": 404,
    "error": "Not Found",
    "message": "Country not found",
    "path": "/countries/xyz"
}
```

## Available Country Codes

| Code | Name |
|---|---|
| IN | India |
| US | United States |
| GB | United Kingdom |
| AU | Australia |
| CA | Canada |

## Screenshots

_Add screenshots of the running application and sample API responses here._

## Future Improvements

- Add a global exception handler (`@RestControllerAdvice`) for consistent error responses.
- Move country data to a database instead of a static XML file.
- Add caching for country lookups.
- Add pagination and a "list all countries" endpoint.
- Add OpenAPI/Swagger documentation.
