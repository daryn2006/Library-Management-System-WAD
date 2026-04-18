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

@WebServlet("/book")
public class BookDetailServlet extends BaseLmsServlet {
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
        if (book == null) {
            resp.sendRedirect(req.getContextPath() + "/books");
            return;
        }
        BookText bookText = dataService.findBookTextByBookId(id);
        req.setAttribute("book", book);
        req.setAttribute("bookText", bookText);
        req.getRequestDispatcher("/WEB-INF/jsp/book.jsp").forward(req, resp);
    }
}

