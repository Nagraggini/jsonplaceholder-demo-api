package api.comments.getComments;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import base.BaseApiTest;
import commentPOJO.Comment;


class GetCommentsTest extends BaseApiTest {

	/**
	 * Task 104 – Retrieve a Comment by ID

	Endpoint:
	
	GET /comments/{id}
	
	Test data:
	
	id = 1
	
	Requirements:
	
	Send a GET request using the comment ID as a path parameter.
	Verify that the status code is 200.
	Verify the following values:
	postId is 1
	id is 1
	name is "id labore ex et quam laborum"
	email is "Eliseo@gardner.biz"
	Verify that the body contains the word "laudantium".
	Deserialize the complete response into a Comment object.
	Use JUnit assertions to verify the ID and email stored in the object.
	Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 104 – Retrieve a Comment by ID")
	void retrieveACommentByIDTest() {
		int id=1;
		
		Comment comment=
		given()
			.pathParam("id",id)
		.when()
			.get("comments/{id}")
		.then()
			.log().ifValidationFails()
			.statusCode(200)
			.body("postId",equalTo(1))
			.body("id",equalTo(1))
			.body("name",equalTo("id labore ex et quam laborum"))
			.body("email",equalTo("Eliseo@gardner.biz"))
			.body("body",containsString("laudantium"))
			.extract().as(Comment.class);
		
		assertEquals(1, comment.getId());
		assertEquals("Eliseo@gardner.biz", comment.getEmail());
	}

}
