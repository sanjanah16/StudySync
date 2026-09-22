
package com.studysync.servlet;

import java.io.IOException;

import com.studysync.DAO.NotificationDAO;
import com.studysync.DAOImpl.NotificationDAOImpl;
import com.studysync.model.Notification;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/mark-notification-read")
public class MarkNotificationReadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Check login session
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        // Get logged-in user
        User user = (User) session.getAttribute("user");

        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect("notifications");
            return;
        }

        int notificationId = Integer.parseInt(idParam);

        NotificationDAO notificationDAO =
                new NotificationDAOImpl();

        // Get notification
        Notification notification =
                notificationDAO.getNotification(notificationId);

        // Check notification exists
        if (notification == null) {
            response.sendRedirect("notifications");
            return;
        }

        // Security check:
        // Make sure notification belongs to logged-in user
        if (notification.getUserId() != user.getId()) {
            response.sendRedirect("notifications");
            return;
        }

        // Mark notification as read
        notificationDAO.markAsRead(notificationId);

        // Go back to notifications page
        response.sendRedirect("notifications");
    }
}

