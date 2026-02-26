<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Регистрация</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>
<div class="page">
    <nav class="nav">
        <div class="brand">Библиотечная система</div>
        <div class="nav-links">
            <a href="<%= request.getContextPath() %>/">Главная</a>
            <a href="<%= request.getContextPath() %>/login">Вход</a>
        </div>
    </nav>
    <main class="container">
        <section class="hero">
            <span class="eyebrow">Новый аккаунт</span>
            <h1>Создание профиля</h1>
            <p class="lead">Регистрация для доступа к управлению библиотекой.</p>
        </section>

        <section class="section">
            <div class="panel">
                <% if (request.getAttribute("error") != null) { %>
                <div class="message"><%= request.getAttribute("error") %></div>
                <% } %>
                <form method="post" action="<%= request.getContextPath() %>/register">
                    <div class="form-grid">
                        <label>
                            <span>ФИО</span>
                            <input type="text" name="fullName" required>
                        </label>
                        <label>
                            <span>Email</span>
                            <input type="email" name="email" required>
                        </label>
                        <label>
                            <span>Пароль</span>
                            <input type="password" name="password" required>
                        </label>
                        <label>
                            <span>Роль</span>
                            <select name="role" required>
                                <option value="ADMIN">Администратор</option>
                                <option value="MEMBER">Читатель</option>
                            </select>
                        </label>
                    </div>
                    <div class="actions" style="margin-top: 16px;">
                        <button class="button" type="submit">Создать аккаунт</button>
                        <a class="button secondary" href="<%= request.getContextPath() %>/login">Войти</a>
                    </div>
                </form>
            </div>
        </section>
    </main>
    <%@ include file="partials/footer.jspf" %>
</div>
</body>
</html>
