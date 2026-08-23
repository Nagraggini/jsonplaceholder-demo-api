package api.comments.patchComment;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import base.BaseApiTest;

class PatchCommentTest extends BaseApiTest {

	/**
	 * Task 112 – Partially Update an Existing Comment

	Endpoint:
	
	PATCH /comments/{id}
	
	Test data:
	
	id = 10
	
	Request data:
	
	{
	  "email": "patched@test.com"
	}
	
	Requirements:
	
	Send a PATCH request containing only the new email address.
	Verify that the status code is 200.
	Verify that the returned ID is still 10.
	Verify that the returned email is "patched@test.com".
	Verify that the original postId, name, and body fields are still present.
	Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 112 – Partially Update an Existing Comment")
	void partiallyUpdateAnExistingCommentTest() {
		int id=10;
		String email="patched@test.com";
		
		String commentRequest="""
						
			{
			  "email": "patched@test.com"
			}
				""";
		
		given()
			.body(commentRequest)
			.pathParam("id",id)
		.when()
			.patch("comments/{id}")
		.then()
			.log().ifValidationFails()
			.statusCode(200)
		    .body("id", equalTo(id))
			.body("email",equalTo(email))
			.body("postId", notNullValue())
			.body("name", notNullValue())
			.body("body", notNullValue());
	}

}
