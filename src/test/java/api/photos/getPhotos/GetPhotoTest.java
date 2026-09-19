package api.photos.getPhotos;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anEmptyMap;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import base.BaseApiTest;
import static io.restassured.RestAssured.given;
import photoPOJO.Photo;

class GetPhotoTest extends BaseApiTest {

	/**
	 * Task 202 – Retrieve a Photo by ID

	Endpoint:
	
	GET /photos/{id}
	
	Test data:
	
	id = 1
	
	Expected values:
	
	albumId = 1
	id = 1
	title = "accusamus beatae ad facilis cum similique qui sunt"
	
	Requirements:
	
	Send the photo ID as a path parameter.
	Verify that the status code is 200.
	Verify the expected albumId, id, and title.
	Verify that url is not null and not empty.
	Verify that thumbnailUrl is not null and not empty.
	Deserialize the complete response into a Photo object.
	Use JUnit assertions to verify the ID and title stored in the object.
	Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 202 – Retrieve a Photo by ID")
	void retrieveAPhotoByIDTest() {
		int id=1;
		int albumId=1;
		String title="accusamus beatae ad facilis cum similique qui sunt";
		
		Photo photo=
			given()
				.pathParam("id",id)
			.when()
				.get("/photos/{id}")
			.then()
				.log().ifValidationFails()
				.statusCode(200)
				.body("$", allOf(notNullValue(),not(empty())))
				.body("url", allOf(notNullValue(),not(emptyString())))
				.body("thumbnailUrl", allOf(notNullValue(),not(emptyString())))
				.body("id", equalTo(id))
				.body("albumId", equalTo(albumId))
				.body("title", equalTo(title))
				.extract().as(Photo.class);
		
		assertEquals(id,photo.getId());
		assertEquals(albumId,photo.getAlbumId());
		assertEquals(title,photo.getTitle());
	}
	
	/**
	 * Task 203 – Retrieve a Non-Existing Photo
	
	Endpoint:
	
	GET /photos/{id}
	
	Test data:
	
	id = 99999
	
	Requirements:
	
	Send a GET request using the non-existing photo ID.
	Verify that the status code is 404.
	Verify that the response body is an empty JSON object.
	Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 203 – Retrieve a Non-Existing Photo")
	void retrieveANonExistingPhotoTest() {
		int id=99999;
		
		given()
			.pathParam("id",id)
		.when()
			.get("/photos/{id}")
		.then()
			.log().ifValidationFails()
			.statusCode(404)
			.body("$", anEmptyMap());		
	}

}
