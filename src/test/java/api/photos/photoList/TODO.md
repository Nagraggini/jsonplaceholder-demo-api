TODO

Task 204 – Filter Photos by Album ID

Endpoint:

GET /photos?albumId={albumId}

Test data:

albumId = 1

Requirements:

Send albumId as a query parameter.
Verify that the status code is 200.
Verify that the response contains exactly 50 photos.
Verify that every returned photo has an albumId of 1.
Verify that the first photo has an ID of 1.
Verify that the last photo has an ID of 50.
Verify that the photo IDs are returned in ascending order.
Log the response only if validation fails.

Task 205 – Deserialize a Filtered Photo List

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
Task 206 – Compare Two Equivalent Photo Endpoints

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
Task 207 – Create a New Photo

Endpoint:

POST /photos

Request data:

{
  "albumId": 10,
  "title": "REST Assured test photo",
  "url": "https://example.com/images/test-photo.jpg",
  "thumbnailUrl": "https://example.com/images/test-thumbnail.jpg"
}

Requirements:

Send a POST request with the complete photo data.
Verify that the status code is 201.
Verify that the response contains a generated id.
Verify that the generated ID is greater than 0.
Verify that all returned fields match the request data.
Deserialize the response into a Photo object.
Use JUnit assertions to verify the returned title and album ID.
Log the response only if validation fails.
Task 208 – Replace an Existing Photo

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
Task 209 – Delete an Existing Photo

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

Fontos: a JSONPlaceholder csak szimulálja a POST, PUT és DELETE műveleteket. A változtatásokat nem menti el véglegesen a szerveren. JSONPlaceholder Guide