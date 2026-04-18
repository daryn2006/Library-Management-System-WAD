package kz.example.lms.servlet;

import kz.example.lms.model.User;
import kz.example.lms.store.Storage;
import kz.example.lms.util.SecurityUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/legacy-register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (isBlank(fullName) || isBlank(email) || isBlank(password)) {
            req.setAttribute("error", "All fields are required.");
            req.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(req, resp);
            return;
        }

        User existing = Storage.findUserByEmail(email);
        if (existing != null) {
            req.setAttribute("error", "User with this email already exists.");
            req.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(req, resp);
            return;
        }

        User user = Storage.createUser(fullName, email, SecurityUtil.sha256(password), "USER");
        HttpSession session = req.getSession(true);
        session.setAttribute("user", user);
        resp.sendRedirect(req.getContextPath() + "/home");
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
