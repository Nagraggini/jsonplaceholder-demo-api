[![Run Rest Assured API Tests](https://github.com/Nagraggini/jsonplaceholder-demo-api/actions/workflows/maven-tests.yml/badge.svg)](https://github.com/Nagraggini/jsonplaceholder-demo-api/actions/workflows/maven-tests.yml)

# JSONPlaceholder API Test Automation

Automated REST API test project built with **Java**, **REST Assured**, **JUnit 5**, and **Maven**.

The project uses the free [JSONPlaceholder](https://jsonplaceholder.typicode.com/) API to practise automated API testing, response validation, JSON processing, POJO serialization and deserialization, and positive and negative test scenarios.

## Technologies

- Java 21
- REST Assured 5.5.6
- JUnit 5
- Hamcrest Matchers
- Jackson Databind
- Maven
- Maven Surefire Plugin

## Test scenarios

The project contains or is being extended with the following album API scenarios:

- Get the complete album list
- Validate the response status code, structure, and number of albums
- Verify that every album contains the required fields
- Get and validate a specific album by ID
- Search for an album in the returned collection
- Filter albums by user ID
- Verify an empty result for a non-existing user ID
- Verify the response for a non-existing album
- Create a new album with a POST request
- Validate the created album and extract its generated ID
- Send a POST request to an invalid endpoint

## Project structure

```text
src/test/java
├── albumsPOJO
│   └── Album.java
├── api
│   └── albums
│       ├── getAlbum
│       └── postAlbum
└── base
    └── BaseApiTest.java
```

- `BaseApiTest` contains the common base URI and JSON request configuration.
- `Album` is the POJO used for JSON serialization and deserialization.
- The `api` packages contain the positive and negative API test classes.

## Example test

```java
@Test
@DisplayName("Get a non-existing album")
void getNonExistingAlbumTest() {
    given()
    .when()
        .get("/albums/999")
    .then()
        .log().ifValidationFails()
        .statusCode(404)
        .body("$", anEmptyMap());
}
```

## Running the tests

### Prerequisites

- Java 21 or newer
- Internet connection

The Maven Wrapper is included, so a separate Maven installation is not required.

### Linux and macOS

```bash
chmod +x mvnw
./mvnw clean test
```

### Windows (PowerShell)

```powershell
mvnw.cmd clean test
```

## API under test

Base URL:

```text
https://jsonplaceholder.typicode.com
```

Main resource used by the project:

```text
/albums
```

JSONPlaceholder is a fake API intended for learning and prototyping. Write operations such as POST do not permanently save the submitted data.

## Learning goals

This project demonstrates my practical experience with:

- arranging requests with `given–when–then` syntax;
- validating status codes and JSON response bodies;
- using Hamcrest matchers;
- sending query parameters and request bodies;
- handling positive and negative test cases;
- mapping JSON responses to Java objects;
- extracting values and complete responses for further validation;
- organizing reusable API test configuration.

## Author

Created by [Nagraggini](https://github.com/Nagraggini) as part of a software testing and Java learning portfolio.

## License

This project is available under the terms of the [MIT License](https://github.com/Nagraggini/jsonplaceholder-demo-api?tab=MIT-1-ov-file).
