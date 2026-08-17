package api.albums.albumList;



import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import albumsPOJO.Album;
import base.BaseApiTest;

class AlbumListTest extends BaseApiTest{

	/**
	 * Task 1 – Get and Search for One Album

	Endpoint:
	
	GET https://jsonplaceholder.typicode.com/albums
	Requirements
	Send a GET request to /albums.
	Verify that the status code is 200.
	Deserialize the response into a List<Album>.
	Verify that the list:
	is not null;
	is not empty;
	contains exactly 100 albums.
	 */
	@Test
	@DisplayName("Get album list")
	void getAlbumList() {
		List<Album> albums=
			given()
			.when()
				.get("/albums")
			.then()
				.log().ifValidationFails()
				.statusCode(200)
				.body("$", instanceOf(List.class))
				.body("$", notNullValue())
				.body("$", not(empty()))
				.body("$",hasSize(100))
				.extract().jsonPath().getList("",Album.class);
		
		assertNotNull(albums);
		assertFalse(albums.isEmpty());
		assertEquals(100,albums.size());
		
	}
	
	/**
	 * Task 2 – List contains userId, id, and title fields.
	 */
	@Test
	@DisplayName("List contains userId, id, and title fields.")
	void albumListContainsSpecificFields() {
					given()
					.when()
						.get("/albums")
					.then()
						.log().ifValidationFails()
						.statusCode(200)
						.body("$", everyItem(allOf(hasKey("userId")
						,hasKey("id")
						,hasKey("title"))));
		
	}
	
}
