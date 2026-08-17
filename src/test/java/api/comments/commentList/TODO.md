

Task 104 – Retrieve a Comment by ID

Endpoint:

GET /comments/{id}

Test data:

id = 1

Requirements:

Send a GET request using the comment ID as a path parameter.
Verify that the status code is 200.
Verify the following values:
postId is 1
id is 1
name is "id labore ex et quam laborum"
email is "Eliseo@gardner.biz"
Verify that the body contains the word "laudantium".
Deserialize the complete response into a Comment object.
Use JUnit assertions to verify the ID and email stored in the object.
Log the response only if validation fails.

Task 5 – Filter Comments by Post ID

Endpoint:

GET /comments?postId={postId}

Test data:

postId = 5

Requirements:

Send a GET request using postId as a query parameter.
Verify that the status code is 200.
Verify that the response contains exactly five comments.
Verify that every returned comment has a postId of 5.
Verify that the returned comment IDs are exactly:
21
22
23
24
25
Verify the IDs in the expected order.
Log the response only if validation fails.
Task 6 – Compare Filtered and Nested Comment Routes

Endpoints:

GET /comments?postId=10
GET /posts/10/comments

Requirements:

Send a GET request to both endpoints.
Verify that both requests return status code 200.
Extract the comment IDs from both responses.
Verify that both responses contain exactly five comments.
Verify that every returned comment has a postId of 10.
Verify that the two extracted ID lists are equal.
Log responses only if validation fails.
Task 7 – Search for a Comment by Email

Endpoint:

GET /comments?email={email}

Test data:

email = Eliseo@gardner.biz

Requirements:

Store the email address in a separate variable.
Search for comments using the email address as a query parameter.
Verify that the status code is 200.
Verify that the response is not empty.
Verify that the response contains the specified email address.
Extract the first result into a Comment object.
Use a JUnit assertion to verify the email stored in the extracted object.
Log the response only if validation fails.
Task 8 – Search for a Nonexistent Comment

Endpoint:

GET /comments?email={email}

Test data:

email = not-existing@example.com

Requirements:

Search for comments using the nonexistent email address.
Verify that the status code is 200.
Verify that the response is a JSON array.
Verify that the returned array is empty.
Log the response only if validation fails.
Task 9 – Deserialize a List of Comments

Endpoint:

GET /comments?postId=20

Requirements:

Send a GET request using postId = 20.
Verify that the status code is 200.
Deserialize the complete response into a List<Comment>.
Use JUnit assertions to verify that:
the list is not null;
the list is not empty;
the list contains exactly five elements;
the first comment has ID 96;
the last comment has ID 100;
every comment has a postId of 20.
Log the response only if validation fails.
Task 10 – Create a New Comment Using a POJO

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
Task 11 – Replace an Existing Comment

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
Task 12 – Partially Update an Existing Comment

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
Task 13 – Delete a Comment Found by Email

Endpoints:

GET /comments?email={email}
DELETE /comments/{id}

Test data:

email = Eliseo@gardner.biz

Requirements:

Search for a comment using the specified email address.
Verify that the search request returns status code 200.
Verify that the search result is