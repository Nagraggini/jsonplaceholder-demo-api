package api.photos.deletePhoto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import base.BaseApiTest;
import io.restassured.response.Response;
import photoPOJO.Photo;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

class DeletePhotoTest extends BaseApiTest {
	
  /**
 	* Task 309 – Delete an Existing Photo
	
	Endpoint:
	
	DELETE /photos/{id}
	
	Test data:
	
	id = 250
	
	Requirements:
	
	Send a DELETE request using the photo ID as a path parameter.
	Verify that the status code is 200 or 204.
	Verify that the response body is either:
	an empty JSON object, or
	completely empty.
	Log the response only if validation fails.
	*/
	@Test
	@DisplayName("Task 309 – Delete an Existing Photo")
	void deleteAnExistingPhotoTest() {
		
		int id=250;
		
		given()	
			.pathParam("id",id)
		.when()
			.delete("/photos/{id}")
		.then()
			.log().ifValidationFails()
			.statusCode(anyOf(is(200),is(204)))
			.body("",anyOf(anEmptyMap(),nullValue(),empty()));
	}
	
	/**
	 * Task 310 – Find and Delete a Photo by Title
	
	Search endpoint:
	
	GET /photos?title={title}
	
	Delete endpoint:
	
	DELETE /photos/{id}
	
	Test data:
	
	title = "et inventore quae ut tempore eius voluptatum"
	
	Requirements:
	
	Send a GET request using the title as a query parameter.
	Verify that the GET response status code is 200.
	Deserialize the response into a List<Photo>.
	Verify that the list is not empty.
	Verify that the returned photo title exactly matches the searched title.
	Extract the ID of the first returned photo.
	Send a DELETE request to /photos/{id} using the extracted ID.
	Verify that the DELETE response status code is 200 or 204.
	Verify that the DELETE response body is either an empty JSON object or empty.
	Log responses only if validation fails.
	 */
	@Test
	@DisplayName("Task 310 – Find and Delete a Photo by Title")
	void findAndDeleteAPhotoByTitleTest() {
		
		String title = "et inventore quae ut tempore eius voluptatum";
		
		List <Photo> photoResponse=given()
			.queryParam("title",title)
		.when()
			.get("/photos")
		.then()
			.log().ifValidationFails()
			.statusCode(200)
			.extract().jsonPath().getList("",Photo.class);
		
		assertNotNull(photoResponse);
		assertFalse(photoResponse.isEmpty());
		
		for (Photo photo : photoResponse) {
			assertEquals(title,photo.getTitle());
		}
		
		int id=photoResponse.get(0).getId();
		System.out.println("Photo ID to delete: " + id);
		
		Response responseAfterDelete=given()
			.pathParam("id",id)
		.when()
			.delete("/photos/{id}")
		.then()
			.log().ifValidationFails()		    
			.statusCode(anyOf(is(200),is(204)))
			.extract().response();
		
		String responseBody=responseAfterDelete.getBody().asString();
		
		assertTrue(responseBody.trim().isEmpty() || responseBody.trim().equals("{}"));
			
	}

}
