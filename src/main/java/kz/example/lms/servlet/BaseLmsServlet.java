package kz.example.lms.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kz.example.lms.model.User;
import kz.example.lms.service.LmsDataService;
import org.springframework.web.context.support.WebApplicationContextUtils;

public abstract class BaseLmsServlet extends HttpServlet {

    protected LmsDataService dataService;

    @Override
    public void init() throws ServletException {
        super.init();
        dataService = WebApplicationContextUtils
                .getRequiredWebApplicationContext(getServletContext())
                .getBean(LmsDataService.class);
    }

    protected boolean isAdmin(HttpServletRequest request) {
        Object attr = request.getAttribute("isAdmin");
        if (attr instanceof Boolean && (Boolean) attr) {
            return true;
        }
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null && "ADMIN".equalsIgnoreCase(user.getRole())) {
                return true;
            }
        }
        return request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ADMIN");
    }
}
