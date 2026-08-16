package api.albums.postAlbum;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import albumsPOJO.Album;
import base.BaseApiTest;
import io.restassured.http.ContentType;


class PostAlbumNegativeTest extends BaseApiTest {

	/**
	 * Task 8 – Create an Album Using an Invalid Endpoint
	
	Endpoint:
	
	POST https://jsonplaceholder.typicode.com/invalid-albums
	
	Request body:
	
	{
	  "userId": 4,
	  "title": "Invalid Endpoint Test"
	}
	
	Requirements:
	
	Send a POST request to /invalid-albums.
	Set the request content type to JSON.
	Add the provided data to the request body.
	Verify that the status code is 404.
	Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 8 – Create an Album Using an Invalid Endpoint")
	void createAnAlbumUsingAnInvalidEndpoint() {
		
		Album album=new Album();
		album.setUserId(4);
		album.setTitle("Invalid Endpoint Test");
		
		given()
			.contentType(ContentType.JSON) // Also include BaseApiTest class.
			.body(album)
		.when()
			.post("/invalid-albums")
		.then()
			.log().ifValidationFails()
			.statusCode(404);
	}
}
