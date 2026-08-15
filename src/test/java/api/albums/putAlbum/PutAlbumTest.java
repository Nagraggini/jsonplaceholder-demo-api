package api.albums.putAlbum;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PutAlbumTest {

	/**
	 * Task 9 – Update an Existing Album
	
	Endpoint:
	
	PUT https://jsonplaceholder.typicode.com/albums/25
	
	Request body:
	
	{
	  "userId": 3,
	  "id": 25,
	  "title": "Updated Rest Assured Album"
	}
	
	Requirements:
	
	Send a PUT request to /albums/25.
	Set the request content type to JSON.
	Add the provided album data to the request body.
	Verify that the status code is 200.
	Verify that the response contains userId with the value 3.
	Verify that the response contains id with the value 25.
	Verify that the returned title is "Updated Rest Assured Album".
	Extract the complete response into an Album object.
	Print the updated album’s ID and title.
	Log the response only if validation fails.
	 */
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
