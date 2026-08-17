package api.albums.putAlbum;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import albumsPOJO.Album;
import base.BaseApiTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

class PutAlbumTest extends BaseApiTest {

	/**
	 * Task 9 – Update an Existing Album
	
	Endpoint:
	
	PUT https://jsonplaceholder.typicode.com/albums/25
	
	Request body:
	
	{
	  "userId": 3,
	  "id": 25,
	  "title": "Updated Rest Assured Album"
	}
	
	Requirements:
	
	Send a PUT request to /albums/25.
	Set the request content type to JSON.
	Add the provided album data to the request body.
	Verify that the status code is 200.
	Verify that the response contains userId with the value 3.
	Verify that the response contains id with the value 25.
	Verify that the returned title is "Updated Rest Assured Album".
	Extract the complete response into an Album object.
	Print the updated album’s ID and title.
	Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 9 – Update an Existing Album")
	void updateAnExistingAlbum() {
		Album album=new Album();
		
		album.setUserId(3);
		album.setId(25);
		album.setTitle("Updated Rest Assured Album");
		
		Response responseObj=given()
			.contentType(ContentType.JSON) // Also include BaseApiTest class.
			.body(album)
		.when()
			.put("/albums/25")
		.then()
			.log().ifValidationFails()
			.statusCode(200)
			.extract().response();
		
		album=responseObj.as(Album.class);
		assertEquals(3,album.getUserId());
		assertEquals(25,album.getId());
		assertEquals("Updated Rest Assured Album",album.getTitle());
		
		System.out.printf("Updated album’s ID: %s \nAnd title: %s",album.getUserId(),album.getTitle());
		
	}

}
