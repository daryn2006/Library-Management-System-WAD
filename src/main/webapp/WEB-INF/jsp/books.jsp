<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="kz.example.lms.model.Book" %>
<%@ page import="kz.example.lms.model.Author" %>
<%@ page import="kz.example.lms.model.Library" %>
<%@ page import="kz.example.lms.util.I18n" %>
<%
    Book editBook = (Book) request.getAttribute("editBook");
    boolean isEdit = editBook != null;
    List<Book> books = (List<Book>) request.getAttribute("books");
    List<Author> authors = (List<Author>) request.getAttribute("authors");
    List<Library> libraries = (List<Library>) request.getAttribute("libraries");
    Map<String, Integer> categories = (Map<String, Integer>) request.getAttribute("categories");
    String searchQuery = (String) request.getAttribute("searchQuery");
    String selectedGenre = (String) request.getAttribute("selectedGenre");
    int coverIndex = 0;
    boolean isAdmin = Boolean.TRUE.equals(request.getAttribute("isAdmin"));
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><%= I18n.t(request, "books.title") %></title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>
<div class="page">
    <%@ include file="partials/header.jspf" %>
    <main class="container">
        <section class="hero hero--books">
            <span class="eyebrow"><%= I18n.t(request, "books.title") %></span>
            <h1><%= I18n.t(request, "books.heroTitle") %></h1>
            <p class="lead"><%= I18n.t(request, "books.lead") %></p>
        </section>

        <section class="section">
            <div class="panel search-panel">
                <form method="get" action="<%= request.getContextPath() %>/books" class="search-form">
                    <input type="text" name="q" value="<%= searchQuery == null ? "" : searchQuery %>" placeholder="Книга или автор">
                    <select name="genre">
                        <option value="">Все категории</option>
                        <% if (categories != null) {
                            for (String genre : categories.keySet()) {
                                boolean selected = genre.equals(selectedGenre); %>
                        <option value="<%= genre %>" <%= selected ? "selected" : "" %>><%= genre %></option>
                        <% } } %>
                    </select>
                    <button class="button" type="submit">Поиск</button>
                    <a class="button secondary" href="<%= request.getContextPath() %>/books">Сброс</a>
                </form>
                <div class="category-list">
                    <% if (categories != null) {
                        for (Map.Entry<String, Integer> entry : categories.entrySet()) { %>
                    <span class="chip"><%= entry.getKey() %> (<%= entry.getValue() %>)</span>
                    <% } } %>
                </div>
            </div>
        </section>

        <section class="shelf-strip">
            <img src="https://images.unsplash.com/photo-1521587760476-6c12a4b040da?auto=format&fit=crop&w=500&q=80" alt="Library hall">
            <img src="https://images.unsplash.com/photo-1495446815901-a7297e633e8d?auto=format&fit=crop&w=500&q=80" alt="Bookshelf rows">
            <img src="https://images.unsplash.com/photo-1481627834876-b7833e8f5570?auto=format&fit=crop&w=500&q=80" alt="Reading desk">
            <img src="https://images.unsplash.com/photo-1512820790803-83ca734da794?auto=format&fit=crop&w=500&q=80" alt="Open book">
        </section>

        <section class="section">
            <div class="panel">
                <% if (request.getAttribute("error") != null) { %>
                <div class="message"><%= request.getAttribute("error") %></div>
                <% } %>
                <% if (isAdmin) { %>
                <form method="post" action="<%= request.getContextPath() %>/books">
                    <input type="hidden" name="action" value="<%= isEdit ? "update" : "create" %>">
                    <% if (isEdit) { %>
                    <input type="hidden" name="id" value="<%= editBook.getId() %>">
                    <% } %>
                    <div class="form-grid">
                        <label>
                            <span><%= I18n.t(request, "books.title") %></span>
                            <input type="text" name="title" value="<%= isEdit ? editBook.getTitle() : "" %>" required>
                        </label>
                        <label>
                            <span>Жанр / Категория</span>
                            <input type="text" name="genre" value="<%= isEdit ? editBook.getGenre() : "" %>" required>
                        </label>
                        <label>
                            <span><%= I18n.t(request, "books.isbn") %></span>
                            <input type="text" name="isbn" value="<%= isEdit ? editBook.getIsbn() : "" %>" required>
                        </label>
                        <label>
                            <span><%= I18n.t(request, "nav.authors") %></span>
                            <select name="authorId" required>
                                <% if (authors != null) {
                                    for (Author author : authors) {
                                        boolean selected = isEdit && author.getId() == editBook.getAuthorId(); %>
                                <option value="<%= author.getId() %>" <%= selected ? "selected" : "" %>><%= author.getFullName() %></option>
                                <% } } %>
                            </select>
                        </label>
                        <label>
                            <span><%= I18n.t(request, "nav.libraries") %></span>
                            <select name="libraryId" required>
                                <% if (libraries != null) {
                                    for (Library library : libraries) {
                                        boolean selected = isEdit && library.getId() == editBook.getLibraryId(); %>
                                <option value="<%= library.getId() %>" <%= selected ? "selected" : "" %>><%= library.getName() %></option>
                                <% } } %>
                            </select>
                        </label>
                        <label>
                            <span><%= I18n.t(request, "books.year") %></span>
                            <input type="number" name="publishedYear" value="<%= isEdit && editBook.getPublishedYear() != null ? editBook.getPublishedYear() : "" %>">
                        </label>
                    </div>
                    <div class="actions" style="margin-top: 16px;">
                        <button class="button" type="submit"><%= isEdit ? I18n.t(request, "common.save") : I18n.t(request, "common.add") %></button>
                        <% if (isEdit) { %>
                        <a class="button secondary" href="<%= request.getContextPath() %>/books"><%= I18n.t(request, "common.cancel") %></a>
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
                        <th><%= I18n.t(request, "books.cover") %></th>
                        <th><%= I18n.t(request, "common.id") %></th>
                        <th><%= I18n.t(request, "books.title") %></th>
                        <th>Категория</th>
                        <th><%= I18n.t(request, "books.isbn") %></th>
                        <th><%= I18n.t(request, "nav.authors") %></th>
                        <th><%= I18n.t(request, "nav.libraries") %></th>
                        <th><%= I18n.t(request, "books.year") %></th>
                        <th><%= I18n.t(request, "common.actions") %></th>
                    </tr>
                    </thead>
                    <tbody>
                    <% if (books != null) {
                        for (Book book : books) {
                            String coverUrl = "https://images.unsplash.com/photo-1521587760476-6c12a4b040da?auto=format&fit=crop&w=84&h=120&q=80";
                            if (coverIndex % 4 == 1) coverUrl = "https://images.unsplash.com/photo-1495446815901-a7297e633e8d?auto=format&fit=crop&w=84&h=120&q=80";
                            if (coverIndex % 4 == 2) coverUrl = "https://images.unsplash.com/photo-1481627834876-b7833e8f5570?auto=format&fit=crop&w=84&h=120&q=80";
                            if (coverIndex % 4 == 3) coverUrl = "https://images.unsplash.com/photo-1512820790803-83ca734da794?auto=format&fit=crop&w=84&h=120&q=80";
                            coverIndex++;
                    %>
                    <tr>
                        <td><img class="cover-thumb" src="<%= coverUrl %>" alt="Book cover"></td>
                        <td><%= book.getId() %></td>
                        <td><a href="<%= request.getContextPath() %>/book?id=<%= book.getId() %>"><%= book.getTitle() %></a></td>
                        <td><%= book.getGenre() %></td>
                        <td><%= book.getIsbn() %></td>
                        <td><%= book.getAuthorName() %></td>
                        <td><%= book.getLibraryName() %></td>
                        <td><%= book.getPublishedYear() == null ? "-" : book.getPublishedYear() %></td>
                        <td>
                            <div class="actions">
                                <% if (isAdmin) { %>
                                <a class="button secondary" href="<%= request.getContextPath() %>/books?editId=<%= book.getId() %>"><%= I18n.t(request, "common.edit") %></a>
                                <% } %>
                                <a class="button secondary" href="<%= request.getContextPath() %>/book?id=<%= book.getId() %>"><%= I18n.t(request, "books.open") %></a>
                                <% if (isAdmin) { %>
                                <form method="post" action="<%= request.getContextPath() %>/books">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="id" value="<%= book.getId() %>">
                                    <button class="button" type="submit"><%= I18n.t(request, "common.delete") %></button>
                                </form>
                                <% } %>
                            </div>
                        </td>
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
