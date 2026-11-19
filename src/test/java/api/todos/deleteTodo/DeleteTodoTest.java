package api.todos.deleteTodo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import base.BaseApiTest;
import todoPOJO.Todo;

class DeleteTodoTest extends BaseApiTest {

	/**
	 * Task 414 – Delete an Existing Todo
	Endpoint:
	
	DELETE /todos/{id}
	
	Test data:
	
	id = 25
	
	Requirements:
	- Send a DELETE request using the todo ID as a path parameter.
	- Verify that the status code is 200 or 204.
	- Verify that the response body is either:
		- an empty JSON object, or
		- completely empty.
	- Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 414 – Delete an Existing Todo")
	void deleteAnExistingTodoTest() {
		int id=25;
		
		given()
			.pathParam("id", id)
		.when()
			.delete("/todos/{id}")
		.then()
			.log().ifValidationFails()
			.statusCode(anyOf(is(200),is(204)))
			.body("$",anyOf(nullValue(), anEmptyMap(),equalTo("{}")));
	}
	
	/**
	 * Task 415 – Find and Delete a Todo by Title
	Search endpoint:
	
	GET /todos?title={title}
	
	Delete endpoint:
	
	DELETE /todos/{id}
	
	Test data:
	
	title = "delectus aut autem"
	
	Requirements:
	- Send a GET request using the title as a query parameter.
	- Verify that the GET response status code is 200.
	- Deserialize the response into a List<Todo>.
	- Verify that the list is not empty.
	- Verify that the returned todo title exactly matches the searched title.
	- Verify that the returned todo has a valid ID greater than 0.
	- Extract the ID of the first returned todo.
	- Send a DELETE request to /todos/{id} using the extracted ID.
	- Verify that the DELETE response status code is 200 or 204.
	- Verify that the DELETE response body is either an empty JSON object or empty.
	- Log responses only if validation fails.
	 */
	@Test
	@DisplayName("Task 415 – Find and Delete a Todo by Title")
	void findAndDeleteATodoByTitleTest() {
		String title="delectus aut autem";
		
		List<Todo> todos=given()
					.queryParam("title", title)
				.when()
					.get("/todos")
				.then()
					.log().ifValidationFails()
					.statusCode(200)
					.extract().jsonPath().getList("",Todo.class);
		
		assertNotNull(todos);
		assertFalse(todos.isEmpty());
		
		for (Todo todo : todos) {
			assertEquals(title, todo.getTitle());
			assertTrue(todo.getId()>0);
		}
		
		int id=todos.get(0).getId();
		
		given()
			.pathParam("id", id)
		.when()
			.delete("/todos/{id}")
		.then()
			.log().ifValidationFails()
			.statusCode(anyOf(is(200),is(204)))
			.body("$", anyOf(anEmptyMap(),nullValue()));
	}

}
