
package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

@WebServlet("/goals")
public class GoalServlet extends HttpServlet {

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

        // Create DAO
        GoalDAO goalDAO = new GoalDAOImpl();

        // Get goals of logged-in user
        List<Goal> goals = goalDAO.getGoalsByUserId(user.getId());

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        // HTML starts
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");

        out.println("<meta charset='UTF-8'>");
        out.println("<title>My Goals - StudySync</title>");

        // CSS
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

        // Navbar
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

        out.println(".nav-links a:hover {");
        out.println("    text-decoration: underline;");
        out.println("}");

        // Main container
        out.println(".container {");
        out.println("    width: 90%;");
        out.println("    max-width: 1200px;");
        out.println("    margin: 40px auto;");
        out.println("}");

        out.println(".header {");
        out.println("    display: flex;");
        out.println("    justify-content: space-between;");
        out.println("    align-items: center;");
        out.println("    margin-bottom: 30px;");
        out.println("}");

        out.println("h1 {");
        out.println("    margin: 0;");
        out.println("    color: #312e81;");
        out.println("}");

        out.println(".subtitle {");
        out.println("    color: #666;");
        out.println("    margin-top: 8px;");
        out.println("}");

        // Add button
        out.println(".add-btn {");
        out.println("    background: linear-gradient(90deg, #ec4899, #8b5cf6);");
        out.println("    color: white;");
        out.println("    padding: 13px 22px;");
        out.println("    border-radius: 10px;");
        out.println("    text-decoration: none;");
        out.println("    font-weight: bold;");
        out.println("}");

        out.println(".add-btn:hover {");
        out.println("    opacity: 0.9;");
        out.println("}");

        // Goal grid
        out.println(".goal-grid {");
        out.println("    display: grid;");
        out.println("    grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));");
        out.println("    gap: 25px;");
        out.println("}");

        // Goal card
        out.println(".goal-card {");
        out.println("    background: white;");
        out.println("    border-radius: 18px;");
        out.println("    padding: 25px;");
        out.println("    box-shadow: 0 10px 25px rgba(0,0,0,0.08);");
        out.println("    border-left: 6px solid #6366f1;");
        out.println("    transition: transform 0.2s;");
        out.println("}");

        out.println(".goal-card:hover {");
        out.println("    transform: translateY(-5px);");
        out.println("}");

        out.println(".goal-title {");
        out.println("    font-size: 21px;");
        out.println("    font-weight: bold;");
        out.println("    color: #312e81;");
        out.println("    margin-bottom: 12px;");
        out.println("}");

        out.println(".goal-description {");
        out.println("    color: #555;");
        out.println("    line-height: 1.6;");
        out.println("    min-height: 50px;");
        out.println("}");

        out.println(".target-date {");
        out.println("    margin-top: 18px;");
        out.println("    color: #555;");
        out.println("    font-size: 14px;");
        out.println("}");

        // Status
        out.println(".status {");
        out.println("    display: inline-block;");
        out.println("    margin-top: 15px;");
        out.println("    padding: 7px 14px;");
        out.println("    border-radius: 20px;");
        out.println("    font-size: 13px;");
        out.println("    font-weight: bold;");
        out.println("}");

        out.println(".pending {");
        out.println("    background: #fff3cd;");
        out.println("    color: #856404;");
        out.println("}");

        out.println(".completed {");
        out.println("    background: #d1fae5;");
        out.println("    color: #065f46;");
        out.println("}");

        out.println(".in-progress {");
        out.println("    background: #dbeafe;");
        out.println("    color: #1e40af;");
        out.println("}");

        // Buttons
        out.println(".actions {");
        out.println("    margin-top: 20px;");
        out.println("    display: flex;");
        out.println("    gap: 10px;");
        out.println("}");

        out.println(".action-btn {");
        out.println("    padding: 9px 15px;");
        out.println("    border-radius: 8px;");
        out.println("    text-decoration: none;");
        out.println("    font-size: 13px;");
        out.println("}");

        out.println(".edit-btn {");
        out.println("    background: #e0e7ff;");
        out.println("    color: #3730a3;");
        out.println("}");

        out.println(".delete-btn {");
        out.println("    background: #fee2e2;");
        out.println("    color: #b91c1c;");
        out.println("}");

        // Empty state
        out.println(".empty {");
        out.println("    background: white;");
        out.println("    padding: 50px;");
        out.println("    text-align: center;");
        out.println("    border-radius: 18px;");
        out.println("    box-shadow: 0 10px 25px rgba(0,0,0,0.08);");
        out.println("}");

        out.println(".empty h2 {");
        out.println("    color: #312e81;");
        out.println("}");

        out.println(".empty p {");
        out.println("    color: #777;");
        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        // Navbar
        out.println("<div class='navbar'>");

        out.println("<div class='logo'>StudySync</div>");

        out.println("<div class='nav-links'>");
        out.println("<a href='dashboard'>Dashboard</a>");
        out.println("<a href='tasks'>Tasks</a>");
        out.println("<a href='study-sessions'>Study Sessions</a>");
        out.println("<a href='notes'>Notes</a>");
        out.println("<a href='goals'>Goals</a>");
        out.println("</div>");

        out.println("</div>");

        // Container
        out.println("<div class='container'>");

        // Header
        out.println("<div class='header'>");

        out.println("<div>");
        out.println("<h1>My Goals</h1>");
        out.println("<p class='subtitle'>Set targets, stay focused and track your progress.</p>");
        out.println("</div>");

        out.println("<a href='add-goal' class='add-btn'>+ Add Goal</a>");

        out.println("</div>");

        // Check goals
        if (goals.isEmpty()) {

            out.println("<div class='empty'>");

            out.println("<h2>No Goals Yet</h2>");

            out.println("<p>Create your first study goal and start working towards it.</p>");

            out.println("<br>");

            out.println("<a href='add-goal' class='add-btn'>Create Your First Goal</a>");

            out.println("</div>");

        } else {

            out.println("<div class='goal-grid'>");

            for (Goal goal : goals) {

                String statusClass = "pending";

                if ("COMPLETED".equalsIgnoreCase(goal.getStatus())) {
                    statusClass = "completed";
                } else if ("IN_PROGRESS".equalsIgnoreCase(goal.getStatus())
                        || "IN PROGRESS".equalsIgnoreCase(goal.getStatus())) {
                    statusClass = "in-progress";
                }

                out.println("<div class='goal-card'>");

                out.println("<div class='goal-title'>"
                        + goal.getTitle()
                        + "</div>");

                out.println("<div class='goal-description'>"
                        + goal.getDescription()
                        + "</div>");

                out.println("<div class='target-date'>");

                out.println("<strong>Target Date:</strong> "
                        + (goal.getTargetDate() != null
                        ? goal.getTargetDate()
                        : "Not Set"));

                out.println("</div>");

                out.println("<span class='status " + statusClass + "'>"
                        + goal.getStatus()
                        + "</span>");

                out.println("<div class='actions'>");

                out.println("<a href='edit-goal?id="
                        + goal.getId()
                        + "' class='action-btn edit-btn'>Edit</a>");

                out.println("<a href='delete-goal?id="
                        + goal.getId()
                        + "' class='action-btn delete-btn'>Delete</a>");

                out.println("</div>");

                out.println("</div>");
            }

            out.println("</div>");
        }

        out.println("</div>");

        out.println("</body>");

        out.println("</html>");
    }
}

