package api.albums.getAlbum;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import albumsPOJO.Album;
import base.BaseApiTest;

class GetAlbum extends BaseApiTest{

	/**
	 * Task 3 – Get and Search for One Album

	Endpoint:
	
	GET https://jsonplaceholder.typicode.com/albums?userId=7
	Requirements
	Send a GET request to /albums using userId=7 as a query parameter.
	Verify that the status code is 200.
	Verify that the album exists.
	Verify its data:
	userId: 3
	id: 25
	title: vero maxime id possimus sunt neque et consequatur
	 */
	@Test
	@DisplayName("Get and Search for One Album")
	void getAndSearchForOneAlbumTest() {
		Album album=
				given()
				.when()
					.get("/albums/25")
				.then()
					.log().ifValidationFails()
					.statusCode(200)
					.extract().jsonPath().getObject("",Album.class);
		
		assertEquals(3,album.getUserId());
		assertEquals(25,album.getId());
		assertEquals("vero maxime id possimus sunt neque et consequatur",album.getTitle());	
	}
	
	@Test
	@DisplayName("2.Solution for Get and Search for One Album")
	void getAndSearchForOneAlbumTest2() {
	    Album album = given()
	        .when()
	            .get("/albums")
	        .then()
	            .log().ifValidationFails()
	            .statusCode(200)
	            .extract()
	            .jsonPath()
	            .getObject("find { it.id == 25 }", Album.class);

	    assertNotNull(album);
	    assertEquals(3, album.getUserId());
	    assertEquals(25, album.getId());
	    assertEquals(
	        "vero maxime id possimus sunt neque et consequatur",
	        album.getTitle()
	    );
	}
	
	/**
	 * Task 4 – Filter Albums by User ID

	Endpoint:
	
	GET https://jsonplaceholder.typicode.com/albums
	Requirements
	Send a GET request to /albums.
	Verify that the status code is 200.
	Deserialize the response into a List<Album>.
	Find all albums where userId = 7.
	Verify that exactly 10 albums were found.
	Verify that every album in the filtered list has userId = 7.
		 */
	@Test
	@DisplayName("Filter Albums by User ID")
	void filterAlbumsByUserIDTest () {
		given()
			.queryParam("userId", 7) // Filtering
		.when()
			.get("/albums")
		.then()
			.log().ifValidationFails()
			.statusCode(200)
			.body("$", instanceOf(List.class))
			.body("size()", equalTo(10))			
			.body("userId", everyItem(equalTo(7)));
		
	}

}
