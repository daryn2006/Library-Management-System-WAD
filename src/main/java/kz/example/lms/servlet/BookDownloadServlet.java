package kz.example.lms.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.example.lms.model.Book;
import kz.example.lms.model.BookText;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@WebServlet("/download")
public class BookDownloadServlet extends BaseLmsServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam == null) {
            resp.sendRedirect(req.getContextPath() + "/books");
            return;
        }

        int id = Integer.parseInt(idParam);
        Book book = dataService.findBookById(id);
        BookText bookText = dataService.findBookTextByBookId(id);
        if (book == null || bookText == null) {
            resp.sendRedirect(req.getContextPath() + "/book?id=" + id);
            return;
        }

        if (bookText.getSourceUrl() != null && !bookText.getSourceUrl().isBlank()) {
            resp.sendRedirect(bookText.getSourceUrl());
            return;
        }

        String fileName = sanitize(book.getTitle()) + ".txt";
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.setContentType("text/plain; charset=UTF-8");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(bookText.getResourcePath())) {
            if (input == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Book file not found");
                return;
            }
            input.transferTo(resp.getOutputStream());
        }
    }

    private String sanitize(String value) {
        if (value == null || value.isBlank()) {
            return "book";
        }
        return value.replaceAll("[^a-zA-Z0-9._-]+", "_");
    }
}
