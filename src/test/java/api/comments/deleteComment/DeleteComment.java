package api.comments.deleteComment;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import base.BaseApiTest;
import commentPOJO.Comment;

class DeleteComment extends BaseApiTest{

	/**
	 * Task 113 – Delete a Comment Found by Email

	Endpoints:
	
	GET /comments?email={email}
	DELETE /comments/{id}
	
	Test data:
	
	email = Eliseo@gardner.biz
	
	Requirements:
	
	Search for a comment using the specified email address.
 	Verify that the search request returns status code 200.
 	Verify that the search result is not empty.
 	Verify that the returned comment has the expected email address.
 	Extract the ID of the first returned comment.
 	Send a DELETE request using the extracted comment ID.
 	Verify that the delete request returns status code 200 or 204.
 	Verify that the delete response body is either an empty JSON object or empty.
 	Log the responses only if validation fails.
	 */
	@Test
	@DisplayName("Task 113 – Delete a Comment Found by Email")
	void deleteACommentFoundByEmailTest() {
		
		String email = "Eliseo@gardner.biz";
		
		Comment comment=given()
			.queryParam("email",email)
		.when()
			.get("comments")
		.then()
			.log().ifValidationFails()
			.statusCode(200)
			.body("$", allOf(not(empty()),notNullValue()))			
			.extract().jsonPath().getObject("[0]",Comment.class);
		
		assertEquals(email,comment.getEmail());
		
		given()
			.pathParam("id",comment.getId())
		.when()
			.delete("comments/{id}")
		.then()
			.log().ifValidationFails()
			.statusCode(anyOf(is(200),is(204)))
			.body("$", anyOf(empty(),nullValue(),anEmptyMap()));
	}
}
