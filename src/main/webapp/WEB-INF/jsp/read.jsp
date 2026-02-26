<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="kz.example.lms.model.Book" %>
<%@ page import="kz.example.lms.model.BookText" %>
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
    <title>Оқу бөлімі</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>
<div class="page">
    <%@ include file="partials/header.jspf" %>
    <main class="container">
        <section class="hero">
            <span class="eyebrow">Кітап оқу</span>
            <h1><%= book != null ? book.getTitle() : "Кітап" %></h1>
            <p class="lead"><%= book != null ? book.getAuthorName() : "" %></p>
        </section>

        <section class="section">
            <div class="panel">
                <div class="meta">
                    <div>
                        <strong>Язык:</strong> <%= bookText != null && "kk".equals(bookText.getLanguage()) ? "қазақша" : "русский" %>
                    </div>
                    <div>
                        <strong>Қысқаша сипаттама:</strong> <%= bookText != null ? bookText.getDescription() : "-" %>
                    </div>
                </div>
                <div class="reader">
                    <pre><%= content != null ? content : "" %></pre>
                </div>
                <div class="actions" style="margin-top: 16px;">
                    <a class="button" href="<%= request.getContextPath() %>/book?id=<%= book != null ? book.getId() : "" %>">Назад</a>
                </div>
            </div>
        </section>
    </main>
    <%@ include file="partials/footer.jspf" %>
</div>
</body>
</html>
