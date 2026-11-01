package api.photos.getPhotos;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import base.BaseApiTest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import photoPOJO.Photo;

class GetPhotoTest extends BaseApiTest {

	/**
	 * Task 303 – Retrieve a Photo by ID

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
	@DisplayName("Task 303 – Retrieve a Photo by ID")
	void retrieveAPhotoByID() {
		
		int id = 1;

	    Photo photo =
	        given()
	            .pathParam("id", id)
	        .when()
	            .get("/photos/{id}")
	        .then()
	            .log().ifValidationFails()
	            .statusCode(200)
	            .extract()
	            .as(Photo.class);

	    assertEquals(1, photo.getId());
	    assertEquals(1, photo.getAlbumId());
	    assertEquals(
	        "accusamus beatae ad facilis cum similique qui sunt",
	        photo.getTitle()
	    );

	    assertNotNull(photo.getUrl());
	    assertFalse(photo.getUrl().isEmpty());

	    assertNotNull(photo.getThumbnailUrl());
	    assertFalse(photo.getThumbnailUrl().isEmpty());
	}
	
	
	
	
	
	/**
	 * Task 304 – Deserialize a Filtered Photo List

	Endpoint:
	
	GET /photos?albumId={albumId}
	
	Test data:
	
	albumId = 2
	
	Requirements:
	
	Send a GET request using albumId as a query parameter.
	Verify that the status code is 200.
	Deserialize the response into a List<Photo>.
	Use JUnit assertions to verify:
	the list is not null;
	the list contains exactly 50 photos;
	the first photo has an ID of 51;
	the last photo has an ID of 100;
	every photo has an albumId of 2;
	every title is not null and not blank.
	Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 304 – Deserialize a Filtered Photo List")
	void deserializeAFilteredPhotoList() {
		int albumId = 2;
		
		Response response=
				given()
			.queryParam("albumId",albumId)
		.when()
			.get("/photos")
		.then()
			.log().ifValidationFails()
			.statusCode(200).extract().response();
		
		List<Photo>photos=response	
			.jsonPath().getList("",Photo.class);
		
		assertNotNull(photos);
		assertEquals(50, photos.size());
		assertEquals(51, photos.get(0).getId());
		assertEquals(100, photos.get(photos.size() - 1).getId());
		
		for (Photo photo : photos) {
			assertEquals(albumId,photo.getAlbumId());
			assertNotNull(photo.getTitle());
			assertFalse(photo.getTitle().isBlank());
		}			
	}

	/**
	 * Task 305 – Compare Two Equivalent Photo Endpoints
	
	Endpoints:
	
	GET /photos?albumId=3
	GET /albums/3/photos
	
	Requirements:
	
	Send a GET request to both endpoints.
	Verify that both responses have a status code of 200.
	Deserialize both responses into separate List<Photo> collections.
	Verify that both lists contain exactly 50 photos.
	Extract the photo IDs from both lists.
	Verify that the two ID lists are equal and in the same order.
	Verify that every returned photo has an albumId of 3.
	Log responses only if validation fails.
	 */
	@Test
	@DisplayName("Task 305 – Compare Two Equivalent Photo Endpoints")
	void compareTwoEquivalentPhotoEndpoints() {
		int albumId=3;
		
		Response responseQueryParam= 
				given()
					.queryParam("albumId",albumId)
				.when()
					.get("photos")
				.then()
					.log().ifValidationFails()
					.statusCode(200).extract().response();
		
		Response responsePathParam= 
				given()
					.pathParam("albumId",albumId)
				.when()
					.get("/albums/{albumId}/photos")
				.then()
					.log().ifValidationFails()
					.statusCode(200).extract().response();
		
		List <Photo> photosQueryParam=responseQueryParam.jsonPath().getList("",Photo.class);
		List <Photo> photosPathParam=responsePathParam.jsonPath().getList("",Photo.class);
		
		try {
			assertEquals(50, photosQueryParam.size());
			assertEquals(50, photosPathParam.size());
			
			List <Integer> photoIdsQueryParam=responseQueryParam.jsonPath().getList("id",Integer.class);
			List <Integer> photoIdsPathParam=responsePathParam.jsonPath().getList("id",Integer.class);
			
			assertEquals(photoIdsQueryParam, photoIdsPathParam);
			
			for (Photo photo : photosQueryParam) {
			    assertEquals(3, photo.getAlbumId());
			}
	
			for (Photo photo : photosPathParam) {
			    assertEquals(3, photo.getAlbumId());	
			}
			
		} catch (AssertionError e) {
		    responseQueryParam.then().log().all();
		    responsePathParam.then().log().all();
		    throw e;
		}
		
	}
}
