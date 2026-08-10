package api.albums.getAlbum;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import base.BaseApiTest;

class GetAlbum extends BaseApiTest{

	/**
	 * Task 2 – Get and Search for One Album

	Endpoint:
	
	GET https://jsonplaceholder.typicode.com/albums
	Requirements
	Send a GET request to /albums.
	Verify that the status code is 200.
	Find the album with id = 25.
	Verify that the album exists.
	Verify its data:
	userId: 3
	id: 25
	title: vero maxime id possimus sunt neque et consequatur
	 */
	@Test
	@DisplayName("Get and Search for One Album")
	void getAndSearchForOneAlbum() {
		given().when().get("/albums").then().log().ifValidationFails()
		.statusCode(200)
		.body("id",equalTo("25"));
	}
	
	/**
	 * Task 3 – Filter Albums by User ID

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
	void test() {
		fail("Not yet implemented");
	}

}
