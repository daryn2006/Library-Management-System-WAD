package kz.example.lms.servlet;

import kz.example.lms.model.Member;
import kz.example.lms.store.Storage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/members")
public class MemberServlet extends BaseLmsServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (!isAdmin(req)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        String editId = req.getParameter("editId");
        if (editId != null) {
            Member member = dataService.findMemberById(Integer.parseInt(editId));
            req.setAttribute("editMember", member);
        }
        List<Member> members = dataService.getMembers();
        req.setAttribute("members", members);
        req.setAttribute("libraries", dataService.getLibraries());
        req.getRequestDispatcher("/WEB-INF/jsp/members.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        if (!isAdmin(req)) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        String action = req.getParameter("action");
        String id = req.getParameter("id");
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String libraryId = req.getParameter("libraryId");

        if ("create".equals(action)) {
            dataService.createMember(fullName, email, phone, Integer.parseInt(libraryId));
        } else if ("update".equals(action) && id != null) {
            dataService.updateMember(Integer.parseInt(id), fullName, email, phone, Integer.parseInt(libraryId));
        } else if ("delete".equals(action) && id != null) {
            dataService.deleteMember(Integer.parseInt(id));
        }
        resp.sendRedirect(req.getContextPath() + "/members");
    }
}

