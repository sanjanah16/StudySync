
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

@WebServlet("/delete-notification")
public class DeleteNotificationServlet extends HttpServlet {

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

        // Get notification ID
        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect("notifications");
            return;
        }

        int notificationId = Integer.parseInt(idParam);

        // Create DAO object
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

        // Security check
        // User can delete only their own notification
        if (notification.getUserId() != user.getId()) {
            response.sendRedirect("notifications");
            return;
        }

        // Delete notification
        notificationDAO.deleteNotification(notificationId);

        // Return to notifications page
        response.sendRedirect("notifications");
    }
}

