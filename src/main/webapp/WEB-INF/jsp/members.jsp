<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="kz.example.lms.model.Member" %>
<%@ page import="kz.example.lms.model.Library" %>
<%@ page import="kz.example.lms.util.I18n" %>
<%
    Member editMember = (Member) request.getAttribute("editMember");
    boolean isEdit = editMember != null;
    List<Member> members = (List<Member>) request.getAttribute("members");
    List<Library> libraries = (List<Library>) request.getAttribute("libraries");
    boolean isAdmin = Boolean.TRUE.equals(request.getAttribute("isAdmin"));
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><%= I18n.t(request, "members.title") %></title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>
<div class="page">
    <%@ include file="partials/header.jspf" %>
    <main class="container">
        <section class="hero hero--members">
            <span class="eyebrow"><%= I18n.t(request, "members.title") %></span>
            <h1><%= I18n.t(request, "members.heroTitle") %></h1>
            <p class="lead"><%= I18n.t(request, "members.lead") %></p>
            <div class="hero-kpis">
                <span class="chip"><%= I18n.t(request, "members.total") %>: <%= members != null ? members.size() : 0 %></span>
            </div>
        </section>

        <section class="section">
            <div class="entity-photo">
                <img src="https://images.unsplash.com/photo-1529156069898-49953e39b3ac?auto=format&fit=crop&w=1200&q=80" alt="Readers photo">
            </div>
        </section>

        <section class="section">
            <div class="panel">
                <% if (request.getAttribute("error") != null) { %>
                <div class="message"><%= request.getAttribute("error") %></div>
                <% } %>
                <% if (isAdmin) { %>
                <form method="post" action="<%= request.getContextPath() %>/members">
                    <input type="hidden" name="action" value="<%= isEdit ? "update" : "create" %>">
                    <% if (isEdit) { %>
                    <input type="hidden" name="id" value="<%= editMember.getId() %>">
                    <% } %>
                    <div class="form-grid">
                        <label>
                            <span><%= I18n.t(request, "members.fullName") %></span>
                            <input type="text" name="fullName" value="<%= isEdit ? editMember.getFullName() : "" %>" required>
                        </label>
                        <label>
                            <span><%= I18n.t(request, "common.email") %></span>
                            <input type="email" name="email" value="<%= isEdit ? editMember.getEmail() : "" %>" required>
                        </label>
                        <label>
                            <span><%= I18n.t(request, "members.phone") %></span>
                            <input type="text" name="phone" value="<%= isEdit ? editMember.getPhone() : "" %>">
                        </label>
                        <label>
                            <span><%= I18n.t(request, "nav.libraries") %></span>
                            <select name="libraryId" required>
                                <% if (libraries != null) {
                                    for (Library library : libraries) {
                                        boolean selected = isEdit && library.getId() == editMember.getLibraryId(); %>
                                <option value="<%= library.getId() %>" <%= selected ? "selected" : "" %>><%= library.getName() %></option>
                                <% } } %>
                            </select>
                        </label>
                    </div>
                    <div class="actions" style="margin-top: 16px;">
                        <button class="button" type="submit"><%= isEdit ? I18n.t(request, "common.save") : I18n.t(request, "common.add") %></button>
                        <% if (isEdit) { %>
                        <a class="button secondary" href="<%= request.getContextPath() %>/members"><%= I18n.t(request, "common.cancel") %></a>
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
                        <th>№</th>
                        <th><%= I18n.t(request, "common.id") %></th>
                        <th><%= I18n.t(request, "members.fullName") %></th>
                        <th><%= I18n.t(request, "common.email") %></th>
                        <th><%= I18n.t(request, "members.phone") %></th>
                        <th><%= I18n.t(request, "nav.libraries") %></th>
                        <% if (isAdmin) { %>
                        <th><%= I18n.t(request, "common.actions") %></th>
                        <% } %>
                    </tr>
                    </thead>
                    <tbody>
                    <% if (members != null) {
                        int rowNumber = 1;
                        for (Member member : members) { %>
                    <tr>
                        <td><%= rowNumber++ %></td>
                        <td><%= member.getId() %></td>
                        <td><%= member.getFullName() %></td>
                        <td><%= member.getEmail() %></td>
                        <td><%= member.getPhone() == null ? "-" : member.getPhone() %></td>
                        <td><%= member.getLibraryName() %></td>
                        <% if (isAdmin) { %>
                        <td>
                            <div class="actions">
                                <a class="button secondary" href="<%= request.getContextPath() %>/members?editId=<%= member.getId() %>"><%= I18n.t(request, "common.edit") %></a>
                                <form method="post" action="<%= request.getContextPath() %>/members">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="id" value="<%= member.getId() %>">
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
