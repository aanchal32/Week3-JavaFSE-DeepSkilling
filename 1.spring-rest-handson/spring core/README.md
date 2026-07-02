# spring-learn

## Objective

This project demonstrates a core Spring Framework concept: **externalizing bean
creation into XML configuration** instead of instantiating objects directly in
application code.

Specifically, it defines a `SimpleDateFormat` bean (pattern `dd/MM/yyyy`) in a
Spring XML configuration file (`date-format.xml`), loads it through a
`ClassPathXmlApplicationContext`, retrieves it by bean name, and uses it to
parse a sample date string.

## Technologies Used

| Technology         | Version |
|---------------------|---------|
| Java                | 17      |
| Maven               | 3.9+    |
| Spring Context       | 6.1.13  |
| JUnit 5             | 5.10.3  |

## Project Structure

```
spring-learn
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com/example/springlearn
│   │   │       └── SpringLearnApplication.java
│   │   │
│   │   └── resources
│   │       ├── application.properties
│   │       └── date-format.xml
│   │
│   └── test
│       └── java
│           └── com/example/springlearn
│               └── SpringLearnApplicationTest.java
│
├── pom.xml
├── README.md
└── .gitignore
```

## How to Run

### 1. Build the project

```bash
mvn clean install
```

### 2. Run the application

Option A — using the Exec plugin:

```bash
mvn exec:java
```

Option B — using the compiled classes directly:

```bash
mvn dependency:build-classpath -Dmdep.outputFile=cp.txt
java -cp "target/classes:$(cat cp.txt)" com.example.springlearn.SpringLearnApplication
```

Option C — run `SpringLearnApplication.java` directly from IntelliJ IDEA,
Eclipse, or Spring Tool Suite as a **Java Application**.

### 3. Run the tests

```bash
mvn test
```

## Expected Output

Running the application executes `displayDate()`, which loads the
`dateFormat` bean from `date-format.xml`, parses the string `31/12/2018`,
and prints the result to the console:

```
Mon Dec 31 00:00:00 UTC 2018
```

*(The exact day-of-week and timezone portion of the output depends on the
JVM's default locale and timezone.)*

## How It Works

1. **`date-format.xml`** defines a single bean, `dateFormat`, of type
   `java.text.SimpleDateFormat`, constructed with the pattern `dd/MM/yyyy`.
2. **`SpringLearnApplication.displayDate()`** creates a
   `ClassPathXmlApplicationContext` pointed at `date-format.xml`.
3. The `dateFormat` bean is retrieved from the context via
   `context.getBean("dateFormat", SimpleDateFormat.class)`.
4. The bean parses `"31/12/2018"` into a `java.util.Date`, which is then
   printed to the console.

This pattern avoids creating a new `SimpleDateFormat` instance every time one
is needed elsewhere in the application — the same shared, centrally
configured instance is reused.

## Screenshots

_Add screenshots of the console output or IDE run configuration here._

```
[ Screenshot placeholder — insert image after running the project locally ]
```

## License

This project is provided as a learning exercise and is free to use and
modify.
