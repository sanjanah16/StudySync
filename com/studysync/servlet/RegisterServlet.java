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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");

        User user = new User(
                name,
                email,
                password,
                phone,
                "STUDENT"
        );

        UserDAO userDAO = new UserDAOImpl();

        userDAO.addUser(user);

        response.sendRedirect("login.html");
    }
}