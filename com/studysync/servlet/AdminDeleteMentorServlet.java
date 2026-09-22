
package com.studysync.servlet;

import java.io.IOException;

import com.studysync.DAO.MentorDAO;
import com.studysync.DAO.UserDAO;
import com.studysync.DAOImpl.MentorDAOImpl;
import com.studysync.DAOImpl.UserDAOImpl;
import com.studysync.model.Mentor;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin-delete-mentor")
public class AdminDeleteMentorServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
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

        String idString =
                request.getParameter("id");

        if (idString == null ||
            idString.isEmpty()) {

            response.sendRedirect("admin-mentors");
            return;
        }

        try {

            int mentorId =
                    Integer.parseInt(idString);

            MentorDAO mentorDAO =
                    new MentorDAOImpl();

            Mentor mentor =
                    mentorDAO.getMentor(mentorId);

            if (mentor != null) {

                int userId =
                        mentor.getUserId();

                // Delete mentor profile
                mentorDAO.deleteMentor(mentorId);

                // Delete mentor user account
                UserDAO userDAO =
                        new UserDAOImpl();

                userDAO.deleteUser(userId);
            }

        } catch (NumberFormatException e) {

            e.printStackTrace();
        }

        response.sendRedirect("admin-mentors");
    }
}

