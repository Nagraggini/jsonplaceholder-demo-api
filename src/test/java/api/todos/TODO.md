TODO

Todos API – Test Tasks
Base endpoint: https://jsonplaceholder.typicode.com

POJO: Todo

Expected JSON structure:
{
  "userId": 1,
  "id": 1,
  "title": "delectus aut autem",
  "completed": false
}



Task 301 – Retrieve All Todos
Endpoint:

GET /todos

Requirements:
- Send a GET request to retrieve all todos.
- Verify that the status code is 200.
- Verify that the response body is not empty.
- Verify that the response contains exactly 200 todos.
- Deserialize the response into a List<Todo>.
- Verify that the list is not null.
- Verify that the first todo has a valid ID greater than 0.
- Verify that every todo has a non-null title.
- Verify that every todo has a valid user ID greater than 0.
- Verify that the completed field contains a boolean value.
- Log the response only if validation fails.

Task 302 – Retrieve a Todo by ID
Endpoint:

GET /todos/{id}

Test data:

id = 5

Expected values:
{
  "userId": 1,
  "id": 5,
  "title": "laboriosam mollitia et enim quasi adipisci quia provident illum",
  "completed": false
}


Requirements:
- Send a GET request using the todo ID as a path parameter.
- Verify that the status code is 200.
- Verify that the response body is not empty.
- Deserialize the response into a Todo object.
- Verify that the returned ID matches the requested ID.
- Verify that the returned user ID is 1.
- Verify that the returned title matches the expected title.
- Verify that the returned completed value is false.
- Use JUnit assertions to validate the ID, title, and completion status.
- Log the response only if validation fails.

Task 303 – Retrieve a Non-Existing Todo
Endpoint:

GET /todos/{id}

Test data:

id = 999

Requirements:
- Send a GET request using a non-existing todo ID as a path parameter.
- Verify that the status code is 404.
- Verify that the response body is an empty JSON object.
- Verify that the response does not contain todo data.
- Log the response only if validation fails.

Task 304 – Filter Todos by User ID
Endpoint:

GET /todos?userId={userId}

Test data:

userId = 3

Requirements:
- Send a GET request using userId as a query parameter.
- Verify that the status code is 200.
- Verify that the response body is not empty.
- Deserialize the response into a List<Todo>.
- Verify that the list is not empty.
- Verify that every returned todo has userId = 3.
- Verify that every todo has a valid ID greater than 0.
- Verify that every todo has a non-null title.
- Verify that the response contains only todos belonging to the requested user.
- Log the response only if validation fails.

Task 305 – Filter Todos by Completion Status
Endpoint:

GET /todos?completed={completed}

Test data:

completed = true

Requirements:
- Send a GET request using completed as a query parameter.
- Verify that the status code is 200.
- Verify that the response body is not empty.
- Deserialize the response into a List<Todo>.
- Verify that the list is not empty.
- Verify that every returned todo has completed = true.
- Verify that every todo has a valid ID greater than 0.
- Verify that every todo has a non-null title.
- Verify that no incomplete todos are returned.
- Log the response only if validation fails.

Task 306 – Filter Todos by User ID and Completion Status
Endpoint:

GET /todos?userId={userId}&completed={completed}

Test data:
- userId = 2
- completed = true
Requirements:
- Send a GET request using both userId and completed as query parameters.
- Verify that the status code is 200.
- Verify that the response body is not empty.
- Deserialize the response into a List<Todo>.
- Verify that the list is not empty.
- Verify that every returned todo has userId = 2.
- Verify that every returned todo has completed = true.
- Verify that every todo has a valid ID greater than 0.
- Verify that every todo has a non-null title.
- Log the response only if validation fails.

Task 307 – Create a New Todo
Endpoint:

POST /todos

Request data:
{
  "userId": 5,
  "title": "Complete REST Assured API testing tasks",
  "completed": false
}


Requirements:
- Create a new Todo object using the request data.
- Send a POST request with the complete todo data.
- Verify that the status code is 201.
- Verify that the response contains a generated ID.
- Verify that the generated ID is greater than 0.
- Deserialize the response into a Todo object.
- Verify that all returned fields match the request data.
- Use JUnit assertions to verify the generated ID, user ID, title, and completion status.
- Log the response only if validation fails.

Task 308 – Replace an Existing Todo
Endpoint:

PUT /todos/{id}

Test data:

id = 10

Request data:
{
  "userId": 4,
  "id": 10,
  "title": "The todo has been completely replaced",
  "completed": true
}


Requirements:
- Create a new Todo object using the request data.
- Send a PUT request using the todo ID as a path parameter.
- Send every todo field in the request body.
- Verify that the status code is 200.
- Verify that the response ID remains 10.
- Deserialize the response into a Todo object.
- Verify that every returned field matches the request data.
- Use JUnit assertions to verify the ID, user ID, title, and completion status.
- Log the response only if validation fails.

Task 309 – Partially Update an Existing Todo
Endpoint:

PATCH /todos/{id}

Test data:

id = 15

Request data:
{
  "completed": true
}


Requirements:
- Create a request body containing only the completed field.
- Send a PATCH request using the todo ID as a path parameter.
- Verify that the status code is 200.
- Verify that the response ID remains 15.
- Deserialize the response into a Todo object.
- Verify that the returned completed value is true.
- Verify that the response contains the original userId.
- Verify that the response contains the original title.
- Use JUnit assertions to validate the ID and completion status.
- Log the response only if validation fails.

Task 310 – Update the Title of an Existing Todo
Endpoint:

PATCH /todos/{id}

Test data:

id = 20

Request data:
{
  "title": "Learn advanced REST Assured techniques"
}


Requirements:
- Create a request body containing only the title field.
- Send a PATCH request using the todo ID as a path parameter.
- Verify that the status code is 200.
- Verify that the response ID remains 20.
- Deserialize the response into a Todo object.
- Verify that the returned title matches the updated title.
- Verify that the response contains the original user ID.
- Verify that the response contains the original completion status.
- Use JUnit assertions to validate the ID and title.
- Log the response only if validation fails.

Task 311 – Delete an Existing Todo
Endpoint:

DELETE /todos/{id}

Test data:

id = 25

Requirements:
- Send a DELETE request using the todo ID as a path parameter.
- Verify that the status code is 200 or 204.
- Verify that the response body is either:
  - an empty JSON object, or
  - completely empty.
- Log the response only if validation fails.

Task 312 – Find and Delete a Todo by Title
Search endpoint:

GET /todos?title={title}

Delete endpoint:

DELETE /todos/{id}

Test data:

title = "delectus aut autem"

Requirements:
- Send a GET request using the title as a query parameter.
- Verify that the GET response status code is 200.
- Deserialize the response into a List<Todo>.
- Verify that the list is not empty.
- Verify that the returned todo title exactly matches the searched title.
- Verify that the returned todo has a valid ID greater than 0.
- Extract the ID of the first returned todo.
- Send a DELETE request to /todos/{id} using the extracted ID.
- Verify that the DELETE response status code is 200 or 204.
- Verify that the DELETE response body is either an empty JSON object or empty.
- Log responses only if validation fails.