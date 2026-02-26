<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Вход</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>
<div class="page">
    <nav class="nav">
        <div class="brand">Библиотечная система</div>
        <div class="nav-links">
            <a href="<%= request.getContextPath() %>/">Главная</a>
            <a href="<%= request.getContextPath() %>/register">Регистрация</a>
        </div>
    </nav>
    <main class="container">
        <section class="hero">
            <span class="eyebrow">С возвращением</span>
            <h1>Вход</h1>
            <p class="lead">Войдите, чтобы управлять библиотекой.</p>
        </section>

        <section class="section">
            <div class="panel">
                <% if (request.getAttribute("error") != null) { %>
                <div class="message"><%= request.getAttribute("error") %></div>
                <% } %>
                <form method="post" action="<%= request.getContextPath() %>/login">
                    <div class="form-grid">
                        <label>
                            <span>Email</span>
                            <input type="email" name="email" required>
                        </label>
                        <label>
                            <span>Пароль</span>
                            <input type="password" name="password" required>
                        </label>
                    </div>
                    <div class="actions" style="margin-top: 16px;">
                        <button class="button" type="submit">Войти</button>
                        <a class="button secondary" href="<%= request.getContextPath() %>/register">Создать аккаунт</a>
                    </div>
                </form>
            </div>
        </section>
    </main>
    <%@ include file="partials/footer.jspf" %>
</div>
</body>
</html>
