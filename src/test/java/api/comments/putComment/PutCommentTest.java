package api.comments.putComment;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import base.BaseApiTest;
import commentPOJO.Comment;

class PutComment extends BaseApiTest {

	/**
	 * Task 111 – Replace an Existing Comment

	Endpoint:
	
	PUT /comments/{id}
	
	Test data:
	
	id = 10
	
	Request data:
	
	{
	  "postId": 2,
	  "name": "Updated comment name",
	  "email": "updated@test.com",
	  "body": "The complete comment has been replaced."
	}
	
	Requirements:
	
	Send a PUT request using the comment ID in the endpoint.
	Send all comment fields in the request body.
	Verify that the status code is 200.
	Verify that the returned ID is still 10.
	Verify that every returned field matches the submitted data.
	Deserialize the response into a Comment object.
	Verify the values stored in the object.
	Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 111 – Replace an Existing Comment")
	void replaceAnExistingCommentTest() {
		
	    int postId = 2;
	    int id=10;
	    String name = "Updated comment name";
	    String email = "updated@test.com";
	    String body = "The complete comment has been replaced.";

	    Comment requestComment = new Comment();
	    requestComment.setPostId(postId);
	    requestComment.setId(id);
	    requestComment.setName(name);
	    requestComment.setEmail(email);
	    requestComment.setBody(body);
		
		Comment comment=given()
	        .body(requestComment)
	        .pathParam("id", id)
	    .when()
	         .put("comments/{id}")
	    .then() 
	         .log().ifValidationFails()
	         .statusCode(200)
	         .body("postId", equalTo(postId))
	         .body("id", equalTo(id))
	         .body("name", equalTo(name))
	         .body("email", equalTo(email))
	         .body("body", equalTo(body))
	         .extract().as(Comment.class) ;

		assertEquals(postId,comment.getPostId());
		assertEquals(id,comment.getId());
		assertEquals(name,comment.getName());
		assertEquals(email,comment.getEmail());
		assertEquals(body,comment.getBody());
	}

}
