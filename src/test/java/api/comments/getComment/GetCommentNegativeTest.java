package api.comments.getComment;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GetCommentNegativeTest {

	/**
	 * Task 209 – Search for a Nonexistent Comment

	Endpoint:
	
	GET /comments?email={email}
	
	Test data:
	
	email = not-existing@example.com
	
	Requirements:
	
	Search for comments using the nonexistent email address.
	Verify that the status code is 200.
	Verify that the response is a JSON array.
	Verify that the returned array is empty.
	Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 209 – Search for a Nonexistent Comment")
	void searchForANonexistentCommentTest() {
		
		String email="not-existing@example.com";
		
		given()
			.queryParam("email",email)		
		.when()
			.get("/comments")
		.then()
			.log().ifValidationFails()
			.statusCode(200)
			.body("$", instanceOf(List.class)) // The response is a JSON array.
			.body("$",anyOf(empty(),nullValue())); // Array is empty.
	}

}
