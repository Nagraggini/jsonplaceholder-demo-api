package api.photos.postPhoto;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import base.BaseApiTest;
import io.restassured.response.Response;
import photoPOJO.Photo;

class PostPhotoTest extends BaseApiTest {

	/**
	 * Task 307 – Create a New Photo
	 
	 Endpoint:
	 
	 POST /photos
	 
	 Request data:
	 
	 { "albumId": 10, "title": "REST Assured test photo", "url":
	  "https://example.com/images/test-photo.jpg", "thumbnailUrl":
	  "https://example.com/images/test-thumbnail.jpg" }
	  
	 Requirements:
	  
	 Send a POST request with the complete photo data. Verify that the status code
	 is 201. Verify that the response contains a generated id. Verify that the
	 generated ID is greater than 0. Verify that all returned fields match the
	 request data. Deserialize the response into a Photo object. Use JUnit
	 assertions to verify the returned title and album ID. Log the response only
	 if validation fails.
	 */
	@Test
	@DisplayName("Task 307 – Create a New Photo")
	void createANewPhotoTest() {

		int albumId = 10;
		String title = "REST Assured test photo";
		String url = "https://example.com/images/test-photo.jpg";
		String thumbnailUrl = "https://example.com/images/test-thumbnail.jpg";

		Photo photo = new Photo();
		photo.setAlbumId(albumId);
		photo.setTitle(title);
		photo.setUrl(url);
		photo.setThumbnailUrl(thumbnailUrl);

		Photo responsePhoto = 
				given()
					.body(photo)
				.when()
					.post("/photos")
				.then()
					.log().ifValidationFails()
					.statusCode(201)
					.extract().response().as(Photo.class);

		assertNotNull(responsePhoto.getId());
		assertTrue(responsePhoto.getId() > 0);
		assertEquals(albumId, responsePhoto.getAlbumId());
		assertEquals(title, responsePhoto.getTitle());
		assertEquals(url, responsePhoto.getUrl());
		assertEquals(thumbnailUrl, responsePhoto.getThumbnailUrl());
	}

}
