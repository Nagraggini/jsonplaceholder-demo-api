package api.photos.photoList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import base.BaseApiTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

class GetPhotoListTest extends BaseApiTest {

	/**
	 * Task 201 – Retrieve All Photos
	 * 
	 * Endpoint:
	 * 
	 * GET /photos
	 * 
	 * Requirements:
	 * 
	 * Send a GET request to /photos.
	 * Verify that the status code is 200.
	 * Verify that the response is a JSON array.
	 * Verify that the response is not null and not empty.
	 * Verify that the response contains exactly 5000 photos.
	 * Verify that every photo contains these fields:
	 * albumId
	 * id
	 * title
	 * url
	 * thumbnailUrl
	 * Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 201 – Retrieve All Photos")
	void retrieveAllPhotosTest() {
		given()
				.accept(ContentType.JSON) // Also include in BaseApiTest class.
				.when()
				.get("/photos")
				.then()
				.log().ifValidationFails()
				.statusCode(200)
				.body("$", instanceOf(List.class))
				.body("$", allOf(notNullValue(), not(empty())))
				.body("$.size()", equalTo(5000))
				.body("$", everyItem(
						allOf(
								hasKey("albumId"), hasKey("id"), hasKey("title"), hasKey("url"),
								hasKey("thumbnailUrl"))));
	}

	/**
	 * Task 204 – Filter Photos by Album ID
	 * 
	 * Endpoint:
	 * 
	 * GET /photos?albumId={albumId}
	 * 
	 * Test data:
	 * 
	 * albumId = 1
	 * 
	 * Requirements:
	 * 
	 * Send albumId as a query parameter.
	 * Verify that the status code is 200.
	 * Verify that the response contains exactly 50 photos.
	 * Verify that every returned photo has an albumId of 1.
	 * Verify that the first photo has an ID of 1.
	 * Verify that the last photo has an ID of 50.
	 * Verify that the photo IDs are returned in ascending order.
	 * Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 204 – Filter Photos by Album ID")
	void filterPhotosByAlbumIDTest() {
		int albumId = 1;

		List<Integer> photoIds = new ArrayList<>(given()
				.queryParam("albumId", albumId)
				.when()
				.get("/photos")
				.then()
				.log().ifValidationFails()
				.statusCode(200)
				.body("$.size()", equalTo(50))
				.body("albumId", everyItem(equalTo(albumId)))
				.body("id[0]", equalTo(1))
				.body("id[49]", equalTo(50))
				.extract().jsonPath().getList("id", Integer.class));

		List<Integer> orderedPhotoIds = new ArrayList<>(photoIds);

		Collections.sort(orderedPhotoIds);

		assertEquals(orderedPhotoIds, photoIds);
	}

}
