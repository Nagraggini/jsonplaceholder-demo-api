package api.todos.todoList;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import base.BaseApiTest;
import io.restassured.response.Response;
import todoPOJO.*;

class TodoListTest extends BaseApiTest {

	/**
	 * Task 401 – Retrieve All Todos
	Endpoint:
	
	GET /todos
	
	Requirements:
	- Send a GET request to retrieve all todos.
	- Verify that the status code is 200.
	- Verify that the response body is not empty.
	- Verify that the response contains exactly 200 todos.
	- Deserialize the response into a List<Todo>.
	- Verify that the list is not null.
	- Verify that the first todo has a valid ID greater than 0.
	- Verify that every todo has a non-null title.
	- Verify that every todo has a valid user ID greater than 0.
	- Verify that the completed field contains a boolean value.
	- Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 401 – Retrieve All Todos")
	void retrieveAllTodos() {
		List<Todo> todos=new ArrayList<>();
		
		todos=given()
		.when()
			.get("/todos")
		.then()
			.log().ifValidationFails()
			.statusCode(200)
			.body("",not(empty()))			
			.body("size()",equalTo(200))
			.extract().jsonPath().getList("",Todo.class);
		
		assertNotNull(todos);
		assertTrue(todos.get(0).getId()>0);	
		
		for (Todo todo : todos) {
			assertNotNull(todo.getTitle());
			assertTrue(todo.getUserId()>0);
			assertNotNull(todo.isCompleted());
		}
	}

	/**
	 * Task 402 – Search Todos by Title
	 
	 Endpoint:
	 GET /todos
	 
	 Requirements:

	 - Send a GET request to retrieve all todos.
	 - Verify that the response contains at least one todo.
	 - Verify that at least one todo has a title containing "delectus aut autem".
	 */
	@Test
	@DisplayName("Task 402 – Search Todos by Title")
	void searchTodosByTitleTest() {
		Response response=given()			
		.when()
			.get("/todos")
		.then()
			//Is there at least one item anywhere in the list that meets this condition?
			.body("title",hasItem( containsString("delectus aut autem")))
			.extract().response();		
	}
}
