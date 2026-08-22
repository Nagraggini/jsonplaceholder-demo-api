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

