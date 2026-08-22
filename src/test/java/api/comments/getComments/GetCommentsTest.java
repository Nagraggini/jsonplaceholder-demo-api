package api.comments.getComments;


import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import base.BaseApiTest;
import commentPOJO.Comment;
import io.restassured.http.ContentType;


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
	
	/**
	 * Task 105 – Filter Comments by Post ID
	
	Endpoint:
	
	GET /comments?postId={postId}
	
	Test data:
	
	postId = 5
	
	Requirements:
	
	Send a GET request using postId as a query parameter.
	Verify that the status code is 200.
	Verify that the response contains exactly five comments.
	Verify that every returned comment has a postId of 5.
	Verify that the returned comment IDs are exactly:
	21
	22
	23
	24
	25
	Verify the IDs in the expected order.
	Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 105 – Filter Comments by Post ID")
	void filterCommentsByPostIDTest() {
		int postId=5;
		
		List<Comment> comments=given()
			.queryParam("postId", postId)
		.when()
			.get("/comments")
		.then()
			.log().ifValidationFails()
			.statusCode(200)
			.extract().jsonPath().getList("",Comment.class);
		
		assertEquals(5,comments.size());
		
		for (Comment comment : comments) {
			assertEquals(postId,comment.getPostId(),"Ennél nem egyezik a postId száma:"+comment.getId());
		}		
	
		assertEquals(21,comments.get(0).getId());
		assertEquals(22,comments.get(1).getId());
		assertEquals(23,comments.get(2).getId());
		assertEquals(24,comments.get(3).getId());
		assertEquals(25,comments.get(4).getId());		
	}
	
	/**
	 * Task 106 – Compare Filtered and Nested Comment Routes

	Endpoints:
	
	GET /comments?postId=10
	GET /posts/10/comments
	
	Requirements:
	
	Send a GET request to both endpoints using the specified post ID.
	Verify that both requests return status code 200.
	Deserialize both responses into lists of Comment objects.
	Verify that both responses contain exactly five comments.
	Verify that every comment returned by the filtered endpoint has a postId of 10.
	Verify that every comment returned by the nested endpoint has a postId of 10.
	Compare the comment IDs at the same list positions.
	Verify that the comment IDs are equal and appear in the same order in both responses.
	Log responses only if validation fails.
	 */
	@Test
	@DisplayName("Task 106 – Compare Filtered and Nested Comment Routes")
	void compareFilteredAndNestedCommentRoutesTest() {
		
		int postId=10;
		
		List<Comment> comments1=given()
					.pathParam("postId", postId)
				.when()
					.get("/comments?postId={postId}")
				.then()
					.log().ifValidationFails()
					.statusCode(200)
					.body("$.postId",everyItem(equalTo(postId)))
					.extract().jsonPath().getList("",Comment.class);
		
		assertEquals(5,comments1.size());
		
		List<Comment> comments2=given()
					.pathParam("postId", postId)
				.when()
					.get("/posts/{postId}/comments")
				.then()
					.log().ifValidationFails()
					.statusCode(200)
					.extract().jsonPath().getList("",Comment.class);
			
		for (Comment comment : comments2) {
			assertEquals(postId,comment.getPostId());
		}	
		
		assertEquals(5,comments2.size());
		
		for (int i=0;i<comments1.size();i++) {
			assertEquals(comments1.get(i).getId(),comments2.get(i).getId());						
		}		
	}

	/**
	 * Task 107 – Search for a Comment by Email

	Endpoint:
	
	GET /comments?email={email}
	
	Test data:
	
	email = Eliseo@gardner.biz
	
	Requirements:
	
	Store the email address in a separate variable.
	Search for comments using the email address as a query parameter.
	Verify that the status code is 200.
	Verify that the response is not empty.
	Verify that the response contains the specified email address.
	Extract the first result into a Comment object.
	Use a JUnit assertion to verify the email stored in the extracted object.
	Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 107 – Search for a Comment by Email")
	void searchForACommentByEmailTest() {
		
		String email="Eliseo@gardner.biz";
		
		Comment comment=given()
			.queryParam("email",email)
		.when()
			.get("/comments")
		.then()
			.log().ifValidationFails()
			.statusCode(200)
			.body("$", allOf(notNullValue(),not(empty())))
			.body("email", hasItem(email))
			.extract().jsonPath().getObject("[0]",Comment.class);
		
		assertEquals(email,comment.getEmail());
	}
	
	/**
	 * Task 108 – Search for a Nonexistent Comment

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
	@DisplayName("Task 108 – Search for a Nonexistent Comment")
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
	
	/**
	 * Task 109 – Deserialize a List of Comments

	Endpoint:
	
	GET /comments?postId=20
	
	Requirements:
	
	Send a GET request using postId = 20.
	Verify that the status code is 200.
	Deserialize the complete response into a List<Comment>.
	Use JUnit assertions to verify that:
	the list is not null;
	the list is not empty;
	the list contains exactly five elements;
	the first comment has ID 96;
	the last comment has ID 100;
	every comment has a postId of 20.
	Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 109 – Deserialize a List of Comments")
	void deserializeAListOfCommentsTest() {
		
		int postId=20;
		
		List <Comment> comments=given()
			.queryParam("postId", postId)
		.when()
			.get("/comments")
		.then()
			.log().ifValidationFails()
			.statusCode(200)
			.extract().jsonPath().getList("",Comment.class);
		
		assertNotNull(comments);
		assertFalse(comments.isEmpty());
		assertEquals(5,comments.size());
		assertEquals(96,comments.get(0).getId());
		assertEquals(100,comments.get(comments.size()-1).getId());
		
		for (Comment comment : comments) {
			assertEquals(postId,comment.getPostId());
		}
	}
	
	
	
	
}
