# 📚 Library Management System — WAD

Полнофункциональный учебный проект библиотечной системы с веб-интерфейсом (JSP/Servlet) и REST API на Spring Boot.

**Студент:** Сағади Дарын, ВТиПО-33

## ✨ Возможности

| Модуль | Функциональность |
|---|---|
| 🔐 Аутентификация | Регистрация, вход, выход, защита приватных страниц через `AuthFilter` |
| 🏠 Веб-интерфейс | Dashboard `/home`, страницы авторов, книг, библиотек, читателей |
| 📚 Каталог книг | CRUD для книг, карточка книги, чтение текста из ресурсов |
| 👤 Авторы | CRUD для авторов |
| 🏛 Библиотеки | CRUD для библиотек |
| 🧾 Читатели | CRUD для читателей |
| 🔌 REST API | CRUD API для книг (`/api/books`) + demo endpoint'ы |

## 🛠 Технологии

- Java 17+
- Spring Boot 3.4.0
- Jakarta Servlet / JSP / JSTL
- Maven
- In-memory хранилище (`Storage`) с тестовыми данными

## 🚀 Быстрый старт

### Минимальные требования

- Java JDK 17+
- Maven 3.6+ (или Maven Wrapper `mvnw`)

### Запуск

```bash
./mvnw spring-boot:run
```

Для Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

После запуска откройте:

- `http://localhost:8080/`
- `http://localhost:8080/api/books`

## 🔌 REST API

**Base URL:** `http://localhost:8080`

### 📘 Books API — `/api/books`

| Метод | URL | Описание |
|---|---|---|
| GET | `/api/books` | Получить список всех книг |
| GET | `/api/books/{id}` | Получить книгу по ID |
| POST | `/api/books` | Создать книгу |
| PUT | `/api/books/{id}` | Обновить книгу |
| DELETE | `/api/books/{id}` | Удалить книгу |

**Пример запроса (POST `/api/books`):**

```json
{
  "title": "Новая книга",
  "isbn": "ISBN-12345",
  "authorId": 1,
  "libraryId": 1,
  "publishedYear": 2026
}
```

### 🧪 Demo API

| Метод | URL | Описание |
|---|---|---|
| GET | `/index` | Текстовый ответ `Hello World` |
| GET | `/index/specific` | JSON с тестовым студентом |
| POST | `/index/specific?name=...` | JSON со студентом по переданному имени |

## 📝 Веб-маршруты

- `/login`, `/register`, `/logout`
- `/home`
- `/authors`
- `/books`
- `/libraries`
- `/members`
- `/book?id={id}`
- `/read?id={id}`
- `/settings`

## 📁 Структура проекта

```text
src/main/java/
├── kz/enu/vtrestapi/
│   ├── VtRestApiApplication.java
│   ├── controller/
│   │   ├── LibraryBookRestController.java
│   │   └── MyController.java
│   └── dto/
└── kz/example/lms/
    ├── servlet/
    ├── model/
    ├── store/Storage.java
    └── util/

src/main/resources/
├── META-INF/
└── books/

src/main/webapp/
├── WEB-INF/jsp/
└── style.css
```

## 📄 Лицензия

Учебный проект для дисциплины WAD.
