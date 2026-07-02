# spring-learn

## 1. Project Overview

`spring-learn` is a Spring Boot 3.5.x Maven project generated with the equivalent configuration of [Spring Initializr](https://start.spring.io/):

| Setting | Value |
|---|---|
| Group | `com.cognizant` |
| Artifact | `spring-learn` |
| Packaging | Jar |
| Java Version | 17 |
| Spring Boot Version | 3.5.16 |
| Dependencies | Spring Web, Spring Boot DevTools |

The project is a minimal, runnable Spring Boot application intended as a learning base. It boots a standard Spring application context, has an embedded Tomcat server available through Spring Web, and reloads automatically during development through DevTools. It includes a working JUnit test and is structured so it can be imported directly into Eclipse and built with Maven without further changes.

## 2. Folder Structure

```
spring-learn
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com/cognizant/springlearn/SpringLearnApplication.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── static
│   └── test
│       └── java
│           └── com/cognizant/springlearn/SpringLearnApplicationTests.java
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .gitignore
└── HELP.md
```

**`src/main/java`**
Contains all application source code. Package structure mirrors the reverse-domain group ID (`com.cognizant.springlearn`), and this is also where the entry-point class, `SpringLearnApplication`, lives. Any controllers, services, or repositories added later go under this tree.

**`src/main/resources`**
Holds non-Java resources used at runtime: `application.properties` for externalized configuration, and `static/` for any static web assets (HTML, CSS, JS, images) that should be served directly by the embedded server.

**`src/test/java`**
Mirrors the main package structure and holds all test code. `SpringLearnApplicationTests` lives here and verifies the Spring application context can start correctly.

## 3. SpringLearnApplication.java

```java
package com.cognizant.springlearn;

@SpringBootApplication
public class SpringLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringLearnApplication.class, args);
    }

}
```

**`main()` method**
This is the standard Java entry point and the first code executed when the JAR is run (`java -jar spring-learn.jar`) or when the class is run directly from an IDE. It delegates almost immediately to Spring Boot's bootstrap machinery, so application startup is effectively a single line.

**`SpringApplication.run()`**
This static method does the heavy lifting of bootstrapping the application:
- Creates and configures the Spring `ApplicationContext`.
- Registers all detected beans (through component scanning and auto-configuration).
- Starts the embedded servlet container (Tomcat, because Spring Web is on the classpath).
- Publishes application lifecycle events (`ApplicationStartingEvent`, `ApplicationReadyEvent`, etc.).

It takes the application's main class (used as a configuration source) and the raw command-line arguments, and returns the fully initialized `ApplicationContext`.

Log statements are placed immediately before and after this call so the console clearly shows when the application begins and finishes starting.

## 4. Explain @SpringBootApplication

`@SpringBootApplication` is a convenience meta-annotation that combines three annotations into one:

- **`@Configuration`** — Marks the class as a source of Spring bean definitions. It tells the container that this class can contain `@Bean` methods and other configuration.
- **`@EnableAutoConfiguration`** — Tells Spring Boot to automatically configure the application context based on the JARs present on the classpath. For example, because `spring-boot-starter-web` is present, Spring Boot automatically configures an embedded Tomcat server and Spring MVC's `DispatcherServlet`, with no manual XML or Java configuration required.
- **`@ComponentScan`** — Instructs Spring to scan the current package (`com.cognizant.springlearn`) and all sub-packages for other Spring-managed components (`@Component`, `@Service`, `@Repository`, `@Controller`, etc.) and register them as beans.

Placing `@SpringBootApplication` on the main class is what allows the entire application to be self-configuring from a single entry point.

## 5. pom.xml Walkthrough

**Parent**
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.5.16</version>
</parent>
```
The parent POM centralizes dependency version management (a Bill of Materials) so individual dependencies below do not need explicit version numbers — Spring Boot guarantees they are mutually compatible. It also configures sensible default plugin settings (compiler source/target, resource filtering, etc.).

**Dependencies**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```
- `spring-boot-starter-web` pulls in everything needed to build a web application: embedded Tomcat, Spring MVC, and Jackson for JSON.
- `spring-boot-devtools` provides fast application restarts, live reload, and sensible development-time defaults. It is `runtime`-scoped and `optional` so it is never packaged into a production artifact used by another module.
- `spring-boot-starter-test` brings in JUnit 5, Mockito, AssertJ, and Spring's test support, and is scoped to `test` so it is excluded from the runnable JAR.

**Plugins**
```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
</plugin>
```
This plugin repackages the JAR built by Maven into an executable "fat" JAR that bundles all dependencies, allowing the application to be started with `java -jar`. It also provides the `spring-boot:run` Maven goal.

**Java Version**
```xml
<properties>
    <java.version>17</java.version>
</properties>
```
This property is read by the parent POM to set the Maven compiler's source and target bytecode level to Java 17, which is the minimum version required by Spring Boot 3.x.

## 6. Dependency Hierarchy

A **Dependency Hierarchy** (or dependency tree) is the full, resolved set of libraries a project depends on — both the ones declared directly in `pom.xml` (direct/first-level dependencies) and the ones those libraries themselves depend on (transitive dependencies). Maven resolves this tree automatically, picks a single version for any library that appears more than once (dependency mediation), and downloads the resulting set of JARs.

You can inspect it in Eclipse from the POM editor's **Dependency Hierarchy** tab, or from the command line with:

```
mvn dependency:tree
```

The tree produced from the `spring-web` and `spring-boot-devtools` starters looks approximately like this (versions correspond to Spring Boot 3.5.16):

```
com.cognizant:spring-learn:jar:0.0.1-SNAPSHOT
├── org.springframework.boot:spring-boot-starter-web:jar
│   ├── org.springframework.boot:spring-boot-starter:jar
│   │   ├── org.springframework.boot:spring-boot:jar
│   │   ├── org.springframework.boot:spring-boot-autoconfigure:jar
│   │   ├── org.springframework.boot:spring-boot-starter-logging:jar
│   │   │   ├── ch.qos.logback:logback-classic:jar
│   │   │   ├── org.apache.logging.log4j:log4j-to-slf4j:jar
│   │   │   └── org.slf4j:jul-to-slf4j:jar
│   │   ├── jakarta.annotation:jakarta.annotation-api:jar
│   │   └── org.springframework:spring-core:jar
│   ├── org.springframework.boot:spring-boot-starter-json:jar
│   │   ├── com.fasterxml.jackson.core:jackson-databind:jar
│   │   ├── com.fasterxml.jackson.datatype:jackson-datatype-jdk8:jar
│   │   └── com.fasterxml.jackson.module:jackson-module-parameter-names:jar
│   ├── org.springframework.boot:spring-boot-starter-tomcat:jar
│   │   ├── org.apache.tomcat.embed:tomcat-embed-core:jar
│   │   └── org.apache.tomcat.embed:tomcat-embed-websocket:jar
│   ├── org.springframework:spring-web:jar
│   └── org.springframework:spring-webmvc:jar
│
├── org.springframework.boot:spring-boot-devtools:jar (optional, runtime)
│   ├── org.springframework.boot:spring-boot:jar
│   └── org.springframework.boot:spring-boot-autoconfigure:jar
│
└── org.springframework.boot:spring-boot-starter-test:jar (test)
    ├── org.springframework.boot:spring-boot-starter:jar
    ├── org.springframework.boot:spring-boot-test:jar
    ├── org.springframework.boot:spring-boot-test-autoconfigure:jar
    ├── org.junit.jupiter:junit-jupiter:jar
    ├── org.assertj:assertj-core:jar
    ├── org.mockito:mockito-core:jar
    └── org.springframework:spring-test:jar
```

The exact transitive versions are resolved by the `spring-boot-starter-parent` BOM at build time and may vary slightly with patch releases.

## 7. Eclipse Import Steps

1. Extract `spring-learn.zip` so the `spring-learn` folder sits inside (or is copied into) your Eclipse workspace directory.
2. Open Eclipse.
3. Go to **File** → **Import...**
4. Expand the **Maven** category and select **Existing Maven Projects**, then click **Next**.
5. Click **Browse**, navigate to the extracted `spring-learn` folder, and select it. Eclipse will detect `pom.xml` and list the project.
6. Ensure the `spring-learn` project checkbox is ticked.
7. Click **Finish**.

Eclipse (with the M2E plugin) will import the project, download dependencies according to `pom.xml`, and configure the Java build path automatically.

## 8. Build Command

```
mvn clean package \
-Dhttp.proxyHost=proxy.cognizant.com \
-Dhttp.proxyPort=6050 \
-Dhttps.proxyHost=proxy.cognizant.com \
-Dhttps.proxyPort=6050 \
-Dhttp.proxyUser=123456
```

Parameter breakdown:

| Parameter | Purpose |
|---|---|
| `mvn clean package` | Maven goals: `clean` deletes the `target/` directory from any previous build; `package` compiles the code, runs tests, and produces the final executable JAR in `target/`. |
| `-Dhttp.proxyHost=proxy.cognizant.com` | Sets the JVM system property for the HTTP proxy host, so Maven routes plain HTTP dependency downloads through the corporate proxy. |
| `-Dhttp.proxyPort=6050` | The port the HTTP proxy listens on. |
| `-Dhttps.proxyHost=proxy.cognizant.com` | Same as the HTTP proxy host setting, but applied to HTTPS traffic (most Maven Central traffic is HTTPS). |
| `-Dhttps.proxyPort=6050` | The port the HTTPS proxy listens on. |
| `-Dhttp.proxyUser=123456` | The username Maven should authenticate with against the proxy, if the proxy requires authentication. A corresponding `-Dhttp.proxyPassword` is typically also required in real network environments but is intentionally omitted here. |

These properties are only needed on networks that require an outbound proxy (such as a corporate network) to reach Maven Central; on an open network, plain `mvn clean package` is sufficient.

## 9. Running

**From Eclipse**
1. Right-click on `SpringLearnApplication.java` in the Package Explorer.
2. Select **Run As** → **Java Application**.

**From the command line**
```
./mvnw spring-boot:run
```
or, after building:
```
java -jar target/spring-learn-0.0.1-SNAPSHOT.jar
```

**Expected console output**

```
Starting SpringLearnApplication...

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.5.16)

... INFO ... Starting SpringLearnApplication using Java 17 ...
... INFO ... Tomcat initialized with port(s): 8080 (http)
... INFO ... Tomcat started on port(s): 8080 (http)
... INFO ... Started SpringLearnApplication in 1.234 seconds
SpringLearnApplication has started successfully.
```

The two `logger.info(...)` calls in `main()` — one immediately before and one immediately after `SpringApplication.run()` — bracket Spring Boot's own startup logs, making it easy to confirm from the console that `main()` executed and the application context finished starting.
