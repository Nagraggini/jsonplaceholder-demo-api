

Task 110 – Create a New Comment Using a POJO

Endpoint:

POST /comments

Request data:

{
  "postId": 1,
  "name": "REST Assured practice comment",
  "email": "practice@test.com",
  "body": "This comment was created during API test practice."
}

Requirements:

Create the request body using a Comment object.
Send a POST request to /comments.
Verify that the status code is 201.
Verify that the response contains an ID.
Verify that the returned postId, name, email, and body match the submitted values.
Deserialize the response into a Comment object.
Use a JUnit assertion to verify that the returned ID is greater than zero.
Log the response only if validation fails.

Task 111 – Replace an Existing Comment

Endpoint:

PUT /comments/{id}

Test data:

id = 10

Request data:

{
  "postId": 2,
  "name": "Updated comment name",
  "email": "updated@test.com",
  "body": "The complete comment has been replaced."
}

Requirements:

Send a PUT request using the comment ID in the endpoint.
Send all comment fields in the request body.
Verify that the status code is 200.
Verify that the returned ID is still 10.
Verify that every returned field matches the submitted data.
Deserialize the response into a Comment object.
Verify the values stored in the object.
Log the response only if validation fails.

Task 112 – Partially Update an Existing Comment

Endpoint:

PATCH /comments/{id}

Test data:

id = 10

Request data:

{
  "email": "patched@test.com"
}

Requirements:

Send a PATCH request containing only the new email address.
Verify that the status code is 200.
Verify that the returned ID is still 10.
Verify that the returned email is "patched@test.com".
Verify that the original postId, name, and body fields are still present.
Log the response only if validation fails.

Task 113 – Delete a Comment Found by Email

Endpoints:

GET /comments?email={email}
DELETE /comments/{id}

Test data:

email = Eliseo@gardner.biz

Requirements:

Search for a comment using the specified email address.
Verify that the search request returns status code 200.
Verify that the search result is

