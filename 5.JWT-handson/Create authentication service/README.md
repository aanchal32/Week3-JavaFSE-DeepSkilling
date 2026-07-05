# Create authentication service

A REST authentication service built with Spring Boot that authenticates a user via HTTP Basic Authentication and returns a signed JWT.

## Project Overview

The client sends a username and password using HTTP Basic Authentication to the `/authenticate` endpoint. The service decodes the `Authorization` header, validates the credentials, and — if valid — issues a JSON Web Token (JWT) signed with HS256. If the credentials are invalid, the service responds with `401 Unauthorized`.

## Technologies Used

- Java 17
- Spring Boot 3.3.4
- Spring Web
- Spring Security
- jjwt (JSON Web Token library) 0.12.6
- Lombok
- Maven / Maven Wrapper

## Project Structure

```
Create authentication service/
├── src/
│   ├── main/
│   │   ├── java/com/example/auth/
│   │   │   ├── AuthServiceApplication.java
│   │   │   ├── controller/
│   │   │   │   └── AuthenticationController.java
│   │   │   ├── config/
│   │   │   │   └── SecurityConfig.java
│   │   │   ├── security/
│   │   │   ├── service/
│   │   │   │   └── AuthenticationService.java
│   │   │   ├── util/
│   │   │   │   └── JwtUtil.java
│   │   │   ├── model/
│   │   │   │   ├── TokenResponse.java
│   │   │   │   └── ErrorResponse.java
│   │   │   └── exception/
│   │   │       ├── InvalidCredentialsException.java
│   │   │       └── GlobalExceptionHandler.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/example/auth/AuthServiceApplicationTests.java
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .gitignore
├── postman_collection.json
└── README.md
```

## How to Run

```bash
./mvnw spring-boot:run
```

The service starts on port `8090`.

## How to Build

```bash
./mvnw clean install
```

This produces an executable jar at `target/create-authentication-service.jar`, which can be run with:

```bash
java -jar target/create-authentication-service.jar
```

## Example curl Request

```bash
curl -s -u user:pwd http://localhost:8090/authenticate
```

## Example Response

```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNTcwMzc5NDc0LCJleHAiOjE1NzAzODA2NzR9.t3LRvlCV-hwKfoqZYlaVQqEUiBloWcWn0ft3tgv0dL0"
}
```

On invalid credentials:

```json
{
    "error": "Invalid username or password"
}
```

## Test Credentials

| Username | Password |
|----------|----------|
| user     | pwd      |

## Maven Commands

| Command | Description |
|---|---|
| `./mvnw clean install` | Build the project and run tests |
| `./mvnw spring-boot:run` | Run the application |
| `./mvnw test` | Run tests only |
| `./mvnw clean package -DskipTests` | Build a jar without running tests |

## GitHub Upload Instructions

```bash
cd "Create authentication service"
git init
git add .
git commit -m "Initial commit: JWT authentication service"
git branch -M main
git remote add origin <your-repository-url>
git push -u origin main
```

## Postman Collection

Import `postman_collection.json` into Postman. It contains a single `GET /authenticate` request pre-configured with Basic Auth (`user` / `pwd`).
