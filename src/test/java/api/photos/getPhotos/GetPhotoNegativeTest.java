package api.photos.getPhotos;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anEmptyMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import base.BaseApiTest;

class GetPhotoNegativeTest extends BaseApiTest {

	/**
	 * Task 306 – Retrieve a Non-Existing Photo
	
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
	@DisplayName("Task 306 – Retrieve a Non-Existing Photo")
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
