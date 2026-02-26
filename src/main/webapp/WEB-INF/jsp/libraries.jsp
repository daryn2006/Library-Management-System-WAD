<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="kz.example.lms.model.Library" %>
<%
    Library editLibrary = (Library) request.getAttribute("editLibrary");
    boolean isEdit = editLibrary != null;
    List<Library> libraries = (List<Library>) request.getAttribute("libraries");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Библиотеки</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>
<div class="page">
    <%@ include file="partials/header.jspf" %>
    <main class="container">
        <section class="hero">
            <span class="eyebrow">Библиотеки</span>
            <h1>Управление филиалами</h1>
            <p class="lead">Создание и редактирование библиотек.</p>
        </section>

        <section class="section">
            <div class="panel">
                <% if (request.getAttribute("error") != null) { %>
                <div class="message"><%= request.getAttribute("error") %></div>
                <% } %>
                <form method="post" action="<%= request.getContextPath() %>/libraries">
                    <input type="hidden" name="action" value="<%= isEdit ? "update" : "create" %>">
                    <% if (isEdit) { %>
                    <input type="hidden" name="id" value="<%= editLibrary.getId() %>">
                    <% } %>
                    <div class="form-grid">
                        <label>
                            <span>Название</span>
                            <input type="text" name="name" value="<%= isEdit ? editLibrary.getName() : "" %>" required>
                        </label>
                        <label>
                            <span>Адрес</span>
                            <input type="text" name="address" value="<%= isEdit ? editLibrary.getAddress() : "" %>" required>
                        </label>
                    </div>
                    <div class="actions" style="margin-top: 16px;">
                        <button class="button" type="submit"><%= isEdit ? "Сохранить" : "Добавить" %></button>
                        <% if (isEdit) { %>
                        <a class="button secondary" href="<%= request.getContextPath() %>/libraries">Отмена</a>
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
                        <th>Адрес</th>
                        <th>Действия</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% if (libraries != null) {
                        for (Library library : libraries) { %>
                    <tr>
                        <td><%= library.getId() %></td>
                        <td><%= library.getName() %></td>
                        <td><%= library.getAddress() %></td>
                        <td>
                            <div class="actions">
                                <a class="button secondary" href="<%= request.getContextPath() %>/libraries?editId=<%= library.getId() %>">Изменить</a>
                                <form method="post" action="<%= request.getContextPath() %>/libraries">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="id" value="<%= library.getId() %>">
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
