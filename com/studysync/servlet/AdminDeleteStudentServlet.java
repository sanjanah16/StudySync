
package com.studysync.servlet;

import java.io.IOException;

import com.studysync.DAO.UserDAO;
import com.studysync.DAOImpl.UserDAOImpl;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin-delete-student")
public class AdminDeleteStudentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        /* CHECK LOGIN */

        if (session == null ||
            session.getAttribute("user") == null) {

            response.sendRedirect("login.html");
            return;
        }

        /* CHECK ADMIN */

        User admin =
                (User) session.getAttribute("user");

        if (!"ADMIN".equalsIgnoreCase(admin.getRole())) {

            response.sendRedirect("dashboard");
            return;
        }

        /* GET STUDENT ID */

        String idString =
                request.getParameter("id");

        if (idString == null ||
            idString.trim().isEmpty()) {

            response.sendRedirect("admin-students");
            return;
        }

        int studentId;

        try {

            studentId =
                    Integer.parseInt(idString);

        } catch (NumberFormatException e) {

            response.sendRedirect("admin-students");
            return;
        }

        /* GET STUDENT */

        UserDAO userDAO =
                new UserDAOImpl();

        User student =
                userDAO.getUser(studentId);

        /* MAKE SURE IT IS A STUDENT */

        if (student == null ||
            !"STUDENT".equalsIgnoreCase(student.getRole())) {

            response.sendRedirect("admin-students");
            return;
        }

        /* DELETE STUDENT */

        userDAO.deleteUser(studentId);

        /* RETURN TO STUDENT PAGE */

        response.sendRedirect("admin-students");
    }
}

