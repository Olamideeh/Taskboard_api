# TaskBoard

TaskBoard is a Spring Boot REST API for managing tasks.

The project demonstrates a layered Spring Boot architecture using:

* Controller
* Service
* Repository
* Entity
* DTO
* Exception Handling

## Tech Stack

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* Maven
* Database
* JUnit / Spring Boot Test

## Project Structure

```text
src/main/java/com/qosim/taskboard
│
├── controller
│   └── TaskController
│
├── service
│   └── TaskService
│
├── repository
│   └── TaskRepository
│
├── entity
│   └── Task
│
├── dto
│   └── UpdateTaskRequest
│
└── exception
    └── GlobalExceptionHandler
```

## Architecture

The application follows a layered architecture:

```text
Client
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
Database
```

### Controller

Responsible for handling HTTP requests and returning HTTP responses.

### Service

Contains the application's business logic.

### Repository

Communicates with the database using Spring Data JPA.

### Entity

Represents the task stored in the database.

### DTO

Used to transfer request/response data without exposing the entity directly through the API.

## API Endpoints

### Create Task

```http
POST /task/createtask
```

Example request:

```json
{
  "taskName": "Learn Spring Boot",
  "description": "Practice REST APIs",
  "priority": "HIGH",
  "status": "TODO"
}
```

Returns:

```text
201 Created
```

### Get All Tasks

```http
GET /task/gettask
```

Returns all tasks.

### Get Task By ID

```http
GET /task/{id}
```

Example:

```http
GET /task/5
```

Returns the task with ID `5`.

### Get Task By Name

```http
GET /task/name/{name}
```

Example:

```http
GET /task/name/Learn Spring Boot
```

Returns the task with the specified name.

### Delete Task

```http
DELETE /task/{id}
```

Example:

```http
DELETE /task/5
```

Successful deletion returns:

```text
204 No Content
```

## Running the Application

Clone the project and open it in your IDE.

Run the Spring Boot application from:

```text
TaskboardApplication.java
```

Or use Maven:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

## Testing

The project uses Spring Boot's testing support for unit and integration testing.

Run the tests with:

```bash
./mvnw test
```

On Windows:

```bash
mvnw.cmd test
```

## Future Improvements

* Add update task endpoint
* Add request validation
* Add custom exceptions
* Add global exception handler
* Add pagination
* Add sorting and filtering
* Add authentication and authorization
* Add comprehensive unit and integration tests
* Add API documentation with OpenAPI/Swagger

## Author

Qosim
