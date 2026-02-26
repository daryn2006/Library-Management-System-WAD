package kz.example.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/data")
public class DataServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!doctype html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("  <meta charset=\"UTF-8\">");
            out.println("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
            out.println("  <title>Request Received</title>");
            out.println("  <link rel=\"stylesheet\" href=\"style.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("  <main class=\"card\">");
            out.println("    <h1>GET request received</h1>");
            out.println("    <p>Use the form to send data with POST.</p>");
            out.println("    <a class=\"button\" href=\"index.html\">Open form</a>");
            out.println("  </main>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String bookTitle = req.getParameter("bookTitle");
        String bookAuthor = req.getParameter("bookAuthor");
        String bookIsbn = req.getParameter("bookIsbn");
        String memberName = req.getParameter("memberName");
        String memberEmail = req.getParameter("memberEmail");
        String memberId = req.getParameter("memberId");
        String libraryName = req.getParameter("libraryName");
        String libraryAddress = req.getParameter("libraryAddress");

        List<String> errors = new ArrayList<>();
        if (isBlank(bookTitle)) {
            errors.add("Book title is required.");
        }
        if (isBlank(bookAuthor)) {
            errors.add("Book author is required.");
        }
        if (isBlank(bookIsbn)) {
            errors.add("Book ISBN is required.");
        }
        if (isBlank(memberName)) {
            errors.add("Member name is required.");
        }
        if (isBlank(memberEmail)) {
            errors.add("Member email is required.");
        }
        if (isBlank(memberId)) {
            errors.add("Member ID is required.");
        }
        if (isBlank(libraryName)) {
            errors.add("Library name is required.");
        }
        if (isBlank(libraryAddress)) {
            errors.add("Library address is required.");
        }

        resp.setContentType("text/html; charset=UTF-8");
        if (!errors.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!doctype html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("  <meta charset=\"UTF-8\">");
            out.println("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
            out.println("  <title>Submission Result</title>");
            out.println("  <link rel=\"stylesheet\" href=\"style.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("  <main class=\"card\">");

            if (!errors.isEmpty()) {
                out.println("    <h1>Fix the form</h1>");
                out.println("    <ul class=\"errors\">");
                for (String error : errors) {
                    out.println("      <li>" + escapeHtml(error) + "</li>");
                }
                out.println("    </ul>");
                out.println("    <a class=\"button\" href=\"index.html\">Back to form</a>");
            } else {
                out.println("    <h1>Library record created</h1>");
                out.println("    <p class=\"lead\">We received your data.</p>");
                out.println("    <div class=\"grid\">");
                out.println("      <div><span class=\"label\">Book Title</span><span>" + escapeHtml(bookTitle) + "</span></div>");
                out.println("      <div><span class=\"label\">Author</span><span>" + escapeHtml(bookAuthor) + "</span></div>");
                out.println("      <div><span class=\"label\">ISBN</span><span>" + escapeHtml(bookIsbn) + "</span></div>");
                out.println("      <div><span class=\"label\">Member</span><span>" + escapeHtml(memberName) + "</span></div>");
                out.println("      <div><span class=\"label\">Member Email</span><span>" + escapeHtml(memberEmail) + "</span></div>");
                out.println("      <div><span class=\"label\">Member ID</span><span>" + escapeHtml(memberId) + "</span></div>");
                out.println("      <div><span class=\"label\">Library</span><span>" + escapeHtml(libraryName) + "</span></div>");
                out.println("      <div><span class=\"label\">Address</span><span>" + escapeHtml(libraryAddress) + "</span></div>");
                out.println("    </div>");
                out.println("    <a class=\"button\" href=\"index.html\">Submit another</a>");
            }

            out.println("  </main>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private static String escapeHtml(String value) {
        if (value == null) {
            return "";
        }
        return value
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}
