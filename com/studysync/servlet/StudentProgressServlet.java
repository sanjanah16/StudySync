
package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.studysync.DAO.ProgressDAO;
import com.studysync.DAO.SubjectDAO;
import com.studysync.DAO.UserDAO;

import com.studysync.DAOImpl.ProgressDAOImpl;
import com.studysync.DAOImpl.SubjectDAOImpl;
import com.studysync.DAOImpl.UserDAOImpl;

import com.studysync.model.Subject;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/student-progress")
public class StudentProgressServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // =========================
        // CHECK LOGIN SESSION
        // =========================

        HttpSession session =
                request.getSession(false);

        if (session == null ||
            session.getAttribute("user") == null) {

            response.sendRedirect("login.html");
            return;
        }

        // =========================
        // GET LOGGED-IN USER
        // =========================

        User mentorUser =
                (User) session.getAttribute("user");

        // Only MENTOR can view student progress
        if (!"MENTOR".equalsIgnoreCase(
                mentorUser.getRole())) {

            response.sendRedirect("dashboard");
            return;
        }

        // =========================
        // GET STUDENT ID
        // =========================

        String studentIdString =
                request.getParameter("id");

        if (studentIdString == null ||
            studentIdString.trim().isEmpty()) {

            response.sendRedirect("mentor-my-students");
            return;
        }

        int studentId;

        try {

            studentId =
                    Integer.parseInt(studentIdString);

        } catch (NumberFormatException e) {

            response.sendRedirect("mentor-my-students");
            return;
        }

        // =========================
        // GET STUDENT DETAILS
        // =========================

        UserDAO userDAO =
                new UserDAOImpl();

        User student =
                userDAO.getUser(studentId);

        if (student == null ||
            !"STUDENT".equalsIgnoreCase(
                    student.getRole())) {

            response.sendRedirect("mentor-my-students");
            return;
        }

        // =========================
        // GET SUBJECTS
        // =========================

        SubjectDAO subjectDAO =
                new SubjectDAOImpl();

        List<Subject> subjects =
                subjectDAO.getAllSubjects();

        // =========================
        // PROGRESS DAO
        // =========================

        ProgressDAO progressDAO =
                new ProgressDAOImpl();

        // =========================
        // RESPONSE SETTINGS
        // =========================

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out =
                response.getWriter();

        // =========================
        // HTML START
        // =========================

        out.println("<!DOCTYPE html>");

        out.println("<html>");

        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println("<meta name='viewport' "
                + "content='width=device-width, initial-scale=1.0'>");

        out.println("<title>StudySync - Student Progress</title>");

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
        out.println("color: #333;");
        out.println("}");

        // =========================
        // NAVBAR
        // =========================

        out.println(".navbar {");
        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("padding: 20px 50px;");
        out.println("color: white;");
        out.println("display: flex;");
        out.println("justify-content: space-between;");
        out.println("align-items: center;");
        out.println("box-shadow: 0 5px 20px rgba(0,0,0,0.15);");
        out.println("}");

        out.println(".logo {");
        out.println("font-size: 27px;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".nav-buttons {");
        out.println("display: flex;");
        out.println("gap: 10px;");
        out.println("}");

        out.println(".nav-button {");
        out.println("color: white;");
        out.println("text-decoration: none;");
        out.println("padding: 10px 18px;");
        out.println("border-radius: 20px;");
        out.println("background: rgba(255,255,255,0.2);");
        out.println("font-weight: bold;");
        out.println("transition: 0.3s;");
        out.println("}");

        out.println(".nav-button:hover {");
        out.println("background: rgba(255,255,255,0.35);");
        out.println("transform: translateY(-2px);");
        out.println("}");

        // =========================
        // CONTAINER
        // =========================

        out.println(".container {");
        out.println("width: 90%;");
        out.println("max-width: 1100px;");
        out.println("margin: 45px auto;");
        out.println("}");

        // =========================
        // STUDENT HEADER
        // =========================

        out.println(".student-header {");
        out.println("background: white;");
        out.println("padding: 30px;");
        out.println("border-radius: 20px;");
        out.println("box-shadow: 0 10px 25px rgba(0,0,0,0.08);");
        out.println("margin-bottom: 30px;");
        out.println("}");

        out.println(".student-header h1 {");
        out.println("color: #667eea;");
        out.println("margin-bottom: 15px;");
        out.println("}");

        out.println(".student-header p {");
        out.println("color: #666;");
        out.println("margin-top: 8px;");
        out.println("font-size: 15px;");
        out.println("}");

        // =========================
        // SUGGESTION BUTTON
        // =========================

        out.println(".suggestion-button {");
        out.println("display: inline-block;");
        out.println("margin-top: 20px;");
        out.println("padding: 13px 24px;");
        out.println("background: linear-gradient(135deg, #8e44ad, #667eea);");
        out.println("color: white;");
        out.println("text-decoration: none;");
        out.println("border-radius: 12px;");
        out.println("font-weight: bold;");
        out.println("font-size: 15px;");
        out.println("transition: 0.3s;");
        out.println("box-shadow: 0 6px 15px rgba(102,126,234,0.25);");
        out.println("}");

        out.println(".suggestion-button:hover {");
        out.println("transform: translateY(-3px);");
        out.println("box-shadow: 0 10px 22px rgba(102,126,234,0.35);");
        out.println("}");

        // =========================
        // PROGRESS GRID
        // =========================

        out.println(".progress-grid {");
        out.println("display: grid;");
        out.println("grid-template-columns: repeat(2, 1fr);");
        out.println("gap: 25px;");
        out.println("}");

        // =========================
        // PROGRESS CARD
        // =========================

        out.println(".progress-card {");
        out.println("background: white;");
        out.println("padding: 25px;");
        out.println("border-radius: 18px;");
        out.println("box-shadow: 0 8px 20px rgba(0,0,0,0.08);");
        out.println("transition: 0.3s;");
        out.println("}");

        out.println(".progress-card:hover {");
        out.println("transform: translateY(-5px);");
        out.println("box-shadow: 0 15px 30px rgba(0,0,0,0.12);");
        out.println("}");

        out.println(".progress-card h2 {");
        out.println("color: #4c1d95;");
        out.println("margin-bottom: 20px;");
        out.println("font-size: 22px;");
        out.println("}");

        // =========================
        // STATS
        // =========================

        out.println(".stats {");
        out.println("display: flex;");
        out.println("justify-content: space-between;");
        out.println("margin-bottom: 18px;");
        out.println("}");

        out.println(".stat {");
        out.println("text-align: center;");
        out.println("flex: 1;");
        out.println("}");

        out.println(".stat strong {");
        out.println("display: block;");
        out.println("font-size: 27px;");
        out.println("color: #667eea;");
        out.println("}");

        out.println(".stat span {");
        out.println("font-size: 13px;");
        out.println("color: #777;");
        out.println("}");

        // =========================
        // PROGRESS BAR
        // =========================

        out.println(".progress-bar {");
        out.println("height: 14px;");
        out.println("background: #eee;");
        out.println("border-radius: 20px;");
        out.println("overflow: hidden;");
        out.println("}");

        out.println(".progress-fill {");
        out.println("height: 100%;");
        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("border-radius: 20px;");
        out.println("transition: width 0.5s ease;");
        out.println("}");

        out.println(".percentage {");
        out.println("text-align: right;");
        out.println("margin-top: 10px;");
        out.println("font-weight: bold;");
        out.println("color: #667eea;");
        out.println("font-size: 15px;");
        out.println("}");

        // =========================
        // EMPTY
        // =========================

        out.println(".empty {");
        out.println("background: white;");
        out.println("padding: 45px;");
        out.println("border-radius: 18px;");
        out.println("text-align: center;");
        out.println("box-shadow: 0 8px 20px rgba(0,0,0,0.08);");
        out.println("}");

        out.println(".empty h2 {");
        out.println("color: #667eea;");
        out.println("margin-bottom: 10px;");
        out.println("}");

        out.println(".empty p {");
        out.println("color: #777;");
        out.println("}");

        // =========================
        // RESPONSIVE
        // =========================

        out.println("@media(max-width: 750px) {");

        out.println(".navbar {");
        out.println("padding: 20px;");
        out.println("flex-direction: column;");
        out.println("gap: 15px;");
        out.println("}");

        out.println(".nav-buttons {");
        out.println("flex-direction: column;");
        out.println("width: 100%;");
        out.println("}");

        out.println(".nav-button {");
        out.println("text-align: center;");
        out.println("}");

        out.println(".progress-grid {");
        out.println("grid-template-columns: 1fr;");
        out.println("}");

        out.println("}");

        out.println("</style>");

        out.println("</head>");

        // =========================
        // BODY
        // =========================

        out.println("<body>");

        // =========================
        // NAVBAR
        // =========================

        out.println("<div class='navbar'>");

        out.println("<div class='logo'>");

        out.println("StudySync");

        out.println("</div>");

        out.println("<div class='nav-buttons'>");

        out.println("<a class='nav-button' "
                + "href='mentor-my-students'>");

        out.println("My Students");

        out.println("</a>");

        out.println("<a class='nav-button' "
                + "href='mentor-dashboard'>");

        out.println("Dashboard");

        out.println("</a>");

        out.println("<a class='nav-button' "
                + "href='logout'>");

        out.println("Logout");

        out.println("</a>");

        out.println("</div>");

        out.println("</div>");

        // =========================
        // MAIN CONTAINER
        // =========================

        out.println("<div class='container'>");

        // =========================
        // STUDENT INFORMATION
        // =========================

        out.println("<div class='student-header'>");

        out.println("<h1>");

        out.println("Student Progress");

        out.println("</h1>");

        out.println("<p>");

        out.println("<strong>Student:</strong> ");

        out.println(student.getName());

        out.println("</p>");

        out.println("<p>");

        out.println("<strong>Email:</strong> ");

        out.println(student.getEmail());

        out.println("</p>");

        if (student.getPhone() != null &&
            !student.getPhone().trim().isEmpty()) {

            out.println("<p>");

            out.println("<strong>Phone:</strong> ");

            out.println(student.getPhone());

            out.println("</p>");
        }

        // =========================
        // GIVE SUGGESTION BUTTON
        // =========================

        out.println("<a class='suggestion-button' "
                + "href='add-suggestion?studentId="
                + studentId
                + "'>");

        out.println("Give Suggestion");

        out.println("</a>");

        out.println("</div>");

        // =========================
        // SUBJECTS
        // =========================

        if (subjects == null ||
            subjects.isEmpty()) {

            out.println("<div class='empty'>");

            out.println("<h2>No Subjects Available</h2>");

            out.println("<p>");

            out.println(
                    "There are no subjects available to calculate progress."
            );

            out.println("</p>");

            out.println("</div>");

        } else {

            out.println("<div class='progress-grid'>");

            // =========================
            // LOOP THROUGH SUBJECTS
            // =========================

            for (Subject subject : subjects) {

                int totalTasks =
                        progressDAO.getTotalTasks(
                                studentId,
                                subject.getId()
                        );

                int completedTasks =
                        progressDAO.getCompletedTasks(
                                studentId,
                                subject.getId()
                        );

                double percentage = 0;

                if (totalTasks > 0) {

                    percentage =
                            ((double) completedTasks
                            / totalTasks) * 100;
                }

                // Round to 2 decimal places
                percentage =
                        Math.round(
                                percentage * 100.0
                        ) / 100.0;

                // Make sure percentage doesn't exceed 100
                if (percentage > 100) {

                    percentage = 100;
                }

                // =========================
                // PROGRESS CARD
                // =========================

                out.println("<div class='progress-card'>");

                out.println("<h2>");

                out.println(subject.getName());

                out.println("</h2>");

                // =========================
                // STATS
                // =========================

                out.println("<div class='stats'>");

                // Total Tasks
                out.println("<div class='stat'>");

                out.println("<strong>");

                out.println(totalTasks);

                out.println("</strong>");

                out.println("<span>Total Tasks</span>");

                out.println("</div>");

                // Completed Tasks
                out.println("<div class='stat'>");

                out.println("<strong>");

                out.println(completedTasks);

                out.println("</strong>");

                out.println("<span>Completed</span>");

                out.println("</div>");

                out.println("</div>");

                // =========================
                // PROGRESS BAR
                // =========================

                out.println("<div class='progress-bar'>");

                out.println("<div class='progress-fill' "
                        + "style='width:"
                        + percentage
                        + "%;'>");

                out.println("</div>");

                out.println("</div>");

                // Percentage
                out.println("<div class='percentage'>");

                out.println(percentage + "%");

                out.println("</div>");

                out.println("</div>");
            }

            out.println("</div>");
        }

        // =========================
        // HTML END
        // =========================

        out.println("</div>");

        out.println("</body>");

        out.println("</html>");
    }
}
