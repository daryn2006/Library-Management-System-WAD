<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Главная</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>
<div class="page">
    <%@ include file="partials/header.jspf" %>
    <main class="container">
        <section class="hero">
            <span class="eyebrow">Главная панель</span>
            <h1>Обзор библиотеки</h1>
            <p class="lead">Краткая статистика и быстрые переходы.</p>
            <% if (request.getAttribute("error") != null) { %>
            <div class="message"><%= request.getAttribute("error") %></div>
            <% } %>
        </section>

        <section class="cards">
            <div class="card">
                <span class="tag">Авторы</span>
                <h3><%= request.getAttribute("authorCount") %> всего</h3>
                <p class="lead">Управление авторами и их данными.</p>
            </div>
            <div class="card">
                <span class="tag">Книги</span>
                <h3><%= request.getAttribute("bookCount") %> всего</h3>
                <p class="lead">Каталог книг по библиотекам.</p>
            </div>
            <div class="card">
                <span class="tag">Библиотеки</span>
                <h3><%= request.getAttribute("libraryCount") %> всего</h3>
                <p class="lead">Филиалы и адреса.</p>
            </div>
            <div class="card">
                <span class="tag">Читатели</span>
                <h3><%= request.getAttribute("memberCount") %> всего</h3>
                <p class="lead">Учет читателей.</p>
            </div>
        </section>

        <section class="section">
            <div class="panel">
                <h3>Быстрые действия</h3>
                <div class="actions">
                    <a class="button" href="<%= request.getContextPath() %>/books">Книги</a>
                    <a class="button secondary" href="<%= request.getContextPath() %>/authors">Авторы</a>
                    <a class="button secondary" href="<%= request.getContextPath() %>/libraries">Библиотеки</a>
                    <a class="button secondary" href="<%= request.getContextPath() %>/members">Читатели</a>
                </div>
            </div>
        </section>
    </main>
    <%@ include file="partials/footer.jspf" %>
</div>
</body>
</html>
