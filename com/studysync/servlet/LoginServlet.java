
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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAOImpl();

        User user = userDAO.getUserByEmail(email);

        if (user != null &&
            user.getPassword().equals(password)) {

            HttpSession session = request.getSession();

            session.setAttribute("user", user);

            String role = user.getRole();

            if ("MENTOR".equalsIgnoreCase(role)) {

                response.sendRedirect("mentor");

            } else if ("ADMIN".equalsIgnoreCase(role)) {

                response.sendRedirect("admin-dashboard");

            } else {

                response.sendRedirect("dashboard");
            }

        } else {

            response.setContentType("text/html");

            response.getWriter().println(
                    "<h2>Invalid email or password</h2>"
            );

            response.getWriter().println(
                    "<a href='login.html'>Try Again</a>"
            );
        }
    }
}
