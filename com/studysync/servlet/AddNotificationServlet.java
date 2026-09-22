
package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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

@WebServlet("/add-notification")
public class AddNotificationServlet extends HttpServlet {

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

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");

        out.println("<meta charset='UTF-8'>");
        out.println("<title>StudySync - Add Notification</title>");

        out.println("<style>");

        out.println("* {");
        out.println("    box-sizing: border-box;");
        out.println("    margin: 0;");
        out.println("    padding: 0;");
        out.println("}");

        out.println("body {");
        out.println("    font-family: Arial, sans-serif;");
        out.println("    min-height: 100vh;");
        out.println("    background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("    display: flex;");
        out.println("    justify-content: center;");
        out.println("    align-items: center;");
        out.println("    padding: 30px;");
        out.println("}");

        out.println(".container {");
        out.println("    width: 100%;");
        out.println("    max-width: 600px;");
        out.println("    background: white;");
        out.println("    padding: 35px;");
        out.println("    border-radius: 20px;");
        out.println("    box-shadow: 0 15px 40px rgba(0,0,0,0.2);");
        out.println("}");

        out.println("h1 {");
        out.println("    text-align: center;");
        out.println("    color: #333;");
        out.println("    margin-bottom: 8px;");
        out.println("}");

        out.println(".subtitle {");
        out.println("    text-align: center;");
        out.println("    color: #777;");
        out.println("    margin-bottom: 30px;");
        out.println("}");

        out.println(".form-group {");
        out.println("    margin-bottom: 20px;");
        out.println("}");

        out.println("label {");
        out.println("    display: block;");
        out.println("    margin-bottom: 8px;");
        out.println("    color: #444;");
        out.println("    font-weight: bold;");
        out.println("}");

        out.println("input, textarea, select {");
        out.println("    width: 100%;");
        out.println("    padding: 13px;");
        out.println("    border: 1px solid #ddd;");
        out.println("    border-radius: 10px;");
        out.println("    font-size: 15px;");
        out.println("    outline: none;");
        out.println("}");

        out.println("input:focus, textarea:focus, select:focus {");
        out.println("    border-color: #667eea;");
        out.println("    box-shadow: 0 0 0 3px rgba(102,126,234,0.12);");
        out.println("}");

        out.println("textarea {");
        out.println("    height: 120px;");
        out.println("    resize: vertical;");
        out.println("}");

        out.println(".buttons {");
        out.println("    display: flex;");
        out.println("    gap: 12px;");
        out.println("    margin-top: 25px;");
        out.println("}");

        out.println("button, .back {");
        out.println("    flex: 1;");
        out.println("    padding: 13px;");
        out.println("    border: none;");
        out.println("    border-radius: 10px;");
        out.println("    font-size: 15px;");
        out.println("    font-weight: bold;");
        out.println("    text-decoration: none;");
        out.println("    text-align: center;");
        out.println("    cursor: pointer;");
        out.println("}");

        out.println("button {");
        out.println("    background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("    color: white;");
        out.println("}");

        out.println("button:hover {");
        out.println("    opacity: 0.9;");
        out.println("}");

        out.println(".back {");
        out.println("    background: #eee;");
        out.println("    color: #555;");
        out.println("}");

        out.println("</style>");
        out.println("</head>");

        out.println("<body>");

        out.println("<div class='container'>");

        out.println("<h1>Add Notification</h1>");
        out.println("<p class='subtitle'>Create a notification for your StudySync account</p>");

        out.println("<form action='add-notification' method='post'>");

        out.println("<div class='form-group'>");
        out.println("<label>Title</label>");
        out.println("<input type='text' name='title' "
                + "placeholder='Enter notification title' required>");
        out.println("</div>");

        out.println("<div class='form-group'>");
        out.println("<label>Message</label>");
        out.println("<textarea name='message' "
                + "placeholder='Enter notification message' required></textarea>");
        out.println("</div>");

        out.println("<div class='form-group'>");
        out.println("<label>Type</label>");
        out.println("<select name='type'>");
        out.println("<option value='REMINDER'>REMINDER</option>");
        out.println("<option value='TASK'>TASK</option>");
        out.println("<option value='GOAL'>GOAL</option>");
        out.println("<option value='STUDY'>STUDY</option>");
        out.println("<option value='GENERAL'>GENERAL</option>");
        out.println("</select>");
        out.println("</div>");

        out.println("<div class='buttons'>");

        out.println("<a class='back' href='notifications'>Cancel</a>");

        out.println("<button type='submit'>Add Notification</button>");

        out.println("</div>");

        out.println("</form>");

        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        User user = (User) session.getAttribute("user");

        String title = request.getParameter("title");
        String message = request.getParameter("message");
        String type = request.getParameter("type");

        Notification notification = new Notification(
                user.getId(),
                title,
                message,
                type,
                false
        );

        NotificationDAO notificationDAO = new NotificationDAOImpl();

        notificationDAO.addNotification(notification);

        response.sendRedirect("notifications");
    }
}

