# School Course Enrollment System

The project aims to develop a RESTful API for a student management system within educational institutions, with a
primary focus on implementing CRUD (Create, Read, Update, Delete) operations for student and course entities.
Additionally, the API will support functionalities related to the assignment of students to specific courses.

## Key feature

- Students management
- Courses management
- Course enrollment
- Email notification for student creation and course enrollment
- Open AI integration for new course based on the current enrolments

## Technologies and frameworks used:

- Java 17
- Spring Boot 3.1.5
- Spring Boot Starter Mail
- Spring Boot Starter Data JPA
- Spring Boot Starter Validation
- Spring Boot Starter Thymeleaf
- Spring Boot Starter Test
- Spring Boot Starter Security & JWT
- Springdoc OpenAPI
- Maven
- PostgreSQL
- Hibernate
- Lombok
- OKHTTP
- H2 in-memory database
- MockMVC
- JUnit
- Mockito

### Student endpoints:

#### 1. Create Student

- Endpoint: `/api/students`
- Method: `POST`
- Request body:

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "birthDate": "1991-08-02"
}
```

- Success responses:
    - `201 Created`

```json
{
  "id": 5,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "birthDate": "1991-08-02",
  "createdAt": "2023-12-13T21:53:46.634599"
}
```

- Error Responses:
    - `400 Bad Request`

```json
{
  "firstName": "The first name must contain a minimum of 3 characters."
}
```

#### 2. Read Students

##### 2.1 Get student by id

- Endpoint: `/api/students/{id}`
- Method: `GET`
- Path variable: `{id}` - The unique identifier of the searched student.
- Success responses:
    - `200 OK`

```json
{
  "id": 5,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "birthDate": "1991-08-02",
  "createdAt": "2023-12-13T21:53:46.634599"
}
```

- Error Responses:
    - `404 Not Found`

```json
{
  "message": "Invalid student id."
}
```

##### 2.2 Get all students

- Endpoint: `/api/students`
- Method: `GET`
- Success responses:
    - `200 OK`

```json
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "birthDate": "1991-08-02",
    "createdAt": "2023-12-11T23:18:38.08905"
  },
  {
    "id": 2,
    "firstName": "Casper",
    "lastName": "Davis",
    "email": "casper.davis.com",
    "birthDate": "2000-01-12",
    "createdAt": "2023-12-11T23:18:39.954523"
  }
]
```

#### 3. Update Student

- Endpoint: `/api/students/{id}`
- Method: `PUT`
- Path variable: `{id}` - The unique identifier of the student to be updated.
- Request body:

```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "birthDate": "1991-08-02"
}
```

- Success responses:
    - `200 Ok`

```json
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "birthDate": "1991-08-02"
}
```

- Error Responses:
    - `400 Bad Request`

```json
{
  "message": "The email address is not in the correct format!"
}
```

- `404 Not Found`

```json
{
  "message": "Invalid student id."
}
```

#### 4 Delete Student

- Endpoint: `/api/students/{id}`
- Method: `DELETE`
- Path variable: `{id}` - The unique identifier of the student to be deleted.
- Success responses:
    - `200 OK`

- Error Responses:
    - `404 Not Found`

```json
{
  "message": "Invalid student id."
}
```

### Course endpoints

#### 1. Create course

- Endpoint: `/api/courses`
- Method: `POST`
- Request body:

```json
{
  "courseName": "Java Introduction",
  "description": "Java course for non-programmers."
}
```

- Success responses:
    - `201 Created`

```json
{
  "id": 1,
  "courseName": "Java Introduction",
  "description": "Java course for non-programmers.",
  "createdAt": "2023-12-13T22:26:05.598212"
}
```

- Error Responses:
    - `400 Bad Reques`

```json
{
  "courseName": "Course name is mandatory."
}
```

#### 2. Read courses

##### 2.1 Get course by id

- Endpoint: `/api/courses/{id}`
- Method: `GET`
- Path variable: `{id}` - The unique identifier of the searched course.
- Success responses:
    - `200 OK`

```json
{
  "id": 1,
  "courseName": "Java Introduction",
  "description": "Java course for non-programmers.",
  "createdAt": "2023-12-13T22:26:05.598212"
}
```

- Error Responses:
    - `404 Not Found`

```json
{
  "message": "Invalid course id."
}
```

##### 2.2 Get all courses with filter and sort

- Endpoint: `/api/courses`
- Method: `GET`
- Query Parameters(optional):
    - courseName (String): Filters courses by name.
    - description (String): Filters courses by description.
    - orderBy (String, Default: "id"): Specifies the field by which the results should be ordered.
    - orderDirection (String, Default: "asc"): Specifies the order direction ("asc" for ascending, "desc" for
      descending).
- Success responses:
    - `200 OK`

```json
[
  {
    "id": 1,
    "courseName": "Java Introduction",
    "description": "Java course for non-programmers.",
    "createdAt": "2023-12-11T23:18:52.173059"
  },
  {
    "id": 2,
    "courseName": "Web Development Basics",
    "description": "Introduction to web development concepts.",
    "createdAt": "2023-12-11T23:19:15.504107"
  }
  // ... other courses
]
```

