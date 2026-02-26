package kz.example.lms.servlet;

import kz.example.lms.model.Book;
import kz.example.lms.store.Storage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/books")
public class BookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String editId = req.getParameter("editId");
        if (editId != null) {
            Book book = Storage.findBookById(Integer.parseInt(editId));
            req.setAttribute("editBook", book);
        }
        req.setAttribute("books", Storage.getBooks());
        req.setAttribute("authors", Storage.getAuthors());
        req.setAttribute("libraries", Storage.getLibraries());
        req.getRequestDispatcher("/WEB-INF/jsp/books.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        String title = req.getParameter("title");
        String isbn = req.getParameter("isbn");
        String authorId = req.getParameter("authorId");
        String libraryId = req.getParameter("libraryId");
        String year = req.getParameter("publishedYear");

        Integer parsedYear = (year == null || year.trim().isEmpty()) ? null : Integer.parseInt(year);
        if ("create".equals(action)) {
            Storage.createBook(title, isbn, Integer.parseInt(authorId), Integer.parseInt(libraryId), parsedYear);
        } else if ("update".equals(action) && id != null) {
            Storage.updateBook(Integer.parseInt(id), title, isbn,
                    Integer.parseInt(authorId), Integer.parseInt(libraryId), parsedYear);
        } else if ("delete".equals(action) && id != null) {
            Storage.deleteBook(Integer.parseInt(id));
        }
        resp.sendRedirect(req.getContextPath() + "/books");
    }
}
