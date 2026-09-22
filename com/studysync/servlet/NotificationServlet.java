
package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

@WebServlet("/notifications")
public class NotificationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        User user = (User) session.getAttribute("user");

        NotificationDAO notificationDAO = new NotificationDAOImpl();

        List<Notification> notifications =
                notificationDAO.getNotificationsByUserId(user.getId());

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");

        out.println("<title>StudySync - Notifications</title>");

        out.println("<style>");

        out.println("* {");
        out.println("    box-sizing: border-box;");
        out.println("    margin: 0;");
        out.println("    padding: 0;");
        out.println("}");

        out.println("body {");
        out.println("    font-family: Arial, sans-serif;");
        out.println("    background: linear-gradient(135deg, #f5f7ff, #eef2ff);");
        out.println("    min-height: 100vh;");
        out.println("}");

        out.println(".navbar {");
        out.println("    background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("    padding: 18px 40px;");
        out.println("    display: flex;");
        out.println("    justify-content: space-between;");
        out.println("    align-items: center;");
        out.println("    color: white;");
        out.println("}");

        out.println(".logo {");
        out.println("    font-size: 25px;");
        out.println("    font-weight: bold;");
        out.println("}");

        out.println(".nav-links {");
        out.println("    display: flex;");
        out.println("    gap: 10px;");
        out.println("    flex-wrap: wrap;");
        out.println("}");

        out.println(".nav-links a {");
        out.println("    color: white;");
        out.println("    text-decoration: none;");
        out.println("    padding: 9px 14px;");
        out.println("    border-radius: 20px;");
        out.println("    font-size: 14px;");
        out.println("}");

        out.println(".nav-links a:hover {");
        out.println("    background: rgba(255,255,255,0.2);");
        out.println("}");

        out.println(".container {");
        out.println("    max-width: 1000px;");
        out.println("    margin: 40px auto;");
        out.println("    padding: 0 20px;");
        out.println("}");

        out.println("h1 {");
        out.println("    color: #333;");
        out.println("    font-size: 32px;");
        out.println("    margin-bottom: 8px;");
        out.println("}");

        out.println(".subtitle {");
        out.println("    color: #777;");
        out.println("    margin-bottom: 25px;");
        out.println("}");

        out.println(".notification-card {");
        out.println("    background: white;");
        out.println("    border-radius: 16px;");
        out.println("    padding: 22px;");
        out.println("    margin-bottom: 16px;");
        out.println("    box-shadow: 0 5px 18px rgba(0,0,0,0.08);");
        out.println("    border-left: 5px solid #667eea;");
        out.println("}");

        out.println(".unread {");
        out.println("    background: #f0f3ff;");
        out.println("    border-left-color: #ff6b6b;");
        out.println("}");

        out.println(".notification-top {");
        out.println("    display: flex;");
        out.println("    justify-content: space-between;");
        out.println("    align-items: center;");
        out.println("    gap: 15px;");
        out.println("}");

        out.println(".notification-title {");
        out.println("    font-size: 20px;");
        out.println("    font-weight: bold;");
        out.println("    color: #333;");
        out.println("}");

        out.println(".type {");
        out.println("    padding: 5px 12px;");
        out.println("    border-radius: 15px;");
        out.println("    background: #e8eaff;");
        out.println("    color: #5a5fcf;");
        out.println("    font-size: 12px;");
        out.println("    font-weight: bold;");
        out.println("}");

        out.println(".message {");
        out.println("    color: #555;");
        out.println("    margin: 12px 0;");
        out.println("    line-height: 1.6;");
        out.println("}");

        out.println(".date {");
        out.println("    color: #999;");
        out.println("    font-size: 13px;");
        out.println("    margin-bottom: 15px;");
        out.println("}");

        out.println(".status {");
        out.println("    color: #ff6b6b;");
        out.println("    font-size: 12px;");
        out.println("    font-weight: bold;");
        out.println("    margin-bottom: 5px;");
        out.println("}");

        out.println(".actions {");
        out.println("    display: flex;");
        out.println("    gap: 10px;");
        out.println("    align-items: center;");
        out.println("    flex-wrap: wrap;");
        out.println("}");

        out.println(".read-button {");
        out.println("    display: inline-block;");
        out.println("    padding: 9px 15px;");
        out.println("    background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("    color: white;");
        out.println("    text-decoration: none;");
        out.println("    border-radius: 8px;");
        out.println("    font-size: 13px;");
        out.println("    font-weight: bold;");
        out.println("}");

        out.println(".delete-button {");
        out.println("    display: inline-block;");
        out.println("    padding: 9px 15px;");
        out.println("    background: #ff4d4d;");
        out.println("    color: white;");
        out.println("    text-decoration: none;");
        out.println("    border-radius: 8px;");
        out.println("    font-size: 13px;");
        out.println("    font-weight: bold;");
        out.println("}");

        out.println(".delete-button:hover {");
        out.println("    background: #d93636;");
        out.println("}");

        out.println(".empty {");
        out.println("    background: white;");
        out.println("    padding: 60px 20px;");
        out.println("    text-align: center;");
        out.println("    border-radius: 18px;");
        out.println("}");

        out.println(".empty h2 {");
        out.println("    color: #555;");
        out.println("    margin-bottom: 10px;");
        out.println("}");

        out.println(".empty p {");
        out.println("    color: #888;");
        out.println("}");

        out.println("@media(max-width: 700px) {");

        out.println("    .navbar {");
        out.println("        flex-direction: column;");
        out.println("        gap: 15px;");
        out.println("    }");

        out.println("    .notification-top {");
        out.println("        flex-direction: column;");
        out.println("        align-items: flex-start;");
        out.println("    }");

        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        out.println("<div class='navbar'>");

        out.println("<div class='logo'>StudySync</div>");

        out.println("<div class='nav-links'>");

        out.println("<a href='dashboard'>Dashboard</a>");
        out.println("<a href='subjects.html'>Subjects</a>");
        out.println("<a href='tasks'>Tasks</a>");
        out.println("<a href='study-sessions'>Study Sessions</a>");
        out.println("<a href='notes'>Notes</a>");
        out.println("<a href='goals'>Goals</a>");
        out.println("<a href='notifications'>Notifications</a>");

        out.println("</div>");

        out.println("</div>");

        out.println("<div class='container'>");

        out.println("<h1>Notifications</h1>");

        out.println("<p class='subtitle'>");
        out.println("Stay updated with your StudySync activities");
        out.println("</p>");

        if (notifications.isEmpty()) {

            out.println("<div class='empty'>");

            out.println("<h2>No Notifications</h2>");

            out.println("<p>You don't have any notifications right now.</p>");

            out.println("</div>");

        } else {

            for (Notification notification : notifications) {

                String cardClass;

                if (notification.isRead()) {
                    cardClass = "notification-card";
                } else {
                    cardClass = "notification-card unread";
                }

                out.println("<div class='" + cardClass + "'>");

                out.println("<div class='notification-top'>");

                out.println("<div class='notification-title'>");
                out.println(notification.getTitle());
                out.println("</div>");

                if (notification.getType() != null
                        && !notification.getType().isEmpty()) {

                    out.println("<span class='type'>");
                    out.println(notification.getType());
                    out.println("</span>");
                }

                out.println("</div>");

                out.println("<div class='message'>");
                out.println(notification.getMessage());
                out.println("</div>");

                if (notification.getCreatedAt() != null) {

                    out.println("<div class='date'>");
                    out.println("Created: " + notification.getCreatedAt());
                    out.println("</div>");
                }

                out.println("<div class='actions'>");

                if (!notification.isRead()) {

                    out.println("<div>");

                    out.println("<div class='status'>UNREAD</div>");

                    out.println("<a class='read-button' href='mark-notification-read?id="
                            + notification.getId() + "'>");
                    out.println("Mark as Read");
                    out.println("</a>");

                    out.println("</div>");
                }

                out.println("<a class='delete-button' href='delete-notification?id="
                        + notification.getId()
                        + "' onclick=\"return confirm('Are you sure you want to delete this notification?');\">");

                out.println("Delete");

                out.println("</a>");

                out.println("</div>");

                out.println("</div>");
            }
        }

        out.println("</div>");

        out.println("</body>");

        out.println("</html>");
    }
}

