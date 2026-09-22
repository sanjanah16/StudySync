
package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import com.studysync.DAO.ProgressDAO;
import com.studysync.DAO.TaskDAO;
import com.studysync.DAOImpl.ProgressDAOImpl;
import com.studysync.DAOImpl.TaskDAOImpl;
import com.studysync.model.Progress;
import com.studysync.model.Task;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Check login session

        HttpSession session = request.getSession(false);

        if (session == null ||
            session.getAttribute("user") == null) {

            response.sendRedirect("login.html");

            return;
        }

        // Get logged-in user

        User user = (User) session.getAttribute("user");

        // Get tasks from database

        TaskDAO taskDAO = new TaskDAOImpl();

        List<Task> tasks =
                taskDAO.getTasksByUserId(user.getId());

        // Response settings

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        // =========================
        // HTML START
        // =========================

        out.println("<!DOCTYPE html>");

        out.println("<html>");

        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println("<meta name='viewport' " +
                "content='width=device-width, initial-scale=1.0'>");

        out.println("<title>StudySync - My Tasks</title>");

        // =========================
        // CSS
        // =========================

        out.println("<style>");

        out.println("* {");

        out.println("margin: 0;");

        out.println("padding: 0;");

        out.println("box-sizing: border-box;");

        out.println("}");

        out.println("body {");

        out.println("font-family: Arial, sans-serif;");

        out.println("background: linear-gradient(135deg, #eef2ff, #fdf2f8);");

        out.println("min-height: 100vh;");

        out.println("}");

        // Navbar

        out.println(".navbar {");

        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");

        out.println("padding: 20px 50px;");

        out.println("color: white;");

        out.println("display: flex;");

        out.println("justify-content: space-between;");

        out.println("align-items: center;");

        out.println("}");

        // Logo

        out.println(".logo {");

        out.println("font-size: 27px;");

        out.println("font-weight: bold;");

        out.println("}");

        // Navigation buttons

        out.println(".nav-buttons {");

        out.println("display: flex;");

        out.println("gap: 10px;");

        out.println("}");

        out.println(".back, .add-task, .study-session-button {");

        out.println("text-decoration: none;");

        out.println("padding: 10px 18px;");

        out.println("border-radius: 20px;");

        out.println("font-weight: bold;");

        out.println("}");

        // Dashboard button

        out.println(".back {");

        out.println("background: rgba(255,255,255,0.2);");

        out.println("color: white;");

        out.println("}");

        // Add Task button

        out.println(".add-task {");

        out.println("background: white;");

        out.println("color: #764ba2;");

        out.println("}");

        // Study Sessions button

        out.println(".study-session-button {");

        out.println("background: #00b894;");

        out.println("color: white;");

        out.println("}");

        // Container

        out.println(".container {");

        out.println("width: 90%;");

        out.println("max-width: 1100px;");

        out.println("margin: 40px auto;");

        out.println("}");

        // Heading

        out.println(".heading {");

        out.println("margin-bottom: 30px;");

        out.println("}");

        out.println(".heading h1 {");

        out.println("color: #333;");

        out.println("margin-bottom: 8px;");

        out.println("}");

        out.println(".heading p {");

        out.println("color: #777;");

        out.println("}");

        // Task grid

        out.println(".task-grid {");

        out.println("display: grid;");

        out.println("grid-template-columns: repeat(3, 1fr);");

        out.println("gap: 25px;");

        out.println("}");

        // Task card

        out.println(".task-card {");

        out.println("background: white;");

        out.println("padding: 25px;");

        out.println("border-radius: 18px;");

        out.println("box-shadow: 0 8px 20px rgba(0,0,0,0.08);");

        out.println("transition: 0.3s;");

        out.println("}");

        out.println(".task-card:hover {");

        out.println("transform: translateY(-6px);");

        out.println("box-shadow: 0 15px 30px rgba(0,0,0,0.12);");

        out.println("}");

        // Task icon

        out.println(".task-icon {");

        out.println("font-size: 38px;");

        out.println("margin-bottom: 15px;");

        out.println("}");

        // Task title

        out.println(".task-card h2 {");

        out.println("color: #667eea;");

        out.println("margin-bottom: 10px;");

        out.println("}");

        // Description

        out.println(".description {");

        out.println("color: #666;");

        out.println("line-height: 1.5;");

        out.println("margin-bottom: 18px;");

        out.println("}");

        // Details

        out.println(".details {");

        out.println("border-top: 1px solid #eee;");

        out.println("padding-top: 15px;");

        out.println("line-height: 2;");

        out.println("}");

        // Priority

        out.println(".priority {");

        out.println("display: inline-block;");

        out.println("background: #ffe0e0;");

        out.println("color: #d63031;");

        out.println("padding: 3px 10px;");

        out.println("border-radius: 15px;");

        out.println("font-size: 13px;");

        out.println("font-weight: bold;");

        out.println("}");

        // Status

        out.println(".status {");

        out.println("display: inline-block;");

        out.println("background: #fff3cd;");

        out.println("color: #856404;");

        out.println("padding: 3px 10px;");

        out.println("border-radius: 15px;");

        out.println("font-size: 13px;");

        out.println("font-weight: bold;");

        out.println("}");

        // Action buttons

        out.println(".action-buttons {");

        out.println("display: flex;");

        out.println("gap: 10px;");

        out.println("margin-top: 20px;");

        out.println("flex-wrap: wrap;");

        out.println("}");

        // Edit button

        out.println(".edit-button {");

        out.println("background: #667eea;");

        out.println("color: white;");

        out.println("padding: 10px 18px;");

        out.println("border-radius: 10px;");

        out.println("text-decoration: none;");

        out.println("font-weight: bold;");

        out.println("}");

        out.println(".edit-button:hover {");

        out.println("background: #5568d9;");

        out.println("}");

        // Delete button

        out.println(".delete-button {");

        out.println("background: #ff4b5c;");

        out.println("color: white;");

        out.println("padding: 10px 18px;");

        out.println("border-radius: 10px;");

        out.println("text-decoration: none;");

        out.println("font-weight: bold;");

        out.println("}");

        out.println(".delete-button:hover {");

        out.println("background: #d63031;");

        out.println("}");

        // Empty message

        out.println(".empty {");

        out.println("background: white;");

        out.println("padding: 40px;");

        out.println("border-radius: 18px;");

        out.println("text-align: center;");

        out.println("color: #777;");

        out.println("}");

        // Responsive design

        out.println("@media(max-width: 800px) {");

        out.println(".task-grid {");

        out.println("grid-template-columns: 1fr;");

        out.println("}");

        out.println(".navbar {");

        out.println("padding: 20px;");

        out.println("}");

        out.println(".nav-buttons {");

        out.println("flex-direction: column;");

        out.println("}");

        out.println(".action-buttons {");

        out.println("flex-direction: column;");

        out.println("}");

        out.println("}");

        out.println("</style>");

        out.println("</head>");

        // =========================
        // BODY
        // =========================

        out.println("<body>");

        // Navbar

        out.println("<div class='navbar'>");

        out.println("<div class='logo'>StudySync</div>");

        out.println("<div class='nav-buttons'>");

        // Add Task

        out.println("<a class='add-task' href='add-task'>");

        out.println("+ Add Task");

        out.println("</a>");

        // Study Sessions

        out.println("<a class='study-session-button' href='study-sessions'>");

        out.println("Study Sessions");

        out.println("</a>");

        // Dashboard

        out.println("<a class='back' href='dashboard'>");

        out.println("Dashboard");

        out.println("</a>");

        out.println("</div>");

        out.println("</div>");

        // Main container

        out.println("<div class='container'>");

        // Heading

        out.println("<div class='heading'>");

        out.println("<h1>My Tasks</h1>");

        out.println("<p>");

        out.println("Welcome, " +
                user.getName() +
                "! Here are your study tasks.");

        out.println("</p>");

        out.println("</div>");

        // =========================
        // DISPLAY TASKS
        // =========================

        if (tasks.isEmpty()) {

            out.println("<div class='empty'>");

            out.println("<h2>No tasks yet</h2>");

            out.println("<p>");

            out.println(
                "Click '+ Add Task' to create your first study task."
            );

            out.println("</p>");

            out.println("</div>");

        } else {

            out.println("<div class='task-grid'>");

            for (Task task : tasks) {

                out.println("<div class='task-card'>");

                out.println("<div class='task-icon'>Task</div>");

                // Task title

                out.println("<h2>");

                out.println(task.getTitle());

                out.println("</h2>");

                // Description

                out.println("<p class='description'>");

                if (task.getDescription() != null) {

                    out.println(task.getDescription());

                } else {

                    out.println("No description available");

                }

                out.println("</p>");

                // Details

                out.println("<div class='details'>");

                // Due date

                out.println("<div>");

                out.println("<strong>Due:</strong> " +
                        task.getDueDate());

                out.println("</div>");

                // Priority

                out.println("<div>");

                out.println("<strong>Priority:</strong> ");

                out.println("<span class='priority'>");

                out.println(task.getPriority());

                out.println("</span>");

                out.println("</div>");

                // Status

                out.println("<div>");

                out.println("<strong>Status:</strong> ");

                out.println("<span class='status'>");

                out.println(task.getStatus());

                out.println("</span>");

                out.println("</div>");

                out.println("</div>");

                // Action buttons

                out.println("<div class='action-buttons'>");

                // Edit button

                out.println("<a class='edit-button' " +
                        "href='edit-task?id=" +
                        task.getId() +
                        "'>");

                out.println("Edit Task");

                out.println("</a>");

                // Delete button

                out.println("<a class='delete-button' " +
                        "href='delete-task?id=" +
                        task.getId() +
                        "' " +
                        "onclick=\"return confirm('Are you sure you want to delete this task?');\">");

                out.println("Delete Task");

                out.println("</a>");

                out.println("</div>");

                out.println("</div>");
            }

            out.println("</div>");
        }

        out.println("</div>");

        out.println("</body>");

        out.println("</html>");
    }


    // =========================
    // ADD TASK
    // =========================

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Check login session

        HttpSession session = request.getSession(false);

        if (session == null ||
            session.getAttribute("user") == null) {

            response.sendRedirect("login.html");

            return;
        }

        // Get logged-in user

        User user = (User) session.getAttribute("user");

        // Get form values

        String title =
                request.getParameter("title");

        String description =
                request.getParameter("description");

        String subjectIdString =
                request.getParameter("subjectId");

        String dueDateString =
                request.getParameter("dueDate");

        String priority =
                request.getParameter("priority");

        String status =
                request.getParameter("status");

        // Convert subject ID

        int subjectId =
                Integer.parseInt(subjectIdString);

        // Convert date

        Date dueDate =
                Date.valueOf(dueDateString);

        // Create Task object

        Task task = new Task(
                user.getId(),
                subjectId,
                title,
                description,
                dueDate,
                priority,
                status
        );

        // Save task

        TaskDAO taskDAO =
                new TaskDAOImpl();

        taskDAO.addTask(task);

        // =========================
        // UPDATE PROGRESS
        // =========================

        updateProgress(
                user.getId(),
                subjectId
        );

        // Return to Tasks page

        response.sendRedirect("tasks");
    }


    // =========================
    // UPDATE PROGRESS
    // =========================

    private void updateProgress(int userId,
                                int subjectId) {

        ProgressDAO progressDAO =
                new ProgressDAOImpl();

        int totalTasks =
                progressDAO.getTotalTasks(
                        userId,
                        subjectId
                );

        int completedTasks =
                progressDAO.getCompletedTasks(
                        userId,
                        subjectId
                );

        double progressPercentage = 0;

        if (totalTasks > 0) {

            progressPercentage =
                    ((double) completedTasks / totalTasks) * 100;
        }

        // Check whether progress already exists

        List<Progress> progressList =
                progressDAO.getProgressByUserId(userId);

        Progress existingProgress = null;

        for (Progress progress : progressList) {

            if (progress.getSubjectId() == subjectId) {

                existingProgress = progress;

                break;
            }
        }

        if (existingProgress == null) {

            Progress progress = new Progress(
                    userId,
                    subjectId,
                    completedTasks,
                    totalTasks,
                    progressPercentage
            );

            progressDAO.addProgress(progress);

        } else {

            existingProgress.setCompletedTasks(
                    completedTasks
            );

            existingProgress.setTotalTasks(
                    totalTasks
            );

            existingProgress.setProgressPercentage(
                    progressPercentage
            );

            progressDAO.updateProgress(
                    existingProgress
            );
        }
    }
}

