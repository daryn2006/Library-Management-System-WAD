<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Библиотечная система</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="page">
    <nav class="nav">
        <div class="brand">Библиотечная система</div>
        <div class="nav-links">
            <a href="login">Вход</a>
            <a href="register">Регистрация</a>
        </div>
    </nav>
    <main class="container">
        <section class="hero">
            <span class="eyebrow">Библиотечная система</span>
            <h1>Управление библиотекой в одном месте.</h1>
            <p class="lead">Регистрация пользователей, книги, авторы, библиотеки и читатели.</p>
            <div class="actions">
                <a class="button" href="register">Создать аккаунт</a>
                <a class="button secondary" href="login">Войти</a>
            </div>
        </section>
        <section class="cards">
            <div class="card">
                <span class="tag">Книги</span>
                <h3>Каталог</h3>
                <p class="lead">Добавляйте, редактируйте и удаляйте книги.</p>
            </div>
            <div class="card">
                <span class="tag">Читатели</span>
                <h3>Реестр читателей</h3>
                <p class="lead">Регистрация и привязка к библиотекам.</p>
            </div>
            <div class="card">
                <span class="tag">Библиотеки</span>
                <h3>Филиалы</h3>
                <p class="lead">Адреса и управление филиалами.</p>
            </div>
        </section>
    </main>
    <footer class="footer">
        Библиотечная система. Jakarta Servlets.
    </footer>
</div>
</body>
</html>
