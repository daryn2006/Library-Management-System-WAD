<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="kz.example.lms.model.Book" %>
<%@ page import="kz.example.lms.model.Author" %>
<%@ page import="kz.example.lms.model.Library" %>
<%
    Book editBook = (Book) request.getAttribute("editBook");
    boolean isEdit = editBook != null;
    List<Book> books = (List<Book>) request.getAttribute("books");
    List<Author> authors = (List<Author>) request.getAttribute("authors");
    List<Library> libraries = (List<Library>) request.getAttribute("libraries");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Книги</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>
<div class="page">
    <%@ include file="partials/header.jspf" %>
    <main class="container">
        <section class="hero">
            <span class="eyebrow">Книги</span>
            <h1>Каталог книг</h1>
            <p class="lead">Назначайте авторов и библиотеки для книг.</p>
        </section>

        <section class="section">
            <div class="panel">
                <% if (request.getAttribute("error") != null) { %>
                <div class="message"><%= request.getAttribute("error") %></div>
                <% } %>
                <form method="post" action="<%= request.getContextPath() %>/books">
                    <input type="hidden" name="action" value="<%= isEdit ? "update" : "create" %>">
                    <% if (isEdit) { %>
                    <input type="hidden" name="id" value="<%= editBook.getId() %>">
                    <% } %>
                    <div class="form-grid">
                        <label>
                            <span>Название</span>
                            <input type="text" name="title" value="<%= isEdit ? editBook.getTitle() : "" %>" required>
                        </label>
                        <label>
                            <span>ISBN</span>
                            <input type="text" name="isbn" value="<%= isEdit ? editBook.getIsbn() : "" %>" required>
                        </label>
                        <label>
                            <span>Автор</span>
                            <select name="authorId" required>
                                <% if (authors != null) {
                                    for (Author author : authors) {
                                        boolean selected = isEdit && author.getId() == editBook.getAuthorId();
                                %>
                                <option value="<%= author.getId() %>" <%= selected ? "selected" : "" %>>
                                    <%= author.getFullName() %>
                                </option>
                                <% } } %>
                            </select>
                        </label>
                        <label>
                            <span>Библиотека</span>
                            <select name="libraryId" required>
                                <% if (libraries != null) {
                                    for (Library library : libraries) {
                                        boolean selected = isEdit && library.getId() == editBook.getLibraryId();
                                %>
                                <option value="<%= library.getId() %>" <%= selected ? "selected" : "" %>>
                                    <%= library.getName() %>
                                </option>
                                <% } } %>
                            </select>
                        </label>
                        <label>
                            <span>Год издания</span>
                            <input type="number" name="publishedYear" value="<%= isEdit && editBook.getPublishedYear() != null ? editBook.getPublishedYear() : "" %>">
                        </label>
                    </div>
                    <div class="actions" style="margin-top: 16px;">
                        <button class="button" type="submit"><%= isEdit ? "Сохранить" : "Добавить" %></button>
                        <% if (isEdit) { %>
                        <a class="button secondary" href="<%= request.getContextPath() %>/books">Отмена</a>
                        <% } %>
                    </div>
                </form>
            </div>
        </section>

        <section class="section">
            <div class="panel">
                <table class="table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Название</th>
                        <th>ISBN</th>
                        <th>Автор</th>
                        <th>Библиотека</th>
                        <th>Год</th>
                        <th>Действия</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% if (books != null) {
                        for (Book book : books) { %>
                    <tr>
                        <td><%= book.getId() %></td>
                        <td>
                            <a href="<%= request.getContextPath() %>/book?id=<%= book.getId() %>">
                                <%= book.getTitle() %>
                            </a>
                        </td>
                        <td><%= book.getIsbn() %></td>
                        <td><%= book.getAuthorName() %></td>
                        <td><%= book.getLibraryName() %></td>
                        <td><%= book.getPublishedYear() == null ? "-" : book.getPublishedYear() %></td>
                        <td>
                            <div class="actions">
                                <a class="button secondary" href="<%= request.getContextPath() %>/books?editId=<%= book.getId() %>">Изменить</a>
                                <a class="button secondary" href="<%= request.getContextPath() %>/book?id=<%= book.getId() %>">Открыть</a>
                                <form method="post" action="<%= request.getContextPath() %>/books">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="id" value="<%= book.getId() %>">
                                    <button class="button" type="submit">Удалить</button>
                                </form>
                            </div>
                        </td>
                    </tr>
                    <% } } %>
                    </tbody>
                </table>
            </div>
        </section>
    </main>
    <%@ include file="partials/footer.jspf" %>
</div>
</body>
</html>
