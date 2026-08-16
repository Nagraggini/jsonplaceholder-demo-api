package api.albums.postAlbum;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import albumsPOJO.Album;
import base.BaseApiTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

class PostAlbumTest extends BaseApiTest {

	/**
	 * Task 7 – Create a New Album

	Endpoint:
	
	POST https://jsonplaceholder.typicode.com/albums
	
	Request body:
	
	{
	  "userId": 4,
	  "title": "My Rest Assured Test Album"
	}
	
	Requirements:
	
	Send a POST request to /albums.
	Set the request content type to JSON.
	Add the provided data to the request body.
	Verify that the status code is 201.
	Verify that the response contains a non-null id.
	Verify that userId is 4.
	Verify that the title is "My Rest Assured Test Album".
	Extract the generated id from the response and print it.
	Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 7 – Create a New Album")
	void createANewAlbumTest() {
		
	Album album=new Album();
	
	album.setUserId(4);
	album.setTitle("My Rest Assured Test Album");
	
	Response responseObj=	given()
		.contentType(ContentType.JSON) // Also include BaseApiTest class.
		.body(album)
		.when().post("/albums")
		.then().log().ifValidationFails()
		.statusCode(201)
		.body("id",notNullValue())
		.body("userId", equalTo(4))
		.body("title",equalTo("My Rest Assured Test Album"))
		.extract().response();
	
	album=responseObj.as(Album.class);
	System.out.println("New id is: "+album.getId());
	}
}
