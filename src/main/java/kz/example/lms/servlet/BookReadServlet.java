package kz.example.lms.servlet;

import kz.example.lms.model.Book;
import kz.example.lms.model.BookText;
import kz.example.lms.store.Storage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@WebServlet("/read")
public class BookReadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam == null) {
            resp.sendRedirect(req.getContextPath() + "/books");
            return;
        }
        int id = Integer.parseInt(idParam);
        Book book = Storage.findBookById(id);
        BookText bookText = Storage.findBookTextByBookId(id);
        if (book == null || bookText == null) {
            resp.sendRedirect(req.getContextPath() + "/book?id=" + id);
            return;
        }

        String content = loadContent(bookText.getResourcePath());
        req.setAttribute("book", book);
        req.setAttribute("bookText", bookText);
        req.setAttribute("content", content);
        req.getRequestDispatcher("/WEB-INF/jsp/read.jsp").forward(req, resp);
    }

    private String loadContent(String resourcePath) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (input == null) {
                return "Мәтін табылмады.";
            }
            return new String(input.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            return "Мәтінді оқу кезінде қате шықты.";
        }
    }
}
