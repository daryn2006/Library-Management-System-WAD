<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="kz.example.lms.model.Library" %>
<%@ page import="kz.example.lms.util.I18n" %>
<%
    Library editLibrary = (Library) request.getAttribute("editLibrary");
    boolean isEdit = editLibrary != null;
    List<Library> libraries = (List<Library>) request.getAttribute("libraries");
    boolean isAdmin = Boolean.TRUE.equals(request.getAttribute("isAdmin"));
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><%= I18n.t(request, "libraries.title") %></title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>
<div class="page">
    <%@ include file="partials/header.jspf" %>
    <main class="container">
        <section class="hero hero--libraries">
            <span class="eyebrow"><%= I18n.t(request, "libraries.title") %></span>
            <h1><%= I18n.t(request, "libraries.heroTitle") %></h1>
            <p class="lead"><%= I18n.t(request, "libraries.lead") %></p>
            <div class="hero-kpis">
                <span class="chip"><%= I18n.t(request, "libraries.total") %>: <%= libraries != null ? libraries.size() : 0 %></span>
            </div>
        </section>

        <section class="section">
            <div class="entity-photo">
                <img src="https://images.unsplash.com/photo-1521587760476-6c12a4b040da?auto=format&fit=crop&w=1200&q=80" alt="Libraries photo">
            </div>
        </section>

        <section class="section">
            <div class="panel">
                <% if (request.getAttribute("error") != null) { %>
                <div class="message"><%= request.getAttribute("error") %></div>
                <% } %>
                <% if (isAdmin) { %>
                <form method="post" action="<%= request.getContextPath() %>/libraries">
                    <input type="hidden" name="action" value="<%= isEdit ? "update" : "create" %>">
                    <% if (isEdit) { %>
                    <input type="hidden" name="id" value="<%= editLibrary.getId() %>">
                    <% } %>
                    <div class="form-grid">
                        <label>
                            <span><%= I18n.t(request, "libraries.name") %></span>
                            <input type="text" name="name" value="<%= isEdit ? editLibrary.getName() : "" %>" required>
                        </label>
                        <label>
                            <span><%= I18n.t(request, "libraries.address") %></span>
                            <input type="text" name="address" value="<%= isEdit ? editLibrary.getAddress() : "" %>" required>
                        </label>
                    </div>
                    <div class="actions" style="margin-top: 16px;">
                        <button class="button" type="submit"><%= isEdit ? I18n.t(request, "common.save") : I18n.t(request, "common.add") %></button>
                        <% if (isEdit) { %>
                        <a class="button secondary" href="<%= request.getContextPath() %>/libraries"><%= I18n.t(request, "common.cancel") %></a>
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
                        <th><%= I18n.t(request, "libraries.name") %></th>
                        <th><%= I18n.t(request, "libraries.address") %></th>
                        <% if (isAdmin) { %>
                        <th><%= I18n.t(request, "common.actions") %></th>
                        <% } %>
                    </tr>
                    </thead>
                    <tbody>
                    <% if (libraries != null) {
                        for (Library library : libraries) { %>
                    <tr>
                        <td><%= library.getId() %></td>
                        <td><%= library.getName() %></td>
                        <td><%= library.getAddress() %></td>
                        <% if (isAdmin) { %>
                        <td>
                            <div class="actions">
                                <a class="button secondary" href="<%= request.getContextPath() %>/libraries?editId=<%= library.getId() %>"><%= I18n.t(request, "common.edit") %></a>
                                <form method="post" action="<%= request.getContextPath() %>/libraries">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="id" value="<%= library.getId() %>">
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
