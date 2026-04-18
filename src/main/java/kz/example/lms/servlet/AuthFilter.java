package kz.example.lms.servlet;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.Filter;
import kz.example.lms.model.User;
import java.io.IOException;
import java.security.Principal;

@WebFilter({"/home", "/authors", "/books", "/libraries", "/members", "/settings", "/book", "/read"})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(jakarta.servlet.ServletRequest request, jakarta.servlet.ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        Principal principal = req.getUserPrincipal();
        boolean hasLegacySession = session != null && session.getAttribute("user") != null;
        boolean hasSpringAuth = principal != null && !"anonymousUser".equals(principal.getName());
        if (!hasLegacySession && !hasSpringAuth) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        boolean isAdmin = false;
        if (hasLegacySession) {
            User user = (User) session.getAttribute("user");
            isAdmin = user != null && "ADMIN".equalsIgnoreCase(user.getRole());
        }
        if (!isAdmin) {
            isAdmin = req.isUserInRole("ROLE_ADMIN") || req.isUserInRole("ADMIN");
        }
        req.setAttribute("isAdmin", isAdmin);
        chain.doFilter(request, response);
    }
}
