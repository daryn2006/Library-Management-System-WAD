<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="kz.example.lms.model.Book" %>
<%@ page import="kz.example.lms.model.BookText" %>
<%@ page import="kz.example.lms.util.I18n" %>
<%
    Book book = (Book) request.getAttribute("book");
    BookText bookText = (BookText) request.getAttribute("bookText");
    boolean isAdmin = Boolean.TRUE.equals(request.getAttribute("isAdmin"));
%>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><%= I18n.t(request, "book.details") %></title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>
<div class="page">
    <%@ include file="partials/header.jspf" %>
    <main class="container">
        <section class="hero hero--books">
            <span class="eyebrow"><%= I18n.t(request, "books.title") %></span>
            <h1><%= book != null ? book.getTitle() : I18n.t(request, "book.untitled") %></h1>
            <p class="lead"><%= I18n.t(request, "book.detailLead") %></p>
        </section>

        <section class="section">
            <div class="panel">
                <div class="form-grid">
                    <label>
                        <span><%= I18n.t(request, "books.title") %></span>
                        <input type="text" value="<%= book != null ? book.getTitle() : "-" %>" readonly>
                    </label>
                    <label>
                        <span><%= I18n.t(request, "books.isbn") %></span>
                        <input type="text" value="<%= book != null ? book.getIsbn() : "-" %>" readonly>
                    </label>
                    <label>
                        <span><%= I18n.t(request, "nav.authors") %></span>
                        <input type="text" value="<%= book != null ? book.getAuthorName() : "-" %>" readonly>
                    </label>
                    <label>
                        <span><%= I18n.t(request, "nav.libraries") %></span>
                        <input type="text" value="<%= book != null ? book.getLibraryName() : "-" %>" readonly>
                    </label>
                    <label>
                        <span><%= I18n.t(request, "books.year") %></span>
                        <input type="text" value="<%= book != null && book.getPublishedYear() != null ? book.getPublishedYear() : "-" %>" readonly>
                    </label>
                </div>

                <div class="actions" style="margin-top: 16px;">
                    <a class="button" href="<%= request.getContextPath() %>/books"><%= I18n.t(request, "book.backToList") %></a>
                    <% if (isAdmin && book != null) { %>
                    <a class="button secondary" href="<%= request.getContextPath() %>/books?editId=<%= book.getId() %>"><%= I18n.t(request, "common.edit") %></a>
                    <% } %>
                    <% if (book != null && bookText != null) { %>
                    <a class="button secondary" href="<%= request.getContextPath() %>/download?id=<%= book.getId() %>">Скачать / открыть</a>
                    <a class="button secondary" href="<%= request.getContextPath() %>/read?id=<%= book.getId() %>"><%= I18n.t(request, "book.read") %></a>
                    <% } %>
                </div>
            </div>
        </section>

        <% if (bookText != null) { %>
        <section class="section">
            <div class="panel">
                <div class="meta">
                    <div><strong><%= I18n.t(request, "book.language") %>:</strong> <%= "kk".equals(bookText.getLanguage()) ? I18n.t(request, "common.kazakh") : I18n.t(request, "common.russian") %></div>
                    <div><strong><%= I18n.t(request, "book.description") %>:</strong> <%= bookText.getDescription() %></div>
                    <div><strong>Источник:</strong>
                        <% if (bookText.getSourceUrl() != null && !bookText.getSourceUrl().isBlank()) { %>
                        <a href="<%= bookText.getSourceUrl() %>" target="_blank" rel="noopener noreferrer">Внешняя ссылка</a>
                        <% } else { %>
                        <span>Файл в системе</span>
                        <% } %>
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
