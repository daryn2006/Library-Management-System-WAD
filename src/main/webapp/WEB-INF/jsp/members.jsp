<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="kz.example.lms.model.Member" %>
<%@ page import="kz.example.lms.model.Library" %>
<%
    Member editMember = (Member) request.getAttribute("editMember");
    boolean isEdit = editMember != null;
    List<Member> members = (List<Member>) request.getAttribute("members");
    List<Library> libraries = (List<Library>) request.getAttribute("libraries");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Читатели</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>
<div class="page">
    <%@ include file="partials/header.jspf" %>
    <main class="container">
        <section class="hero">
            <span class="eyebrow">Читатели</span>
            <h1>Реестр читателей</h1>
            <p class="lead">Добавляйте, редактируйте и удаляйте читателей.</p>
        </section>

        <section class="section">
            <div class="panel">
                <% if (request.getAttribute("error") != null) { %>
                <div class="message"><%= request.getAttribute("error") %></div>
                <% } %>
                <form method="post" action="<%= request.getContextPath() %>/members">
                    <input type="hidden" name="action" value="<%= isEdit ? "update" : "create" %>">
                    <% if (isEdit) { %>
                    <input type="hidden" name="id" value="<%= editMember.getId() %>">
                    <% } %>
                    <div class="form-grid">
                        <label>
                            <span>ФИО</span>
                            <input type="text" name="fullName" value="<%= isEdit ? editMember.getFullName() : "" %>" required>
                        </label>
                        <label>
                            <span>Email</span>
                            <input type="email" name="email" value="<%= isEdit ? editMember.getEmail() : "" %>" required>
                        </label>
                        <label>
                            <span>Телефон</span>
                            <input type="text" name="phone" value="<%= isEdit ? editMember.getPhone() : "" %>">
                        </label>
                        <label>
                            <span>Библиотека</span>
                            <select name="libraryId" required>
                                <% if (libraries != null) {
                                    for (Library library : libraries) {
                                        boolean selected = isEdit && library.getId() == editMember.getLibraryId();
                                %>
                                <option value="<%= library.getId() %>" <%= selected ? "selected" : "" %>>
                                    <%= library.getName() %>
                                </option>
                                <% } } %>
                            </select>
                        </label>
                    </div>
                    <div class="actions" style="margin-top: 16px;">
                        <button class="button" type="submit"><%= isEdit ? "Сохранить" : "Добавить" %></button>
                        <% if (isEdit) { %>
                        <a class="button secondary" href="<%= request.getContextPath() %>/members">Отмена</a>
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
                        <th>Email</th>
                        <th>Телефон</th>
                        <th>Библиотека</th>
                        <th>Действия</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% if (members != null) {
                        for (Member member : members) { %>
                    <tr>
                        <td><%= member.getId() %></td>
                        <td><%= member.getFullName() %></td>
                        <td><%= member.getEmail() %></td>
                        <td><%= member.getPhone() == null ? "-" : member.getPhone() %></td>
                        <td><%= member.getLibraryName() %></td>
                        <td>
                            <div class="actions">
                                <a class="button secondary" href="<%= request.getContextPath() %>/members?editId=<%= member.getId() %>">Изменить</a>
                                <form method="post" action="<%= request.getContextPath() %>/members">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="id" value="<%= member.getId() %>">
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
