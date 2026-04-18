<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="kz.example.lms.model.Book" %>
<%@ page import="kz.example.lms.model.BookText" %>
<%@ page import="kz.example.lms.util.I18n" %>
<%
    Book book = (Book) request.getAttribute("book");
    BookText bookText = (BookText) request.getAttribute("bookText");
    String content = (String) request.getAttribute("content");
%>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><%= I18n.t(request, "read.room") %></title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>
<div class="page">
    <%@ include file="partials/header.jspf" %>
    <main class="container">
        <section class="hero hero--books">
            <span class="eyebrow"><%= I18n.t(request, "read.readBook") %></span>
            <h1><%= book != null ? book.getTitle() : I18n.t(request, "books.title") %></h1>
            <p class="lead"><%= book != null ? book.getAuthorName() : "" %></p>
        </section>

        <section class="section">
            <div class="panel">
                <div class="meta">
                    <div><strong><%= I18n.t(request, "book.language") %>:</strong> <%= bookText != null && "kk".equals(bookText.getLanguage()) ? I18n.t(request, "common.kazakh") : I18n.t(request, "common.russian") %></div>
                    <div><strong><%= I18n.t(request, "book.description") %>:</strong> <%= bookText != null ? bookText.getDescription() : "-" %></div>
                </div>
                <div class="reader">
                    <pre><%= content != null ? content : "" %></pre>
                </div>
                <div class="actions" style="margin-top:16px;">
                    <a class="button" href="<%= request.getContextPath() %>/book?id=<%= book != null ? book.getId() : "" %>"><%= I18n.t(request, "common.back") %></a>
                    <% if (book != null) { %>
                    <a class="button secondary" href="<%= request.getContextPath() %>/download?id=<%= book.getId() %>">Скачать файл</a>
                    <% } %>
                </div>
            </div>
        </section>
    </main>
    <%@ include file="partials/footer.jspf" %>
</div>
</body>
</html>
