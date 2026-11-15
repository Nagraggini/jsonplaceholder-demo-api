package api.todos.todoList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import base.BaseApiTest;
import io.restassured.response.Response;
import todoPOJO.Todo;

class TodoListTest extends BaseApiTest {
	
	private static final Logger LOG=LogManager.getLogger(TodoListTest.class);

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
	
	/**
	 * Task 407 – Filter Todos by User ID
	Endpoint:
	
	GET /todos?userId={userId}
	
	Test data:
	
	userId = 4
	
	Requirements:
	- Send a GET request using userId as a query parameter.
	- Verify that the status code is 200.
	- Verify that the response body is not empty.
	- Deserialize the response into a List<Todo>.
	- Verify that the list is not empty.
	- Verify that every returned todo has userId = 4.
	- Verify that every todo has a valid ID greater than 0.
	- Verify that every todo has a non-null title.
	- Verify that the response contains only todos belonging to the requested user.
	- Log the response only if validation fails.
	 */	
	@Test
	@DisplayName("Task 407 – Filter Todos by User ID")
	void filterTodosByUserId() {
	
	    int userId = 4;
	
	    Response response = given()
	            .queryParam("userId", userId)
	        .when()
	            .get("/todos")
	        .then()
	            .log().ifValidationFails()
	            .statusCode(200)
	            .body("$", not(empty()))
	            .extract()
	            .response();
	
	    List<Todo> todos = response.as(
	            new io.restassured.common.mapper.TypeRef<List<Todo>>() {}
	    );
	
	    try {
	        assertNotNull(todos, "Todo list should not be null");
	        assertFalse(todos.isEmpty(), "Todo list should not be empty");
	
	        for (Todo todo : todos) {
	            assertEquals(userId, todo.getUserId(),
	                    "Todo should belong to the requested user");
	
	            assertTrue(todo.getId() > 0,
	                    "Todo ID should be greater than 0");
	
	            assertNotNull(todo.getTitle(),
	                    "Todo title should not be null");
	        }
	
	    } catch (AssertionError e) {
	        response.then().log().all();
	        throw e;
	    }
	}
	
	/**
	 * Task 408 – Filter Todos by Completion Status
	Endpoint:
	
	GET /todos?completed={completed}
	
	Test data:
	
	completed = true
	
	Requirements:
	- Send a GET request using completed as a query parameter.
	- Verify that the status code is 200.
	- Verify that the response body is not empty.
	- Deserialize the response into a List<Todo>.
	- Verify that the list is not empty.
	- Verify that every returned todo has completed = true.
	- Verify that every todo has a valid ID greater than 0.
	- Verify that every todo has a non-null title.
	- Verify that no incomplete todos are returned.
	- Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 408 – Filter Todos by Completion Status")
	void filterTodosByCompletionStatus() {
		boolean completed = true;
		
		List<Todo> todos=given()
			.queryParam("completed",completed)
		.when()
			.get("/todos")
		.then()
			.log().ifValidationFails()
			.statusCode(200)
			.extract().jsonPath().getList("",Todo.class);
		
		assertNotNull(todos);
		assertFalse(todos.isEmpty());
		
		for (Todo todo : todos) {
		    assertTrue(todo.isCompleted(),
		            "Todo should be completed");

		    assertTrue(todo.getId() > 0,
		            "Todo ID should be greater than 0");

		    assertNotNull(todo.getTitle(),
		            "Todo title should not be null");
		}
	}

	
	/**
	 * Task 409 – Filter Todos by User ID and Completion Status
	Endpoint:
	
	GET /todos?userId={userId}&completed={completed}
	
	Test data:
	- userId = 2
	- completed = true
	Requirements:
	- Send a GET request using both userId and completed as query parameters.
	- Verify that the status code is 200.
	- Verify that the response body is not empty.
	- Deserialize the response into a List<Todo>.
	- Verify that the list is not empty.
	- Verify that every returned todo has userId = 2.
	- Verify that every returned todo has completed = true.
	- Verify that every todo has a valid ID greater than 0.
	- Verify that every todo has a non-null title.
	- Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 409 – Filter Todos by User ID and Completion Status")
	void filterTodosByUserIDAndCompletionStatus() {
		
		int userId = 2;
		boolean completed = true;
		
		LOG.info("Task 409 – Filter Todos by User ID and Completion Status");
		
		List <Todo>todos=given()
				.queryParam("userId", userId)
				.queryParam("completed", completed)
			.when()
				.get("/todos")
			.then()
				.log().ifValidationFails()
				.statusCode(200)
				.body("$",not(empty()))
				.extract().jsonPath().getList("",Todo.class);
		
		assertNotNull(todos);
		assertFalse(todos.isEmpty());
		
		for (Todo todo : todos) {
			
			LOG.info("Validating Todo ID: {}", todo.getId());
			
			assertEquals(userId,todo.getUserId());
			assertTrue(todo.isCompleted());
			assertTrue(todo.getId()>0);
			assertNotNull(todo.getTitle());
		}		
	}
	
}
