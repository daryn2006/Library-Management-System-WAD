package kz.example.lms.servlet;

import kz.example.lms.model.Author;
import kz.example.lms.store.Storage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/authors")
public class AuthorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String editId = req.getParameter("editId");
        if (editId != null) {
            Author author = Storage.findAuthorById(Integer.parseInt(editId));
            req.setAttribute("editAuthor", author);
        }
        List<Author> authors = Storage.getAuthors();
        req.setAttribute("authors", authors);
        req.getRequestDispatcher("/WEB-INF/jsp/authors.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        String fullName = req.getParameter("fullName");
        String country = req.getParameter("country");

        if ("create".equals(action)) {
            Storage.createAuthor(fullName, country);
        } else if ("update".equals(action) && id != null) {
            Storage.updateAuthor(Integer.parseInt(id), fullName, country);
        } else if ("delete".equals(action) && id != null) {
            Storage.deleteAuthor(Integer.parseInt(id));
        }
        resp.sendRedirect(req.getContextPath() + "/authors");
    }
}
