
package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import com.studysync.DAO.ProgressDAO;
import com.studysync.DAO.SubjectDAO;
import com.studysync.DAO.TaskDAO;

import com.studysync.DAOImpl.ProgressDAOImpl;
import com.studysync.DAOImpl.SubjectDAOImpl;
import com.studysync.DAOImpl.TaskDAOImpl;

import com.studysync.model.Progress;
import com.studysync.model.Subject;
import com.studysync.model.Task;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/add-task")
public class AddTaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
            session.getAttribute("user") == null) {

            response.sendRedirect("login.html");
            return;
        }

        SubjectDAO subjectDAO = new SubjectDAOImpl();

        List<Subject> subjects =
                subjectDAO.getAllSubjects();

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");

        out.println("<html>");

        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println("<meta name='viewport' "
                + "content='width=device-width, initial-scale=1.0'>");

        out.println("<title>StudySync - Add Task</title>");

        out.println("<style>");

        out.println("* {");

        out.println("margin: 0;");

        out.println("padding: 0;");

        out.println("box-sizing: border-box;");

        out.println("}");

        out.println("body {");

        out.println("font-family: Arial, sans-serif;");

        out.println("min-height: 100vh;");

        out.println("background: linear-gradient(135deg, #eef2ff, #fdf2f8);");

        out.println("}");

        out.println(".navbar {");

        out.println("height: 75px;");

        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");

        out.println("display: flex;");

        out.println("align-items: center;");

        out.println("justify-content: space-between;");

        out.println("padding: 0 50px;");

        out.println("color: white;");

        out.println("box-shadow: 0 4px 15px rgba(0,0,0,0.15);");

        out.println("}");

        out.println(".logo {");

        out.println("font-size: 28px;");

        out.println("font-weight: bold;");

        out.println("}");

        out.println(".back {");

        out.println("text-decoration: none;");

        out.println("color: white;");

        out.println("background: rgba(255,255,255,0.2);");

        out.println("padding: 10px 20px;");

        out.println("border-radius: 25px;");

        out.println("font-weight: bold;");

        out.println("}");

        out.println(".container {");

        out.println("width: 90%;");

        out.println("max-width: 700px;");

        out.println("margin: 45px auto;");

        out.println("}");

        out.println(".form-card {");

        out.println("background: white;");

        out.println("padding: 40px;");

        out.println("border-radius: 22px;");

        out.println("box-shadow: 0 10px 30px rgba(0,0,0,0.10);");

        out.println("}");

        out.println(".form-card h1 {");

        out.println("color: #667eea;");

        out.println("margin-bottom: 10px;");

        out.println("}");

        out.println(".subtitle {");

        out.println("color: #777;");

        out.println("margin-bottom: 30px;");

        out.println("}");

        out.println(".form-group {");

        out.println("margin-bottom: 20px;");

        out.println("}");

        out.println(".form-group label {");

        out.println("display: block;");

        out.println("margin-bottom: 8px;");

        out.println("font-weight: bold;");

        out.println("color: #444;");

        out.println("}");

        out.println(".form-group input,");

        out.println(".form-group textarea,");

        out.println(".form-group select {");

        out.println("width: 100%;");

        out.println("padding: 13px;");

        out.println("border: 1px solid #ddd;");

        out.println("border-radius: 10px;");

        out.println("font-size: 15px;");

        out.println("outline: none;");

        out.println("}");

        out.println(".form-group textarea {");

        out.println("height: 100px;");

        out.println("resize: vertical;");

        out.println("}");

        out.println(".form-row {");

        out.println("display: grid;");

        out.println("grid-template-columns: 1fr 1fr;");

        out.println("gap: 20px;");

        out.println("}");

        out.println(".add-button {");

        out.println("width: 100%;");

        out.println("padding: 14px;");

        out.println("border: none;");

        out.println("border-radius: 12px;");

        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");

        out.println("color: white;");

        out.println("font-size: 17px;");

        out.println("font-weight: bold;");

        out.println("cursor: pointer;");

        out.println("}");

        out.println("@media(max-width: 600px) {");

        out.println(".navbar {");

        out.println("padding: 0 20px;");

        out.println("}");

        out.println(".form-card {");

        out.println("padding: 25px;");

        out.println("}");

        out.println(".form-row {");

        out.println("grid-template-columns: 1fr;");

        out.println("gap: 0;");

        out.println("}");

        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        out.println("<div class='navbar'>");

        out.println("<div class='logo'>📚 StudySync</div>");

        out.println("<a href='tasks' class='back'>");

        out.println("← My Tasks");

        out.println("</a>");

        out.println("</div>");

        out.println("<div class='container'>");

        out.println("<div class='form-card'>");

        out.println("<h1>➕ Add New Task</h1>");

        out.println("<p class='subtitle'>");

        out.println("Create a study task and keep your learning organized.");

        out.println("</p>");

        /*
         * IMPORTANT:
         *
         * The form now submits to /add-task,
         * so this same servlet handles the POST.
         */

        out.println("<form action='add-task' method='post'>");

        out.println("<div class='form-group'>");

        out.println("<label>Task Title</label>");

        out.println("<input type='text'");

        out.println("name='title'");

        out.println("placeholder='Example: Complete Java OOP'");

        out.println("required>");

        out.println("</div>");

        out.println("<div class='form-group'>");

        out.println("<label>Description</label>");

        out.println("<textarea name='description'");

        out.println("placeholder='Enter task description'");

        out.println("required></textarea>");

        out.println("</div>");

        out.println("<div class='form-group'>");

        out.println("<label>Subject</label>");

        out.println("<select name='subjectId' required>");

        out.println("<option value=''>");

        out.println("Select Subject");

        out.println("</option>");

        for (Subject subject : subjects) {

            out.println("<option value='"
                    + subject.getId()
                    + "'>");

            out.println(subject.getName());

            out.println("</option>");
        }

        out.println("</select>");

        out.println("</div>");

        out.println("<div class='form-row'>");

        out.println("<div class='form-group'>");

        out.println("<label>Due Date</label>");

        out.println("<input type='date'");

        out.println("name='dueDate'");

        out.println("required>");

        out.println("</div>");

        out.println("<div class='form-group'>");

        out.println("<label>Priority</label>");

        out.println("<select name='priority' required>");

        out.println("<option value='LOW'>Low</option>");

        out.println("<option value='MEDIUM' selected>");

        out.println("Medium");

        out.println("</option>");

        out.println("<option value='HIGH'>High</option>");

        out.println("</select>");

        out.println("</div>");

        out.println("</div>");

        out.println("<div class='form-group'>");

        out.println("<label>Status</label>");

        out.println("<select name='status' required>");

        out.println("<option value='PENDING' selected>");

        out.println("Pending");

        out.println("</option>");

        out.println("<option value='IN_PROGRESS'>");

        out.println("In Progress");

        out.println("</option>");

        out.println("<option value='COMPLETED'>");

        out.println("Completed");

        out.println("</option>");

        out.println("</select>");

        out.println("</div>");

        out.println("<button type='submit' class='add-button'>");

        out.println("Add Task");

        out.println("</button>");

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

        /*
         * Check login session.
         */

        HttpSession session =
                request.getSession(false);

        if (session == null ||
            session.getAttribute("user") == null) {

            response.sendRedirect("login.html");
            return;
        }


        /*
         * Get logged-in user.
         */

        User user =
                (User) session.getAttribute("user");


        /*
         * Get form values.
         */

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


        /*
         * Convert subject ID.
         */

        int subjectId =
                Integer.parseInt(subjectIdString);


        /*
         * Convert date.
         */

        Date dueDate =
                Date.valueOf(dueDateString);


        /*
         * Create Task object.
         */

        Task task =
                new Task(
                        user.getId(),
                        subjectId,
                        title,
                        description,
                        dueDate,
                        priority,
                        status
                );


        /*
         * Save task into database.
         */

        TaskDAO taskDAO =
                new TaskDAOImpl();

        taskDAO.addTask(task);


        /*
         * Update progress automatically.
         */

        updateProgress(
                user.getId(),
                subjectId
        );


        /*
         * Return to My Tasks page.
         */

        response.sendRedirect("tasks");
    }


    /*
     * Recalculate progress for the
     * selected subject.
     */

    private void updateProgress(
            int userId,
            int subjectId) {

        ProgressDAO progressDAO =
                new ProgressDAOImpl();


        /*
         * Get total number of tasks.
         */

        int totalTasks =
                progressDAO.getTotalTasks(
                        userId,
                        subjectId
                );


        /*
         * Get completed number of tasks.
         */

        int completedTasks =
                progressDAO.getCompletedTasks(
                        userId,
                        subjectId
                );


        /*
         * Calculate percentage.
         */

        double progressPercentage = 0;

        if (totalTasks > 0) {

            progressPercentage =
                    ((double) completedTasks
                    / totalTasks) * 100;
        }


        /*
         * Find existing progress record.
         */

        List<Progress> progressList =
                progressDAO.getProgressByUserId(
                        userId
                );

        Progress existingProgress = null;


        for (Progress progress : progressList) {

            if (progress.getSubjectId()
                    == subjectId) {

                existingProgress = progress;

                break;
            }
        }


        /*
         * Create progress if it does not exist.
         */

        if (existingProgress == null) {

            Progress progress =
                    new Progress(
                            userId,
                            subjectId,
                            completedTasks,
                            totalTasks,
                            progressPercentage
                    );

            progressDAO.addProgress(progress);

        } else {

            /*
             * Update existing progress.
             */

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

