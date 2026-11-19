package api.todos.putTodo;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import base.BaseApiTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import todoPOJO.Todo;

class ModifyTodoTest extends BaseApiTest{

	/**
	 * Task 411 – Replace an Existing Todo
	Endpoint:
	
	PUT /todos/{id}
	
	Test data:
	
	id = 10
	
	Request data:
	{
	  "userId": 4,
	  "id": 10,
	  "title": "The todo has been completely replaced",
	  "completed": true
	}
	
	
	Requirements:
	- Create a new Todo object using the request data.
	- Send a PUT request using the todo ID as a path parameter.
	- Send every todo field in the request body.
	- Verify that the status code is 200.
	- Verify that the response ID remains 10.
	- Deserialize the response into a Todo object.
	- Verify that every returned field matches the request data.
	- Use JUnit assertions to verify the ID, user ID, title, and completion status.
	- Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 411 – Replace an Existing Todo")
	 void replaceAnExistingTodoTest() {

        int userId = 4;
        int id = 10;
        String title = "The todo has been completely replaced";
        boolean completed = true;

        Todo newTodo = new Todo();
        newTodo.setUserId(userId);
        newTodo.setId(id);
        newTodo.setTitle(title);
        newTodo.setCompleted(completed);

        Response response = given()
                .pathParam("id", id)
                .contentType(ContentType.JSON) // BaseApiTest also included.
                .body(newTodo)
            .when()
                .put("/todos/{id}")
            .then()
                .log().ifValidationFails()
                .statusCode(200)
                .extract()
                .response();

        Todo todoResponse = response.as(Todo.class);

        try {
            assertEquals(id, todoResponse.getId(),
                    "Todo ID should remain unchanged");

            assertEquals(userId, todoResponse.getUserId(),
                    "User ID should match request data");

            assertEquals(title, todoResponse.getTitle(),
                    "Todo title should match request data");

            assertEquals(completed, todoResponse.isCompleted(),
                    "Completion status should match request data");

        } catch (AssertionError e) {
            response.then().log().all();
            throw e;
        }
    }
}
