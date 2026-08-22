package api.comments.commentList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.apache.logging.log4j.core.config.status.StatusConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import base.BaseApiTest;
import io.restassured.http.ContentType;

class CommentListTest extends BaseApiTest{

	/**
	 * Task 101 – Retrieve All Comments

	Endpoint:	
	GET /comments
	
	Requirements:	
	Send a GET request to /comments.
	Verify that the status code is 200.
	Verify that the response is a JSON array.
	Verify that the response is not null.
	Verify that the response is not empty.
	Verify that the response contains exactly 500 comments.
	Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 101 – Retrieve All Comments")
	void retrieveAllCommentsTest() {
		given()
			.accept(ContentType.JSON) // Also include BaseApiTest class.
		.when()
			.get("/comments")
		.then()
			.log().ifValidationFails()
			.statusCode(200)
			.body("$", instanceOf(List.class)) // !
			.body("$", notNullValue())
			.body("$", not(empty()))	// !
			.body("$.size()", equalTo(500));
	}
	
	/**
	 * Task 102 – Verify the Structure of Every Comment
	
	Endpoint:
	
	GET /comments
	
	Requirements:
	
	Send a GET request to /comments.
	Verify that the status code is 200.
	Verify that every comment contains all the following fields:
	postId
	id
	name
	email
	body
	Perform the field validation using one combined body assertion.
	Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 102 – Verify the Structure of Every Comment")
	void verifyTheStructureOfEveryCommentTest() {
		given()
		.when()
			.get("/comments")
		.then()
			.log().ifValidationFails()
			.statusCode(200)
			.body("$", everyItem(
					allOf( // !
						hasKey("postId")
						,hasKey("postId")
						,hasKey("id")
						,hasKey("name")
						,hasKey("email")
						,hasKey("body"))));
	}
	
	/**
	 * Task 103 – Verify That Required Fields Contain Valid Values

	Endpoint:
	
	GET /comments
	
	Requirements:
	
	Send a GET request to /comments.
	Verify that the status code is 200.
	Verify that every postId is greater than zero.
	Verify that every id is greater than zero.
	Verify that every name is neither null nor empty.
	Verify that every email is neither null nor empty.
	Verify that every body is neither null nor empty.
	Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 103 – Verify That Required Fields Contain Valid Values")
	void verifyThatRequiredFieldsContainValidValues() {
		given()
		.when()
			.get("/comments")
		.then()
			.log().ifValidationFails()
			.statusCode(200)
			.body("$.postId",everyItem(greaterThan(0)))
			.body("$.id",everyItem(greaterThan(0)))
			.body("$.name",everyItem(allOf(not(empty()),notNullValue())))
			.body("$.email",everyItem(allOf(not(empty()),notNullValue())))
			.body("$.body",everyItem(allOf(not(empty()),notNullValue())));		
	}		
}
