
package com.studysync.servlet;

import java.io.IOException;

import com.studysync.DAO.StudySessionDAO;
import com.studysync.DAOImpl.StudySessionDAOImpl;
import com.studysync.model.StudySession;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/delete-study-session")
public class DeleteStudySessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
            session.getAttribute("user") == null) {

            response.sendRedirect("login.html");
            return;
        }

        User user = (User) session.getAttribute("user");

        String idString = request.getParameter("id");

        if (idString == null) {
            response.sendRedirect("study-sessions");
            return;
        }

        int id = Integer.parseInt(idString);

        StudySessionDAO sessionDAO =
                new StudySessionDAOImpl();

        StudySession studySession =
                sessionDAO.getStudySession(id);

        /*
         * Check whether the study session exists
         */
        if (studySession == null) {

            response.sendRedirect("study-sessions");
            return;
        }

        /*
         * Security check:
         * User can delete only their own study session
         */
        if (studySession.getUserId() != user.getId()) {

            response.sendRedirect("study-sessions");
            return;
        }

        /*
         * Delete the study session
         */
        sessionDAO.deleteStudySession(id);

        /*
         * Go back to Study Sessions page
         */
        response.sendRedirect("study-sessions");
    }
}

