# Library Management System - WAD

Course project for Web Application Development. The repository contains two integrated application layers:

- a legacy JSP/Servlet library management UI;
- a Spring Boot application with Thymeleaf pages, REST endpoints, security, and profile management.

Student: Daryn Sagadi, WTiPO-33

## What is included

### Main modules

- Authentication: register, login, logout, protected pages.
- Home dashboard: overview cards and navigation.
- Books: list, details, read view, CRUD logic.
- Authors: list and management pages.
- Libraries: management pages.
- Members: management pages.
- Profile: nickname, avatar upload, avatar URL, password change.
- Admin users: create, edit, and list users.
- REST API: book CRUD and demo endpoints.

### Application layers

- `kz.example.lms` - servlet-based legacy web layer.
- `kz.enu.vtrestapi` - Spring Boot layer with controllers, services, DTOs, repository, security, and error handling.

## Technologies

- Java 17+
- Spring Boot 3.4.0
- Spring Security
- Spring Data JPA
- Jakarta Servlet / JSP / JSTL
- Thymeleaf
- Maven
- PostgreSQL for the Spring Boot user/account data
- In-memory storage for the legacy LMS demo data

## Run locally

### Prerequisites

- JDK 17 or newer
- Maven 3.6+ or Maven Wrapper
- PostgreSQL running for the Spring Boot account module if you want that part enabled

### Start the app

Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

Or with the Maven Wrapper on Unix-like systems:

```bash
./mvnw spring-boot:run
```

After startup, open:

- `http://localhost:8080/`
- `http://localhost:8080/home`
- `http://localhost:8080/login`
- `http://localhost:8080/register`
- `http://localhost:8080/profile`
- `http://localhost:8080/api/books`

## REST API

Base URL: `http://localhost:8080`

### Books API

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/books` | List all books |
| GET | `/api/books/{id}` | Get book by id |
| POST | `/api/books` | Create a book |
| PUT | `/api/books/{id}` | Update a book |
| DELETE | `/api/books/{id}` | Delete a book |

Example request body:

```json
{
  "title": "New book",
  "isbn": "ISBN-12345",
  "authorId": 1,
  "libraryId": 1,
  "publishedYear": 2026
}
```

### Demo endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/index` | Simple text response |
| GET | `/index/specific` | JSON with a sample student |
| POST | `/index/specific?name=...` | JSON response for the provided name |

## Web routes

- `/login`
- `/register`
- `/logout`
- `/home`
- `/authors`
- `/books`
- `/libraries`
- `/members`
- `/book?id={id}`
- `/read?id={id}`
- `/settings`
- `/profile`

## Project structure

```text
src/main/java/
├── kz/enu/vtrestapi/
│   ├── config/
│   ├── controller/
│   ├── dto/
│   ├── exception/
│   ├── model/
│   ├── pattern/
│   ├── repository/
│   ├── security/
│   └── service/
└── kz/example/lms/
    ├── model/
    ├── servlet/
    ├── service/
    ├── store/
    └── util/

src/main/resources/
├── static/css/app.css
└── templates/
    ├── admin/
    ├── home.html
    ├── login.html
    ├── profile.html
    ├── register.html
    └── lab11.html

src/main/webapp/
└── WEB-INF/jsp/
    ├── partials/
    ├── authors.jsp
    ├── book.jsp
    ├── books.jsp
    ├── home.jsp
    ├── libraries.jsp
    ├── login.jsp
    ├── members.jsp
    ├── read.jsp
    ├── register.jsp
    └── settings.jsp
```

## Notes

- The repository includes both the original JSP/Servlet implementation and the newer Spring Boot implementation.
- Some UI parts are duplicated because the project is being modernized step by step.
- Generated logs such as `boot-out.log` and `boot-err.log` are local development artifacts and are not part of the application code.

## License

Educational project for WAD.
