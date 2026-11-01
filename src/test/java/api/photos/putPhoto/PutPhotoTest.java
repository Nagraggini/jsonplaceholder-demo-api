package api.photos.putPhoto;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PutPhotoTest {

	/**
	 * Task 208 – Replace an Existing Photo

	Endpoint:
	
	PUT /photos/{id}
	
	Test data:
	
	id = 100
	
	Request data:
	
	{
	  "albumId": 5,
	  "id": 100,
	  "title": "The complete photo has been replaced",
	  "url": "https://example.com/images/replaced-photo.jpg",
	  "thumbnailUrl": "https://example.com/images/replaced-thumbnail.jpg"
	}
	
	Requirements:
	
	Send a PUT request using the photo ID as a path parameter.
	Send every photo field in the request body.
	Verify that the status code is 200.
	Verify that the response ID remains 100.
	Verify that every returned field matches the request data.
	Deserialize the response into a Photo object.
	Use JUnit assertions to verify the ID, title, and URL.
	Log the response only if validation fails.
	 */
	@Test
	@DisplayName("Task 208 – Replace an Existing Photo")
	void replaceAnExistingPhotoTest() {
		
	}

}
