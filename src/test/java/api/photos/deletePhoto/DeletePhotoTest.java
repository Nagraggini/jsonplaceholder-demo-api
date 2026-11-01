package api.photos.deletePhoto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DeletePhotoTest {

	/**
	 * Task 309 – Delete an Existing Photo

	Endpoint:
	
	DELETE /photos/{id}
	
	Test data:
	
	id = 250
	
	Requirements:
	
	Send a DELETE request using the photo ID as a path parameter.
	Verify that the status code is 200 or 204.
	Verify that the response body is either:
	an empty JSON object, or
	completely empty.
	Log the response only if validation fails.
	
	Task 210 – Find and Delete a Photo by Title
	
	Search endpoint:
	
	GET /photos?title={title}
	
	Delete endpoint:
	
	DELETE /photos/{id}
	
	Test data:
	
	title = "et inventore quae ut tempore eius voluptatum"
	
	Requirements:
	
	Send a GET request using the title as a query parameter.
	Verify that the GET response status code is 200.
	Deserialize the response into a List<Photo>.
	Verify that the list is not empty.
	Verify that the returned photo title exactly matches the searched title.
	Extract the ID of the first returned photo.
	Send a DELETE request to /photos/{id} using the extracted ID.
	Verify that the DELETE response status code is 200 or 204.
	Verify that the DELETE response body is either an empty JSON object or empty.
	Log responses only if validation fails.
	 */
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
