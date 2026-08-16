package api.albums.deleteAlbum;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import base.BaseApiTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

class DeleteAlbumTest extends BaseApiTest{

	/**
	 * Task 10 – Delete an Existing Album

	Precondition:
	
	An album with ID 25 exists.
	
	Endpoint:
	
	DELETE https://jsonplaceholder.typicode.com/albums/25
	
	Requirements:
	
	Send a DELETE request to /albums/25.
	Verify that the status code is either 200 or 204.
	Verify that the response body is either an empty JSON object or empty.
	Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 10 – Delete an Existing Album")
	void deleteAnExistingAlbumTest() {
		given()
		.when()
			.delete("/albums/25")
		.then()
			.log().ifValidationFails()
			.statusCode(anyOf(is(200),is(204)))
			.body("$", anEmptyMap());
	}
	
	/**
	 * Task 11 – Delete an Existing Album by Title
	 *
	 * Precondition:
	 *
	 * An album with the specified title exists.
	 *
	 * Endpoints:
	 *
	 * GET    https://jsonplaceholder.typicode.com/albums?title={title}
	 * DELETE https://jsonplaceholder.typicode.com/albums/{id}
	 *
	 * Requirements:
	 *
	 * Search for an album by its title.
	 * Verify that the search request returns status code 200.
	 * Verify that an album with the specified title exists.
	 * Extract the album ID from the response.
	 * Send a DELETE request using the extracted album ID.
	 * Verify that the delete response status code is either 200 or 204.
	 * Verify that the response body is either an empty JSON object or empty.
	 * Log responses only if validation fails.
	 */
	@Test
	@DisplayName("Task 11 – Delete an Existing Album by Title")
	void deleteAnExistingAlbumByTitleTest() {
		String title="omnis laborum odio";
		
	
	}

}
