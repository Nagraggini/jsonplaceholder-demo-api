package api.todos.postTodo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import base.BaseApiTest;
import static io.restassured.RestAssured.given;
import todoPOJO.Todo;

class CreateTodoTest extends BaseApiTest {

	/**
	 * Task 410 – Create a New Todo
	 * Endpoint:
	 * 
	 * POST /todos
	 * 
	 * Request data:
	 * {
	 * "userId": 5,
	 * "title": "Complete REST Assured API testing tasks",
	 * "completed": false
	 * }
	 * 
	 * 
	 * Requirements:
	 * - Create a new Todo object using the request data.
	 * - Send a POST request with the complete todo data.
	 * - Verify that the status code is 201.
	 * - Verify that the response contains a generated ID.
	 * - Verify that the generated ID is greater than 0.
	 * - Deserialize the response into a Todo object.
	 * - Verify that all returned fields match the request data.
	 * - Use JUnit assertions to verify the generated ID, user ID, title, and
	 * completion status.
	 * - Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 410 – Create a New Todo")
	void createANewTodoTest() {

		int userId = 5;
		String title = "Complete REST Assured API testing tasks";
		boolean completed = false;

		Todo todo = new Todo();
		todo.setUserId(userId);
		todo.setTitle(title);
		todo.setCompleted(completed);

		Todo todoResponse = given()
				.body(todo)
				.when()
				.post("/todos")
				.then()
				.log().ifValidationFails()
				.statusCode(201)
				.extract().as(Todo.class);

		assertNotNull(todoResponse.getId());
		assertTrue(todoResponse.getId() > 0);

		assertEquals(userId, todoResponse.getUserId());
		assertEquals(title, todoResponse.getTitle());
		assertEquals(completed, todoResponse.isCompleted());

	}

}
