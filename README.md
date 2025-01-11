# Alexandria

Alexandria is a RESTful API designed to manage information about books, authors, publishers, and book details. The backend is developed using Java with Spring Boot, integrating a MySQL database with Hibernate for ORM (Object-Relational Mapping). A `docker-compose` file is included to simplify database setup and management.

---

## Tech Stack

[![stack](https://skillicons.dev/icons?i=java,spring,hibernate,mysql,docker&theme=dark&height=120)](https://skillicons.dev)

---

## Features

This API provides endpoints for managing:

1. **Books**: CRUD operations for books, associating them with authors, publishers, and detailed metadata.
2. **Authors**: Managing author information.
3. **Publishers**: Managing publisher data.
4. **Book Details**: Additional metadata for books, such as summaries and page counts.

---

## Endpoints

### Books

| Method | Functionality | URL |
|--------|---------------|-----|
| GET    | Retrieve all books (paginated) | `/books` |
| GET    | Retrieve a book by its ID | `/books/{id}` |
| POST   | Create a new book | `/books` |
| PUT    | Update a book by its ID | `/books/{id}` |
| DELETE | Delete a book by its ID | `/books/{id}` |
| POST   | Add detailed metadata to a book | `/books/{bookId}/detail` |
| GET    | Retrieve detailed metadata of a book | `/books/{bookId}/detail` |
| PUT    | Update detailed metadata of a book | `/books/{bookId}/detail` |
| DELETE | Remove detailed metadata from a book | `/books/{bookId}/detail` |
| PUT    | Assign a publisher to a book | `/books/{bookId}/publisher/{publisherId}` |
| DELETE | Remove a publisher from a book | `/books/{bookId}/publisher` |
| PUT    | Add an author to a book | `/books/{bookId}/authors/{authorId}` |
| DELETE | Remove an author from a book | `/books/{bookId}/authors/{authorId}` |

#### Example Request Bodies

**Create or Update a Book**
```json
{
  "title": "The Great Adventure",
  "genre": "Adventure"
}
```

**Add Book Details**
```json
{
  "summary": "An epic journey across uncharted lands.",
  "pageCount": 350,
  "year": 2022,
  "isbn": "978-1234567890"
}
```

---

### Authors

| Method | Functionality | URL |
|--------|---------------|-----|
| GET    | Retrieve all authors | `/authors` |
| GET    | Retrieve an author by ID | `/authors/{id}` |
| POST   | Create a new author | `/authors` |
| PUT    | Update an author by ID | `/authors/{id}` |
| DELETE | Delete an author by ID | `/authors/{id}` |

#### Example Request Body

**Create or Update an Author**
```json
{
  "name": "Jane Doe",
  "nationality": "American"
}
```

---

### Publishers

| Method | Functionality | URL |
|--------|---------------|-----|
| GET    | Retrieve all publishers | `/publishers` |
| GET    | Retrieve a publisher by ID | `/publishers/{id}` |
| POST   | Create a new publisher | `/publishers` |
| PUT    | Update a publisher by ID | `/publishers/{id}` |
| DELETE | Delete a publisher by ID | `/publishers/{id}` |

#### Example Request Body

**Create or Update a Publisher**
```json
{
  "name": "Sunrise Publishing",
  "address": "123 Main St, Fiction City"
}
```

---

## Setup and Running the Application

### Prerequisites

- Java 17+
- Docker and Docker Compose

### Steps

1. Clone the repository.
2. Navigate to the project directory.
3. Build the project using Maven:
   ```bash
   mvn clean install
   ```
3. Start the MySQL database container:
   ```bash
   docker-compose up -d
   ```
5. Run the application:
   ```bash
   mvn spring-boot:run
   ```
6. Access the API at `http://localhost:8080`

---

## Database Schema

### Tables

- `books`: Stores book information (ID, title, genre, etc.)
- `authors`: Stores author information (ID, name, nationality, etc.)
- `publishers`: Stores publisher information (ID, name, address, etc.)
- `book_details`: Stores metadata for books (summary, page count, year, ISBN, etc.)

---
