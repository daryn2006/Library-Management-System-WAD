<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="kz.example.lms.model.Author" %>
<%
    Author editAuthor = (Author) request.getAttribute("editAuthor");
    boolean isEdit = editAuthor != null;
    List<Author> authors = (List<Author>) request.getAttribute("authors");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Авторы</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>
<div class="page">
    <%@ include file="partials/header.jspf" %>
    <main class="container">
        <section class="hero">
            <span class="eyebrow">Авторы</span>
            <h1>Управление авторами</h1>
            <p class="lead">Создание, редактирование и удаление авторов.</p>
        </section>

        <section class="section">
            <div class="panel">
                <% if (request.getAttribute("error") != null) { %>
                <div class="message"><%= request.getAttribute("error") %></div>
                <% } %>
                <form method="post" action="<%= request.getContextPath() %>/authors">
                    <input type="hidden" name="action" value="<%= isEdit ? "update" : "create" %>">
                    <% if (isEdit) { %>
                    <input type="hidden" name="id" value="<%= editAuthor.getId() %>">
                    <% } %>
                    <div class="form-grid">
                        <label>
                            <span>ФИО</span>
                            <input type="text" name="fullName" value="<%= isEdit ? editAuthor.getFullName() : "" %>" required>
                        </label>
                        <label>
                            <span>Страна</span>
                            <input type="text" name="country" value="<%= isEdit ? editAuthor.getCountry() : "" %>">
                        </label>
                    </div>
                    <div class="actions" style="margin-top: 16px;">
                        <button class="button" type="submit"><%= isEdit ? "Сохранить" : "Добавить" %></button>
                        <% if (isEdit) { %>
                        <a class="button secondary" href="<%= request.getContextPath() %>/authors">Отмена</a>
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
                        <th>ФИО</th>
                        <th>Страна</th>
                        <th>Действия</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% if (authors != null) {
                        for (Author author : authors) { %>
                    <tr>
                        <td><%= author.getId() %></td>
                        <td><%= author.getFullName() %></td>
                        <td><%= author.getCountry() == null ? "-" : author.getCountry() %></td>
                        <td>
                            <div class="actions">
                                <a class="button secondary" href="<%= request.getContextPath() %>/authors?editId=<%= author.getId() %>">Изменить</a>
                                <form method="post" action="<%= request.getContextPath() %>/authors">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="id" value="<%= author.getId() %>">
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
