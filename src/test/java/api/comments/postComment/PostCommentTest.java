package api.comments.postComment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import base.BaseApiTest;
import commentPOJO.Comment;

class PostCommentTest extends BaseApiTest{

	/**
	 * Task 110 – Create a New Comment Using a POJO

	Endpoint:
	
	POST /comments
	
	Request data:
	
	{
	  "postId": 1,
	  "name": "REST Assured practice comment",
	  "email": "practice@test.com",
	  "body": "This comment was created during API test practice."
	}
	
	Requirements:
	
	Create the request body using a Comment object.
	Send a POST request to /comments.
	Verify that the status code is 201.
	Verify that the response contains an ID.
	Verify that the returned postId, name, email, and body match the submitted values.
	Deserialize the response into a Comment object.
	Use a JUnit assertion to verify that the returned ID is greater than zero.
	Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 110 – Create a New Comment Using a POJO")
	void createANewCommentUsingAPOJOTest() {
		int postId = 1;
		String name = "REST Assured practice comment";
		String email = "practice@test.com";
		String body = "This comment was created during API test practice.";

		Comment requestComment = new Comment();
		requestComment.setPostId(postId);
		requestComment.setName(name);
		requestComment.setEmail(email);
		requestComment.setBody(body);

		Comment responseComment = given()
			            .body(requestComment)
			        .when()
			            .post("/comments")
			        .then()
			            .log().ifValidationFails()
			            .statusCode(201)
			            .body("$", hasKey("id"))
			            .body("postId", equalTo(postId))
			            .body("name", equalTo(name))
			            .body("email", equalTo(email))
			            .body("body", equalTo(body))
			            .extract()
			            .as(Comment.class);

		assertTrue(responseComment.getId() > 0);
	
	}

}
