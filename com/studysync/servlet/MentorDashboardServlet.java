
package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.studysync.DAO.MentorStudentDAO;
import com.studysync.DAOImpl.MentorStudentDAOImpl;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/mentor-dashboard")
public class MentorDashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Check login
        if (session == null ||
            session.getAttribute("user") == null) {

            response.sendRedirect("login.html");
            return;
        }

        // Get logged-in user
        User mentorUser =
                (User) session.getAttribute("user");

        // Check mentor role
        if (!"MENTOR".equalsIgnoreCase(mentorUser.getRole())) {

            response.sendRedirect("dashboard");
            return;
        }

        /*
         * mentor table ID is different from user ID.
         *
         * For our current database:
         * Rahul Mentor user_id = 2
         * Rahul Mentor mentor id = 1
         *
         * We are using mentor id = 1 for now.
         */

        int mentorId = 1;

        MentorStudentDAO mentorStudentDAO =
                new MentorStudentDAOImpl();

        List<User> students =
                mentorStudentDAO.getStudentsByMentorId(mentorId);

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out =
                response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println("<meta name='viewport' "
                + "content='width=device-width, initial-scale=1.0'>");

        out.println("<title>StudySync - Mentor Dashboard</title>");

        out.println("<style>");

        out.println("* {");
        out.println("box-sizing: border-box;");
        out.println("margin: 0;");
        out.println("padding: 0;");
        out.println("}");

        out.println("body {");
        out.println("font-family: Arial, sans-serif;");
        out.println("background: linear-gradient(135deg, #eef2ff, #fdf2f8);");
        out.println("min-height: 100vh;");
        out.println("}");

        /* NAVBAR */

        out.println(".navbar {");
        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("color: white;");
        out.println("padding: 20px 50px;");
        out.println("display: flex;");
        out.println("justify-content: space-between;");
        out.println("align-items: center;");
        out.println("}");

        out.println(".logo {");
        out.println("font-size: 27px;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".nav-buttons {");
        out.println("display: flex;");
        out.println("gap: 12px;");
        out.println("}");

        out.println(".nav-button {");
        out.println("color: white;");
        out.println("text-decoration: none;");
        out.println("padding: 10px 18px;");
        out.println("border-radius: 20px;");
        out.println("background: rgba(255,255,255,0.20);");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".logout {");
        out.println("background: #ff416c;");
        out.println("}");

        /* CONTAINER */

        out.println(".container {");
        out.println("width: 90%;");
        out.println("max-width: 1150px;");
        out.println("margin: 40px auto;");
        out.println("}");

        /* WELCOME */

        out.println(".welcome {");
        out.println("background: white;");
        out.println("padding: 30px;");
        out.println("border-radius: 20px;");
        out.println("box-shadow: 0 10px 25px rgba(0,0,0,0.08);");
        out.println("margin-bottom: 30px;");
        out.println("}");

        out.println(".welcome h1 {");
        out.println("color: #333;");
        out.println("margin-bottom: 10px;");
        out.println("}");

        out.println(".welcome p {");
        out.println("color: #777;");
        out.println("font-size: 16px;");
        out.println("}");

        /* STAT */

        out.println(".stat-card {");
        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("color: white;");
        out.println("padding: 25px;");
        out.println("border-radius: 18px;");
        out.println("margin-bottom: 30px;");
        out.println("box-shadow: 0 8px 20px rgba(0,0,0,0.10);");
        out.println("}");

        out.println(".stat-card h2 {");
        out.println("font-size: 18px;");
        out.println("margin-bottom: 10px;");
        out.println("}");

        out.println(".stat-number {");
        out.println("font-size: 40px;");
        out.println("font-weight: bold;");
        out.println("}");

        /* SECTION */

        out.println(".section-title {");
        out.println("margin-bottom: 20px;");
        out.println("color: #333;");
        out.println("}");

        /* STUDENT GRID */

        out.println(".student-grid {");
        out.println("display: grid;");
        out.println("grid-template-columns: repeat(3, 1fr);");
        out.println("gap: 25px;");
        out.println("}");

        /* STUDENT CARD */

        out.println(".student-card {");
        out.println("background: white;");
        out.println("padding: 25px;");
        out.println("border-radius: 18px;");
        out.println("box-shadow: 0 8px 20px rgba(0,0,0,0.08);");
        out.println("transition: 0.3s;");
        out.println("}");

        out.println(".student-card:hover {");
        out.println("transform: translateY(-6px);");
        out.println("box-shadow: 0 15px 30px rgba(0,0,0,0.12);");
        out.println("}");

        out.println(".student-icon {");
        out.println("width: 65px;");
        out.println("height: 65px;");
        out.println("border-radius: 50%;");
        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("color: white;");
        out.println("display: flex;");
        out.println("align-items: center;");
        out.println("justify-content: center;");
        out.println("font-size: 25px;");
        out.println("margin-bottom: 18px;");
        out.println("}");

        out.println(".student-card h2 {");
        out.println("color: #667eea;");
        out.println("margin-bottom: 12px;");
        out.println("}");

        out.println(".student-info {");
        out.println("color: #666;");
        out.println("line-height: 1.9;");
        out.println("}");

        out.println(".progress-button {");
        out.println("display: block;");
        out.println("margin-top: 18px;");
        out.println("padding: 11px;");
        out.println("text-align: center;");
        out.println("background: #667eea;");
        out.println("color: white;");
        out.println("text-decoration: none;");
        out.println("border-radius: 10px;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".progress-button:hover {");
        out.println("background: #5568d9;");
        out.println("}");

        /* EMPTY */

        out.println(".empty {");
        out.println("background: white;");
        out.println("padding: 40px;");
        out.println("border-radius: 18px;");
        out.println("text-align: center;");
        out.println("color: #777;");
        out.println("}");

        /* RESPONSIVE */

        out.println("@media(max-width: 850px) {");

        out.println(".student-grid {");
        out.println("grid-template-columns: 1fr;");
        out.println("}");

        out.println(".navbar {");
        out.println("padding: 20px;");
        out.println("}");

        out.println(".nav-buttons {");
        out.println("flex-direction: column;");
        out.println("}");

        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        /* NAVBAR */

        out.println("<div class='navbar'>");

        out.println("<div class='logo'>StudySync | Mentor</div>");

        out.println("<div class='nav-buttons'>");

        out.println("<a class='nav-button' href='mentor-dashboard'>");
        out.println("Dashboard");
        out.println("</a>");

        out.println("<a class='nav-button logout' href='logout'>");
        out.println("Logout");
        out.println("</a>");

        out.println("</div>");

        out.println("</div>");

        /* MAIN */

        out.println("<div class='container'>");

        out.println("<div class='welcome'>");

        out.println("<h1>Welcome, "
                + mentorUser.getName()
                + "!</h1>");

        out.println("<p>");
        out.println("Manage your students and monitor their learning progress.");
        out.println("</p>");

        out.println("</div>");

        /* STUDENT COUNT */

        out.println("<div class='stat-card'>");

        out.println("<h2>Total Assigned Students</h2>");

        out.println("<div class='stat-number'>"
                + students.size()
                + "</div>");

        out.println("</div>");

        /* STUDENTS */

        out.println("<h2 class='section-title'>My Students</h2>");

        if (students.isEmpty()) {

            out.println("<div class='empty'>");

            out.println("<h2>No Students Assigned</h2>");

            out.println("<p>");
            out.println("Students assigned to you will appear here.");
            out.println("</p>");

            out.println("</div>");

        } else {

            out.println("<div class='student-grid'>");

            for (User student : students) {

                out.println("<div class='student-card'>");

                out.println("<div class='student-icon'>");
                out.println("S");
                out.println("</div>");

                out.println("<h2>"
                        + student.getName()
                        + "</h2>");

                out.println("<div class='student-info'>");

                out.println("<div>Email: "
                        + student.getEmail()
                        + "</div>");

                out.println("<div>Phone: "
                        + student.getPhone()
                        + "</div>");

                out.println("<div>Role: "
                        + student.getRole()
                        + "</div>");

                out.println("</div>");

                out.println("<a class='progress-button' "
                        + "href='student-progress?id="
                        + student.getId()
                        + "'>");

                out.println("View Progress");

                out.println("</a>");

                out.println("</div>");
            }

            out.println("</div>");
        }

        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }
}

