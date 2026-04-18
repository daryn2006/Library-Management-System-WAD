<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="kz.example.lms.model.Author" %>
<%@ page import="kz.example.lms.util.I18n" %>
<%
    Author editAuthor = (Author) request.getAttribute("editAuthor");
    boolean isEdit = editAuthor != null;
    List<Author> authors = (List<Author>) request.getAttribute("authors");
    boolean isAdmin = Boolean.TRUE.equals(request.getAttribute("isAdmin"));
    String searchQuery = (String) request.getAttribute("searchQuery");
%>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><%= I18n.t(request, "authors.title") %></title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>
<div class="page">
    <%@ include file="partials/header.jspf" %>
    <main class="container">
        <section class="hero hero--authors">
            <span class="eyebrow"><%= I18n.t(request, "authors.title") %></span>
            <h1><%= I18n.t(request, "authors.heroTitle") %></h1>
            <p class="lead"><%= I18n.t(request, "authors.lead") %></p>
            <div class="hero-kpis">
                <span class="chip"><%= I18n.t(request, "authors.total") %>: <%= authors != null ? authors.size() : 0 %></span>
            </div>
        </section>

        <section class="section">
            <div class="panel">
                <form method="get" action="<%= request.getContextPath() %>/authors" class="search-form">
                    <input type="text" name="q" value="<%= searchQuery == null ? "" : searchQuery %>" placeholder="Поиск по имени или стране">
                    <button class="button" type="submit">Найти</button>
                    <a class="button secondary" href="<%= request.getContextPath() %>/authors">Сброс</a>
                </form>
            </div>
        </section>

        <section class="section">
            <div class="panel">
                <% if (request.getAttribute("error") != null) { %>
                <div class="message"><%= request.getAttribute("error") %></div>
                <% } %>
                <% if (isAdmin) { %>
                <form method="post" action="<%= request.getContextPath() %>/authors">
                    <input type="hidden" name="action" value="<%= isEdit ? "update" : "create" %>">
                    <% if (isEdit) { %>
                    <input type="hidden" name="id" value="<%= editAuthor.getId() %>">
                    <% } %>
                    <div class="form-grid">
                        <label>
                            <span><%= I18n.t(request, "authors.fullName") %></span>
                            <input type="text" name="fullName" value="<%= isEdit ? editAuthor.getFullName() : "" %>" required>
                        </label>
                        <label>
                            <span><%= I18n.t(request, "authors.country") %></span>
                            <input type="text" name="country" value="<%= isEdit ? editAuthor.getCountry() : "" %>">
                        </label>
                    </div>
                    <div class="actions" style="margin-top: 16px;">
                        <button class="button" type="submit"><%= isEdit ? I18n.t(request, "common.save") : I18n.t(request, "common.add") %></button>
                        <% if (isEdit) { %>
                        <a class="button secondary" href="<%= request.getContextPath() %>/authors"><%= I18n.t(request, "common.cancel") %></a>
                        <% } %>
                    </div>
                </form>
                <% } %>
            </div>
        </section>

        <section class="section">
            <div class="panel">
                <table class="table">
                    <thead>
                    <tr>
                        <th><%= I18n.t(request, "common.id") %></th>
                        <th><%= I18n.t(request, "authors.fullName") %></th>
                        <th><%= I18n.t(request, "authors.country") %></th>
                        <% if (isAdmin) { %>
                        <th><%= I18n.t(request, "common.actions") %></th>
                        <% } %>
                    </tr>
                    </thead>
                    <tbody>
                    <% if (authors != null) {
                        for (Author author : authors) { %>
                    <tr>
                        <td><%= author.getId() %></td>
                        <td><%= author.getFullName() %></td>
                        <td><%= author.getCountry() == null ? "-" : author.getCountry() %></td>
                        <% if (isAdmin) { %>
                        <td>
                            <div class="actions">
                                <a class="button secondary" href="<%= request.getContextPath() %>/authors?editId=<%= author.getId() %>"><%= I18n.t(request, "common.edit") %></a>
                                <form method="post" action="<%= request.getContextPath() %>/authors">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="id" value="<%= author.getId() %>">
                                    <button class="button" type="submit"><%= I18n.t(request, "common.delete") %></button>
                                </form>
                            </div>
                        </td>
                        <% } %>
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
