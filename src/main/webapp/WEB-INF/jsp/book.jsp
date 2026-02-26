<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="kz.example.lms.model.Book" %>
<%@ page import="kz.example.lms.model.BookText" %>
<%
    Book book = (Book) request.getAttribute("book");
    BookText bookText = (BookText) request.getAttribute("bookText");
%>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Карточка книги</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>
<div class="page">
    <%@ include file="partials/header.jspf" %>
    <main class="container">
        <section class="hero">
            <span class="eyebrow">Книга</span>
            <h1><%= book != null ? book.getTitle() : "Без названия" %></h1>
            <p class="lead">Подробная информация о книге.</p>
        </section>

        <section class="section">
            <div class="panel">
                <div class="form-grid">
                    <label>
                        <span>Название</span>
                        <input type="text" value="<%= book != null ? book.getTitle() : "-" %>" readonly>
                    </label>
                    <label>
                        <span>ISBN</span>
                        <input type="text" value="<%= book != null ? book.getIsbn() : "-" %>" readonly>
                    </label>
                    <label>
                        <span>Автор</span>
                        <input type="text" value="<%= book != null ? book.getAuthorName() : "-" %>" readonly>
                    </label>
                    <label>
                        <span>Библиотека</span>
                        <input type="text" value="<%= book != null ? book.getLibraryName() : "-" %>" readonly>
                    </label>
                    <label>
                        <span>Год издания</span>
                        <input type="text" value="<%= book != null && book.getPublishedYear() != null ? book.getPublishedYear() : "-" %>" readonly>
                    </label>
                </div>
                <div class="actions" style="margin-top: 16px;">
                    <a class="button" href="<%= request.getContextPath() %>/books">Назад к списку</a>
                    <a class="button secondary" href="<%= request.getContextPath() %>/books?editId=<%= book != null ? book.getId() : "" %>">Редактировать</a>
                    <% if (bookText != null) { %>
                    <a class="button secondary" href="<%= request.getContextPath() %>/read?id=<%= book.getId() %>">Читать</a>
                    <% } %>
                </div>
            </div>
        </section>

        <% if (bookText != null) { %>
        <section class="section">
            <div class="panel">
                <div class="meta">
                    <div>
                        <strong>Язык:</strong> <%= "kk".equals(bookText.getLanguage()) ? "қазақша" : "русский" %>
                    </div>
                    <div>
                        <strong>Қысқаша сипаттама:</strong> <%= bookText.getDescription() %>
                    </div>
                </div>
            </div>
        </section>
        <% } %>
    </main>
    <%@ include file="partials/footer.jspf" %>
</div>
</body>
</html>
