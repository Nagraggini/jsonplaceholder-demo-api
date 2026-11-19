package api.todos.patchTodo;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import base.BaseApiTest;
import io.restassured.response.Response;
import todoPOJO.Todo;

class PatchTodoTest extends BaseApiTest {

	/**
	 * Task 412 – Partially Update an Existing Todo
	Endpoint:
	
	PATCH /todos/{id}
	
	Test data:
	
	id = 15
	
	Request data:
	{
	  "completed": true
	}
	
	
	Requirements:
	- Create a request body containing only the completed field.
	- Send a PATCH request using the todo ID as a path parameter.
	- Verify that the status code is 200.
	- Verify that the response ID remains 15.
	- Deserialize the response into a Todo object.
	- Verify that the returned completed value is true.
	- Verify that the response contains the original userId.
	- Verify that the response contains the original title.
	- Use JUnit assertions to validate the ID and completion status.
	- Log the response only if validation fails.
	 */
	@Test
    @DisplayName("Task 412 – Partially Update an Existing Todo")
    void partiallyUpdateAnExistingTodoTest() {

        int id = 15;

        // 1. Retrieve the original Todo
        Todo todoOriginal = given()
                .pathParam("id", id)
            .when()
                .get("/todos/{id}")
            .then()
                .log().ifValidationFails()
                .statusCode(200)
                .extract()
                .as(Todo.class);

        // 2. Create request body containing only completed
        Map<String, Boolean> requestBody = Map.of(
                "completed", true
        );

        // 3. Send PATCH request
        Response response = given()
                .pathParam("id", id)
                .body(requestBody)
            .when()
                .patch("/todos/{id}");

        try {
            // 4. Verify status code
            assertEquals(200, response.statusCode());

            // 5. Deserialize response
            Todo todoResponse = response.as(Todo.class);

            // 6. Verify response
            assertAll(
                () -> assertEquals(id, todoResponse.getId()),
                () -> assertTrue(todoResponse.isCompleted()),
                () -> assertEquals(
                        todoOriginal.getUserId(),
                        todoResponse.getUserId()
                ),
                () -> assertEquals(
                        todoOriginal.getTitle(),
                        todoResponse.getTitle()
                )
            );

        } catch (AssertionError e) {
            // Log response only if validation fails
            response.then().log().all();
            throw e;
        }
    }
	
	/**
	 * Task 413 – Update the Title of an Existing Todo
	Endpoint:
	
	PATCH /todos/{id}
	
	Test data:
	
	id = 20
	
	Request data:
	{
	  "title": "Learn advanced REST Assured techniques"
	}
	
	
	Requirements:
	- Create a request body containing only the title field.
	- Send a PATCH request using the todo ID as a path parameter.
	- Verify that the status code is 200.
	- Verify that the response ID remains 20.
	- Deserialize the response into a Todo object.
	- Verify that the returned title matches the updated title.
	- Verify that the response contains the original user ID.
	- Verify that the response contains the original completion status.
	- Use JUnit assertions to validate the ID and title.
	- Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 413 – Update the Title of an Existing Todo")
	void updateTheTitleOfAnExistingTodoTest() {

        int id = 20;
        String title = "Learn advanced REST Assured techniques";

        // 1. Create request body
        Map<String, String> requestBody =
                Map.of("title", title);

        // 2. Retrieve original Todo
        Todo todoOriginal = given()
                .pathParam("id", id)
            .when()
                .get("/todos/{id}")
            .then()
                .log().ifValidationFails()
                .statusCode(200)
                .extract()
                .as(Todo.class);

        // 3. Send PATCH request
        Response response = given()
                .pathParam("id", id)
                .body(requestBody)
            .when()
                .patch("/todos/{id}");

        try {
            // 4. Verify status code
            assertEquals(200, response.statusCode());

            // 5. Deserialize response
            Todo todoResponse = response.as(Todo.class);

            // 6. Verify response
            assertAll(
                () -> assertEquals(id, todoResponse.getId()),
                () -> assertEquals(title, todoResponse.getTitle()),
                () -> assertEquals(
                        todoOriginal.getUserId(),
                        todoResponse.getUserId()
                ),
                () -> assertEquals(
                        todoOriginal.isCompleted(),
                        todoResponse.isCompleted()
                )
            );

        } catch (AssertionError e) {
            // Log response only if validation fails
            response.then().log().all();
            throw e;
        }
    }
}