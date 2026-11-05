package api.todos.getTodo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

import base.BaseApiTest;
import io.restassured.response.Response;
import todoPOJO.Todo;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

class GetTodos extends BaseApiTest{

	/**
	 * Task 403 – Retrieve Two Todos by IDs
	 
	 Endpoint:
	 GET /todos
	 
	 Requirements:
	 
	 - Send a GET request using multiple ID query parameters (3 and 4).
	 - Verify that the response contains exactly two todos.
	 - Deserialize the response into a List<Todo>.
	 - Verify that the first todo has ID 3.
	 - Verify that the second todo has ID 4.
	 */
	@Test
	@DisplayName("Task 403 – Retrieve Two Todos by IDs")
	void retrieveTwoTodosByIDsTest() {
		Response response=given()	
			.queryParam("id",3,4)
		.when()
			.get("/todos")
		.then().extract().response();
		
		//System.out.println(response.getBody().asString());
		
		List<Todo> todos=new ArrayList<>();
		
		todos=response.jsonPath().getList("",Todo.class);
		
		assertEquals(3,todos.get(0).getId());
		assertEquals(4,todos.get(1).getId());
	}
	
	

	/**
	 * Task 404 – Validate Todo JSON Schema
	 
	 Endpoint:
	 GET /todos/1
	 
	 Requirements:
	 
	 - Send a GET request to retrieve the todo with ID 1.
	 - Verify that the status code is 200.
	 - Validate the response body against the JSON schema stored in
	   "schemas/todos-schema.json".
	 - Verify that the response structure and data types match the schema.
	 */
	@Test
	@DisplayName("Task 404 – Validate Todo JSON Schema")
	void validateTodoJSONSchemaTest() {

	    given()
	    .when()
	        .get("/todos/1")
	    .then()
	        .statusCode(200)
	        .body(matchesJsonSchemaInClasspath("schemas/todos-schema.json"));
	}
	
	/**
	 * Task 405 – Retrieve a Todo by ID
	Endpoint:
	
	GET /todos/{id}
	
	Test data:
	
	id = 5
	
	Expected values:
	{
	  "userId": 1,
	  "id": 5,
	  "title": "laboriosam mollitia et enim quasi adipisci quia provident illum",
	  "completed": false
	}
	
	
	Requirements:
	- Send a GET request using the todo ID as a path parameter.
	- Verify that the status code is 200.
	- Verify that the response body is not empty.
	- Deserialize the response into a Todo object.
	- Verify that the returned ID matches the requested ID.
	- Verify that the returned user ID is 1.
	- Verify that the returned title matches the expected title.
	- Verify that the returned completed value is false.
	- Use JUnit assertions to validate the ID, title, and completion status.
	- Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 405 – Retrieve a Todo by ID")
	void retrieveATodoByID() {
		int id = 5;
		
		Todo todo=given()
			.pathParam("id", id)
		.when()
			.get("/todos/{id}")
		.then()
			.log().ifValidationFails()
			.statusCode(200)		
			.extract().as(Todo.class);
		
			assertNotNull(todo);
		
			assertEquals(id,todo.getId());
			assertEquals(1,todo.getUserId());
			assertEquals("laboriosam mollitia et enim quasi adipisci quia provident illum"
					,todo.getTitle());			
			assertFalse(todo.isCompleted());			
	}
	
}
