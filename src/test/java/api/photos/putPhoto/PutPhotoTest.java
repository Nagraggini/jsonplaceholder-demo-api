package api.photos.putPhoto;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import base.BaseApiTest;
import photoPOJO.Photo;

class PutPhotoTest extends BaseApiTest {

	/**
	 * Task 308 – Replace an Existing Photo
	  
	 Endpoint:
	  
	 PUT /photos/{id}
	  
	 Test data:
	  
	 id = 100
	  
	 Request data:
	 
	 { "albumId": 5, 
	 "id": 100, "title": "The complete photo has been replaced",
	 "url": "https://example.com/images/replaced-photo.jpg", 
	 "thumbnailUrl": "https://example.com/images/replaced-thumbnail.jpg" }
	 
	 Requirements:
	 
	 Send a PUT request using the photo ID as a path parameter. 
	 Send every photo field in the request body. 
	 Verify that the status code is 200.
	 Verify that the response ID remains 100. 
	 Verify that every returned field matches the request data. 
	 Deserialize the response into a Photo object. 
	 Use JUnit assertions to verify the ID, title, and URL. 
	 Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 308 – Replace an Existing Photo")
	void replaceAnExistingPhotoTest() {

		int albumId = 5;
		int id = 100;
		String title = "The complete photo has been replaced";
		String url = "https://example.com/images/replaced-photo.jpg";
		String thumbnailUrl = "https://example.com/images/replaced-thumbnail.jpg";

		Photo newPhoto = new Photo();
		newPhoto.setAlbumId(albumId);
		newPhoto.setId(id);
		newPhoto.setTitle(title);
		newPhoto.setUrl(url);
		newPhoto.setThumbnailUrl(thumbnailUrl);
		
		Photo responsePhoto=
			given()
				.pathParam("id", id)
				.body(newPhoto)
			.when()
				.put("/photos/{id}")
			.then()
				.log().ifValidationFails()
				.statusCode(200)
				.extract().as(Photo.class);
		
		assertEquals(albumId,responsePhoto.getAlbumId());
		assertEquals(id,responsePhoto.getId());
		assertEquals(title,responsePhoto.getTitle());
		assertEquals(url,responsePhoto.getUrl());
		assertEquals(thumbnailUrl,responsePhoto.getThumbnailUrl());
	}

}
