<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="kz.example.lms.util.I18n" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><%= I18n.t(request, "nav.home") %></title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>
<div class="page">
    <%@ include file="partials/header.jspf" %>
    <main class="container">
        <section class="hero hero--home">
            <span class="eyebrow"><%= I18n.t(request, "home.mainPanel") %></span>
            <h1><%= I18n.t(request, "home.title") %></h1>
            <p class="lead"><%= I18n.t(request, "home.lead") %></p>
            <% if (request.getAttribute("error") != null) { %>
            <div class="message"><%= request.getAttribute("error") %></div>
            <% } %>
        </section>

        <section class="cards">
            <div class="card">
                <span class="tag"><%= I18n.t(request, "nav.authors") %></span>
                <h3><%= request.getAttribute("authorCount") %> <%= I18n.t(request, "common.total") %></h3>
                <p class="lead"><%= I18n.t(request, "home.authorsDesc") %></p>
            </div>
            <div class="card">
                <span class="tag"><%= I18n.t(request, "nav.books") %></span>
                <h3><%= request.getAttribute("bookCount") %> <%= I18n.t(request, "common.total") %></h3>
                <p class="lead"><%= I18n.t(request, "home.booksDesc") %></p>
            </div>
            <div class="card">
                <span class="tag"><%= I18n.t(request, "nav.libraries") %></span>
                <h3><%= request.getAttribute("libraryCount") %> <%= I18n.t(request, "common.total") %></h3>
                <p class="lead"><%= I18n.t(request, "home.librariesDesc") %></p>
            </div>
            <div class="card">
                <span class="tag"><%= I18n.t(request, "nav.readers") %></span>
                <h3><%= request.getAttribute("memberCount") %> <%= I18n.t(request, "common.total") %></h3>
                <p class="lead"><%= I18n.t(request, "home.readersDesc") %></p>
            </div>
        </section>

        <section class="book-gallery">
            <a class="gallery-card gallery-link" href="<%= request.getContextPath() %>/books">
                <img src="https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?auto=format&fit=crop&w=900&q=80" alt="Bookshelf">
                <div class="overlay"><%= I18n.t(request, "home.classicBooks") %></div>
            </a>
            <a class="gallery-card gallery-link" href="<%= request.getContextPath() %>/authors">
                <img src="https://images.unsplash.com/photo-1481627834876-b7833e8f5570?auto=format&fit=crop&w=900&q=80" alt="Reading desk">
                <div class="overlay"><%= I18n.t(request, "home.readingMood") %></div>
            </a>
            <a class="gallery-card gallery-link" href="<%= request.getContextPath() %>/libraries">
                <img src="https://images.unsplash.com/photo-1512820790803-83ca734da794?auto=format&fit=crop&w=900&q=80" alt="Open book">
                <div class="overlay"><%= I18n.t(request, "home.story") %></div>
            </a>
        </section>

        <section class="section">
            <div class="panel">
                <h3><%= I18n.t(request, "home.quickActions") %></h3>
                <div class="actions">
                    <a class="button" href="<%= request.getContextPath() %>/books"><%= I18n.t(request, "nav.books") %></a>
                    <a class="button secondary" href="<%= request.getContextPath() %>/authors"><%= I18n.t(request, "nav.authors") %></a>
                    <a class="button secondary" href="<%= request.getContextPath() %>/libraries"><%= I18n.t(request, "nav.libraries") %></a>
                    <a class="button secondary" href="<%= request.getContextPath() %>/members"><%= I18n.t(request, "nav.readers") %></a>
                </div>
            </div>
        </section>
    </main>
    <%@ include file="partials/footer.jspf" %>
</div>
</body>
</html>
