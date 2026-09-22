
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

@WebServlet("/admin-add-subject")
public class AdminAddSubjectServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        // Check admin login
        if (session == null ||
            session.getAttribute("user") == null) {

            response.sendRedirect("login.html");
            return;
        }

        User loggedInUser =
                (User) session.getAttribute("user");

        // Check admin role
        if (!"ADMIN".equalsIgnoreCase(loggedInUser.getRole())) {

            response.sendRedirect("dashboard");
            return;
        }

        String name =
                request.getParameter("name");

        String description =
                request.getParameter("description");

        if (name == null ||
            name.trim().isEmpty()) {

            response.sendRedirect("admin-subjects");
            return;
        }

        Subject subject =
                new Subject(
                        name,
                        description
                );

        SubjectDAO subjectDAO =
                new SubjectDAOImpl();

        subjectDAO.addSubject(subject);

        response.sendRedirect("admin-subjects");
    }
}

