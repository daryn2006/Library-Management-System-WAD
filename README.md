# Library Management System — WAD

Учебный проект по дисциплине Web Application Development. Репозиторий объединяет два уровня одного решения:

- классический веб-интерфейс на JSP/Servlet;
- современный модуль на Spring Boot с Thymeleaf, REST API и профилем пользователя.

Студент: **Сағади Дарын**, **ВТиПО-33**

## О проекте

Это библиотечная система, в которой можно управлять книгами, авторами, библиотеками, читателями и пользовательскими профилями.  
Проект развивался поэтапно, поэтому в репозитории сохранены как исходная servlet-часть, так и Spring Boot-часть.

## Что умеет система

### Основные возможности

- Регистрация, вход и выход из системы.
- Защита приватных страниц.
- Главная панель с навигацией и карточками.
- Каталог книг: список, карточка книги, чтение текста.
- CRUD для авторов.
- CRUD для библиотек.
- CRUD для читателей.
- Профиль пользователя:
  - изменение имени;
  - изменение nickname;
  - загрузка аватара;
  - указание avatar URL;
  - смена пароля.
- Админ-панель для управления пользователями.
- REST API для книг и демонстрационные endpoint'ы.

### Слои приложения

- `kz.example.lms` — legacy JSP/Servlet слой.
- `kz.enu.vtrestapi` — Spring Boot слой с контроллерами, сервисами, DTO, репозиторием, security и обработкой ошибок.

## Технологии

- Java 17+
- Spring Boot 3.4.0
- Spring Security
- Spring Data JPA
- Thymeleaf
- Jakarta Servlet / JSP / JSTL
- Maven
- PostgreSQL для модулей Spring Boot
- In-memory storage для демонстрационных данных legacy-части

## Быстрый запуск

### Требования

- JDK 17 или новее
- Maven 3.6+ или Maven Wrapper
- PostgreSQL, если нужен модуль с аккаунтами Spring Boot

### Запуск в Windows PowerShell

```powershell
.\mvnw.cmd spring-boot:run
```

### Запуск в Linux / macOS

```bash
./mvnw spring-boot:run
```

После запуска откройте:

- `http://localhost:8080/`
- `http://localhost:8080/home`
- `http://localhost:8080/login`
- `http://localhost:8080/register`
- `http://localhost:8080/profile`
- `http://localhost:8080/api/books`

## REST API

Base URL: `http://localhost:8080`

### Книги

| Метод | Endpoint | Описание |
|---|---|---|
| GET | `/api/books` | Получить список книг |
| GET | `/api/books/{id}` | Получить книгу по ID |
| POST | `/api/books` | Создать книгу |
| PUT | `/api/books/{id}` | Обновить книгу |
| DELETE | `/api/books/{id}` | Удалить книгу |

Пример тела запроса:

```json
{
  "title": "Новая книга",
  "isbn": "ISBN-12345",
  "authorId": 1,
  "libraryId": 1,
  "publishedYear": 2026
}
```

### Демонстрационные endpoint'ы

| Метод | Endpoint | Описание |
|---|---|---|
| GET | `/index` | Текстовый ответ `Hello World` |
| GET | `/index/specific` | JSON с тестовым студентом |
| POST | `/index/specific?name=...` | JSON-ответ с переданным именем |

## Веб-маршруты

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

## Структура проекта

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

## Примечания

- В репозитории одновременно находятся исходная servlet-реализация и обновлённая Spring Boot-версия.
- Часть экранов и логики дублируется намеренно, потому что проект развивался поэтапно.
- Файлы `boot-out.log` и `boot-err.log` — локальные логи запуска, не часть исходного кода.

## Лицензия

Учебный проект для дисциплины WAD.
