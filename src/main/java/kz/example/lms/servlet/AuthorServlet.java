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
public class AuthorServlet extends BaseLmsServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String query = req.getParameter("q");
        String editId = req.getParameter("editId");
        if (editId != null && isAdmin(req)) {
            Author author = dataService.findAuthorById(Integer.parseInt(editId));
            req.setAttribute("editAuthor", author);
        }
        List<Author> authors = dataService.searchAuthors(query);
        req.setAttribute("searchQuery", query == null ? "" : query);
        req.setAttribute("authors", authors);
        req.getRequestDispatcher("/WEB-INF/jsp/authors.jsp").forward(req, resp);
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
        String fullName = req.getParameter("fullName");
        String country = req.getParameter("country");

        if ("create".equals(action)) {
            dataService.createAuthor(fullName, country);
        } else if ("update".equals(action) && id != null) {
            dataService.updateAuthor(Integer.parseInt(id), fullName, country);
        } else if ("delete".equals(action) && id != null) {
            dataService.deleteAuthor(Integer.parseInt(id));
        }
        resp.sendRedirect(req.getContextPath() + "/authors");
    }
}

