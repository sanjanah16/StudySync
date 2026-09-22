
package com.studysync.servlet;

import java.io.IOException;

import com.studysync.DAO.SubjectDAO;
import com.studysync.DAOImpl.SubjectDAOImpl;
import com.studysync.model.Subject;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/delete-subject")
public class DeleteSubjectServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        // Check login
        if (session == null ||
            session.getAttribute("user") == null) {

            response.sendRedirect("login.html");
            return;
        }

        // Get logged-in user
        User loggedInUser =
                (User) session.getAttribute("user");

        // Only ADMIN can delete subjects
        if (!"ADMIN".equalsIgnoreCase(loggedInUser.getRole())) {

            response.sendRedirect("dashboard");
            return;
        }

        // Get subject ID
        String idString =
                request.getParameter("id");

        if (idString == null ||
            idString.isEmpty()) {

            response.sendRedirect("admin-subjects");
            return;
        }

        int subjectId;

        try {

            subjectId =
                    Integer.parseInt(idString);

        } catch (NumberFormatException e) {

            response.sendRedirect("admin-subjects");
            return;
        }

        // Get subject from database
        SubjectDAO subjectDAO =
                new SubjectDAOImpl();

        Subject subject =
                subjectDAO.getSubject(subjectId);

        if (subject == null) {

            response.sendRedirect("admin-subjects");
            return;
        }

        // Delete subject
        subjectDAO.deleteSubject(subjectId);

        // Go back to Manage Subjects
        response.sendRedirect("admin-subjects");
    }
}
