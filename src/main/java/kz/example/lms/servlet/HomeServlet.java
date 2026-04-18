package kz.example.lms.servlet;

import kz.example.lms.store.Storage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends BaseLmsServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("authorCount", dataService.getAuthorCount());
        req.setAttribute("bookCount", dataService.getBookCount());
        req.setAttribute("libraryCount", dataService.getLibraryCount());
        req.setAttribute("memberCount", dataService.getMemberCount());
        req.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(req, resp);
    }
}

