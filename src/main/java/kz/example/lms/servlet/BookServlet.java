package kz.example.lms.servlet;

import kz.example.lms.model.Book;
import kz.example.lms.store.Storage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/books")
public class BookServlet extends BaseLmsServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String editId = req.getParameter("editId");
        if (editId != null && isAdmin(req)) {
            Book book = dataService.findBookById(Integer.parseInt(editId));
            req.setAttribute("editBook", book);
        }

        String query = req.getParameter("q");
        String genre = req.getParameter("genre");

        List<Book> allBooks = dataService.getBooks();
        Map<String, Integer> categories = new LinkedHashMap<>();
        for (Book book : allBooks) {
            String bookGenre = (book.getGenre() == null || book.getGenre().isBlank()) ? "General" : book.getGenre();
            categories.put(bookGenre, categories.getOrDefault(bookGenre, 0) + 1);
        }

        req.setAttribute("searchQuery", query == null ? "" : query);
        req.setAttribute("selectedGenre", genre == null ? "" : genre);
        req.setAttribute("categories", categories);
        req.setAttribute("books", dataService.searchBooks(query, genre));
        req.setAttribute("authors", dataService.getAuthors());
        req.setAttribute("libraries", dataService.getLibraries());
        req.getRequestDispatcher("/WEB-INF/jsp/books.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        if (!isAdmin(req)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        String title = req.getParameter("title");
        String genre = req.getParameter("genre");
        String isbn = req.getParameter("isbn");
        String authorId = req.getParameter("authorId");
        String libraryId = req.getParameter("libraryId");
        String year = req.getParameter("publishedYear");

        if (genre == null || genre.trim().isEmpty()) {
            genre = "General";
        } else {
            genre = genre.trim();
        }
        Integer parsedYear = (year == null || year.trim().isEmpty()) ? null : Integer.parseInt(year);
        if ("create".equals(action)) {
            dataService.createBook(title, genre, isbn, Integer.parseInt(authorId), Integer.parseInt(libraryId), parsedYear);
        } else if ("update".equals(action) && id != null) {
            dataService.updateBook(Integer.parseInt(id), title, genre, isbn,
                    Integer.parseInt(authorId), Integer.parseInt(libraryId), parsedYear);
        } else if ("delete".equals(action) && id != null) {
            dataService.deleteBook(Integer.parseInt(id));
        }
        resp.sendRedirect(req.getContextPath() + "/books");
    }
}

