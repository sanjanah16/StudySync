
package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import com.studysync.DAO.GoalDAO;
import com.studysync.DAOImpl.GoalDAOImpl;
import com.studysync.model.Goal;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/add-goal")
public class AddGoalServlet extends HttpServlet {

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

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");

        out.println("<meta charset='UTF-8'>");
        out.println("<title>Add Goal - StudySync</title>");

        out.println("<style>");

        out.println("* {");
        out.println("    box-sizing: border-box;");
        out.println("}");

        out.println("body {");
        out.println("    margin: 0;");
        out.println("    font-family: Arial, sans-serif;");
        out.println("    background: linear-gradient(135deg, #eef2ff, #fdf2f8);");
        out.println("    min-height: 100vh;");
        out.println("}");

        /* Navbar */
        out.println(".navbar {");
        out.println("    background: linear-gradient(90deg, #4f46e5, #7c3aed);");
        out.println("    color: white;");
        out.println("    padding: 18px 40px;");
        out.println("    display: flex;");
        out.println("    justify-content: space-between;");
        out.println("    align-items: center;");
        out.println("}");

        out.println(".logo {");
        out.println("    font-size: 25px;");
        out.println("    font-weight: bold;");
        out.println("}");

        out.println(".nav-links a {");
        out.println("    color: white;");
        out.println("    text-decoration: none;");
        out.println("    margin-left: 22px;");
        out.println("    font-size: 15px;");
        out.println("}");

        /* Container */
        out.println(".container {");
        out.println("    width: 90%;");
        out.println("    max-width: 750px;");
        out.println("    margin: 50px auto;");
        out.println("}");

        /* Card */
        out.println(".card {");
        out.println("    background: white;");
        out.println("    padding: 35px;");
        out.println("    border-radius: 20px;");
        out.println("    box-shadow: 0 12px 30px rgba(0,0,0,0.10);");
        out.println("}");

        out.println("h1 {");
        out.println("    margin-top: 0;");
        out.println("    color: #312e81;");
        out.println("}");

        out.println(".subtitle {");
        out.println("    color: #666;");
        out.println("    margin-bottom: 30px;");
        out.println("}");

        /* Labels */
        out.println("label {");
        out.println("    display: block;");
        out.println("    margin-top: 20px;");
        out.println("    margin-bottom: 8px;");
        out.println("    font-weight: bold;");
        out.println("    color: #333;");
        out.println("}");

        /* Inputs */
        out.println("input, textarea, select {");
        out.println("    width: 100%;");
        out.println("    padding: 13px;");
        out.println("    border: 1px solid #d1d5db;");
        out.println("    border-radius: 10px;");
        out.println("    font-size: 15px;");
        out.println("}");

        out.println("textarea {");
        out.println("    min-height: 130px;");
        out.println("    resize: vertical;");
        out.println("}");

        out.println("input:focus, textarea:focus, select:focus {");
        out.println("    outline: none;");
        out.println("    border-color: #6366f1;");
        out.println("    box-shadow: 0 0 0 3px rgba(99,102,241,0.12);");
        out.println("}");

        /* Buttons */
        out.println(".buttons {");
        out.println("    display: flex;");
        out.println("    gap: 15px;");
        out.println("    margin-top: 30px;");
        out.println("}");

        out.println("button, .cancel-btn {");
        out.println("    padding: 13px 24px;");
        out.println("    border-radius: 10px;");
        out.println("    font-size: 15px;");
        out.println("    cursor: pointer;");
        out.println("    text-decoration: none;");
        out.println("}");

        out.println(".save-btn {");
        out.println("    border: none;");
        out.println("    color: white;");
        out.println("    background: linear-gradient(90deg, #4f46e5, #7c3aed);");
        out.println("}");

        out.println(".cancel-btn {");
        out.println("    background: #e5e7eb;");
        out.println("    color: #333;");
        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        /* Navbar */
        out.println("<div class='navbar'>");

        out.println("<div class='logo'>StudySync</div>");

        out.println("<div class='nav-links'>");
        out.println("<a href='dashboard'>Dashboard</a>");
        out.println("<a href='goals'>Goals</a>");
        out.println("<a href='tasks'>Tasks</a>");
        out.println("<a href='notes'>Notes</a>");
        out.println("</div>");

        out.println("</div>");

        /* Main */
        out.println("<div class='container'>");

        out.println("<div class='card'>");

        out.println("<h1>Create New Goal</h1>");

        out.println("<p class='subtitle'>Set a clear target and keep yourself motivated.</p>");

        /* Form */
        out.println("<form action='add-goal' method='post'>");

        out.println("<label>Goal Title</label>");

        out.println("<input type='text' "
                + "name='title' "
                + "placeholder='Example: Complete Java Full Stack Course' "
                + "required>");

        out.println("<label>Description</label>");

        out.println("<textarea name='description' "
                + "placeholder='Describe what you want to achieve...' "
                + "required></textarea>");

        out.println("<label>Target Date</label>");

        out.println("<input type='date' "
                + "name='targetDate' "
                + "required>");

        out.println("<label>Status</label>");

        out.println("<select name='status'>");

        out.println("<option value='PENDING'>PENDING</option>");

        out.println("<option value='IN_PROGRESS'>IN PROGRESS</option>");

        out.println("<option value='COMPLETED'>COMPLETED</option>");

        out.println("</select>");

        out.println("<div class='buttons'>");

        out.println("<button type='submit' class='save-btn'>Save Goal</button>");

        out.println("<a href='goals' class='cancel-btn'>Cancel</a>");

        out.println("</div>");

        out.println("</form>");

        out.println("</div>");

        out.println("</div>");

        out.println("</body>");

        out.println("</html>");
    }


    @Override
    protected void doPost(HttpServletRequest request,
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

        // Get form data
        String title = request.getParameter("title");

        String description = request.getParameter("description");

        String targetDateString = request.getParameter("targetDate");

        String status = request.getParameter("status");

        // Convert String date to SQL Date
        Date targetDate = Date.valueOf(targetDateString);

        // Create Goal object
        Goal goal = new Goal();

        goal.setUserId(user.getId());
        goal.setTitle(title);
        goal.setDescription(description);
        goal.setTargetDate(targetDate);
        goal.setStatus(status);

        // DAO
        GoalDAO goalDAO = new GoalDAOImpl();

        // Save goal
        goalDAO.addGoal(goal);

        // Redirect to Goals page
        response.sendRedirect("goals");
    }
}

