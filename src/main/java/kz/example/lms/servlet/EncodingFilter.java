package kz.example.lms.servlet;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kz.example.lms.util.I18n;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter implements Filter {
    @Override
    public void doFilter(jakarta.servlet.ServletRequest request, jakarta.servlet.ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        request.setCharacterEncoding("UTF-8");
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String lang = req.getParameter("lang");
        if (lang != null && !lang.isBlank()) {
            I18n.setLang(req, lang);
        } else if (req.getSession(false) == null || req.getSession(false).getAttribute(I18n.LANG_SESSION_KEY) == null) {
            I18n.setLang(req, I18n.RU);
        }

        chain.doFilter(request, response);
    }
}
