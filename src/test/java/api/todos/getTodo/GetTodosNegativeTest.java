package api.todos.getTodo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anEmptyMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import base.BaseApiTest;

class GetTodosNegativeTest extends BaseApiTest{

	/**
	 * Task 406 – Retrieve a Non-Existing Todo
	Endpoint:
	
	GET /todos/{id}
	
	Test data:
	
	id = 999
	
	Requirements:
	- Send a GET request using a non-existing todo ID as a path parameter.
	- Verify that the status code is 404.
	- Verify that the response body is an empty JSON object.
	- Verify that the response does not contain todo data.
	- Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 406 – Retrieve a Non-Existing Todo")
	void retrieveANonExistingTodoTest() {
		String id="999";
		
		given()
			.pathParam("id",id)
		.when()
			.get("/todos/{id}")
		.then()
			.log().ifValidationFails()
			.statusCode(404)
			.body("$",anEmptyMap());
		
	}

}
