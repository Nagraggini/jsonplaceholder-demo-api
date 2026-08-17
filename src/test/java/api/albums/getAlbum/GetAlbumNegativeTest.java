package api.albums.getAlbum;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anEmptyMap;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.instanceOf;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import base.BaseApiTest;

class GetAlbumNegativeTest extends BaseApiTest{

	/**
	 * Task 5 – Get a Non-Existing Album

	Endpoint:
	
	GET https://jsonplaceholder.typicode.com/albums/999
	
	Requirements:
	
	Send a GET request to /albums/999.
	Verify that the status code is 404.
	Verify that the response body is an empty JSON object: {}.
	Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 5 – Get a Non-Existing Album")
	void getANonExistingAlbumTest() {
		given()
		.when().get("/albums/999")
		.then().log().ifValidationFails()
		.statusCode(404)
		.body("$",anEmptyMap());
	}
	
	/**
	 * Task 6 – Filter Albums by a Non-Existing User ID

	Endpoint:
	
	GET https://jsonplaceholder.typicode.com/albums?userId=999
	
	Requirements:
	
	Send a GET request to /albums.
	Add userId=999 as a query parameter.
	Verify that the status code is 200.
	Verify that the response body is a JSON list.
	Verify that the returned list is empty.
	Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 6 – Filter Albums by a Non-Existing User ID")
	void filterAlbumsByANonExistingUserIDTest()	{
		given()
			.queryParam("userId",999)
		.when()
			.get("/albums")
		.then()
			.log().ifValidationFails()
			.statusCode(200)
			.body("$", instanceOf(List.class))
			.body("$",empty());			
	}

}
