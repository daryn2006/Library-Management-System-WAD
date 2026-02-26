package kz.example.lms.servlet;

import kz.example.lms.model.Library;
import kz.example.lms.store.Storage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/libraries")
public class LibraryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String editId = req.getParameter("editId");
        if (editId != null) {
            Library library = Storage.findLibraryById(Integer.parseInt(editId));
            req.setAttribute("editLibrary", library);
        }
        List<Library> libraries = Storage.getLibraries();
        req.setAttribute("libraries", libraries);
        req.getRequestDispatcher("/WEB-INF/jsp/libraries.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String address = req.getParameter("address");

        if ("create".equals(action)) {
            Storage.createLibrary(name, address);
        } else if ("update".equals(action) && id != null) {
            Storage.updateLibrary(Integer.parseInt(id), name, address);
        } else if ("delete".equals(action) && id != null) {
            Storage.deleteLibrary(Integer.parseInt(id));
        }
        resp.sendRedirect(req.getContextPath() + "/libraries");
    }
}
