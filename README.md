# Library-Management-System-WAD

Учебный проект системы управления библиотекой на Java (Spring Boot + Servlet/JSP).

## Что реализовано в проекте

- Аутентификация и авторизация:
- регистрация пользователя (`/register`);
- вход в систему (`/login`);
- выход (`/logout`);
- защита приватных страниц через `AuthFilter`.

- Веб-модуль (JSP/Servlet):
- главная панель со статистикой (`/home`);
- CRUD для авторов (`/authors`);
- CRUD для книг (`/books`);
- CRUD для библиотек (`/libraries`);
- CRUD для читателей (`/members`);
- карточка книги (`/book?id=...`);
- чтение текста книги из ресурсов (`/read?id=...`);
- страница настроек (`/settings`).

- REST API:
- `GET /api/books` - получить список книг;
- `GET /api/books/{id}` - получить книгу по ID;
- `POST /api/books` - создать книгу;
- `PUT /api/books/{id}` - обновить книгу;
- `DELETE /api/books/{id}` - удалить книгу.

- Дополнительные REST endpoint'ы (демо):
- `GET /index` - простой текстовый ответ;
- `GET /index/specific` - JSON с тестовым студентом;
- `POST /index/specific?name=...` - JSON со студентом по имени.

## Пример JSON для REST API книг

```json
{
  "title": "Новая книга",
  "isbn": "ISBN-12345",
  "authorId": 1,
  "libraryId": 1,
  "publishedYear": 2026
}
```

## Технологии

- Java 17
- Spring Boot 3.4.0
- Jakarta Servlet / JSP / JSTL
- Maven

## Запуск проекта

1. Установить Java 17+ и Maven (или использовать `mvnw` из проекта).
2. В корне проекта выполнить:

```bash
./mvnw spring-boot:run
```

Для Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```
