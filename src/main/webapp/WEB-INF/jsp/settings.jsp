<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="kz.example.lms.model.User" %>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Настройки</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>
<div class="page">
    <%@ include file="partials/header.jspf" %>
    <main class="container">
        <section class="hero">
            <span class="eyebrow">Настройки</span>
            <h1>Профиль пользователя</h1>
            <p class="lead">Основная информация вашей учетной записи.</p>
        </section>

        <section class="section">
            <div class="panel">
                <div class="form-grid">
                    <label>
                        <span>ФИО</span>
                        <input type="text" value="<%= user != null ? user.getFullName() : "-" %>" readonly>
                    </label>
                    <label>
                        <span>Email</span>
                        <input type="text" value="<%= user != null ? user.getEmail() : "-" %>" readonly>
                    </label>
                    <label>
                        <span>Роль</span>
                        <input type="text" value="<%= user != null ? user.getRole() : "-" %>" readonly>
                    </label>
                </div>
                <div class="actions" style="margin-top: 16px;">
                    <a class="button secondary" href="<%= request.getContextPath() %>/home">Назад</a>
                </div>
            </div>
        </section>
    </main>
    <%@ include file="partials/footer.jspf" %>
</div>
</body>
</html>
