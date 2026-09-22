
package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.studysync.DAO.MentorDAO;
import com.studysync.DAO.MentorStudentDAO;
import com.studysync.DAOImpl.MentorDAOImpl;
import com.studysync.DAOImpl.MentorStudentDAOImpl;
import com.studysync.model.Mentor;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/mentor-my-students")
public class MentorMyStudentsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

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

        User loggedUser =
                (User) session.getAttribute("user");

        /* ONLY MENTOR CAN ACCESS */

        if (!"MENTOR".equalsIgnoreCase(loggedUser.getRole())) {

            response.sendRedirect("dashboard");
            return;
        }

        /* FIND MENTOR PROFILE */

        MentorDAO mentorDAO =
                new MentorDAOImpl();

        Mentor mentor =
                mentorDAO.getMentorByUserId(loggedUser.getId());

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out =
                response.getWriter();

        /* HTML */

        out.println("<!DOCTYPE html>");
        out.println("<html>");

        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println("<meta name='viewport' "
                + "content='width=device-width, initial-scale=1.0'>");

        out.println("<title>StudySync - My Students</title>");

        out.println("<style>");

        /* COMMON */

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

        /* NAVBAR */

        out.println(".navbar {");
        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("padding: 20px 50px;");
        out.println("display: flex;");
        out.println("justify-content: space-between;");
        out.println("align-items: center;");
        out.println("color: white;");
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

        /* CONTAINER */

        out.println(".container {");
        out.println("width: 90%;");
        out.println("max-width: 1100px;");
        out.println("margin: 45px auto;");
        out.println("}");

        out.println(".heading {");
        out.println("text-align: center;");
        out.println("margin-bottom: 35px;");
        out.println("}");

        out.println(".heading h1 {");
        out.println("font-size: 36px;");
        out.println("color: #333;");
        out.println("margin-bottom: 10px;");
        out.println("}");

        out.println(".heading p {");
        out.println("color: #777;");
        out.println("font-size: 17px;");
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
        out.println("border-radius: 20px;");
        out.println("box-shadow: 0 10px 25px rgba(0,0,0,0.08);");
        out.println("transition: 0.3s;");
        out.println("}");

        out.println(".student-card:hover {");
        out.println("transform: translateY(-6px);");
        out.println("box-shadow: 0 15px 30px rgba(0,0,0,0.12);");
        out.println("}");

        /* STUDENT ICON */

        out.println(".student-icon {");
        out.println("width: 70px;");
        out.println("height: 70px;");
        out.println("border-radius: 50%;");
        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("color: white;");
        out.println("display: flex;");
        out.println("align-items: center;");
        out.println("justify-content: center;");
        out.println("font-size: 28px;");
        out.println("font-weight: bold;");
        out.println("margin-bottom: 18px;");
        out.println("}");

        out.println(".student-card h2 {");
        out.println("color: #667eea;");
        out.println("margin-bottom: 10px;");
        out.println("}");

        out.println(".student-card p {");
        out.println("color: #666;");
        out.println("margin-bottom: 8px;");
        out.println("}");

        /* BUTTON */

        out.println(".progress-button {");
        out.println("display: inline-block;");
        out.println("margin-top: 18px;");
        out.println("padding: 11px 18px;");
        out.println("border-radius: 10px;");
        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("color: white;");
        out.println("text-decoration: none;");
        out.println("font-weight: bold;");
        out.println("transition: 0.3s;");
        out.println("}");

        out.println(".progress-button:hover {");
        out.println("transform: translateY(-2px);");
        out.println("box-shadow: 0 6px 15px rgba(102,126,234,0.3);");
        out.println("}");

        /* EMPTY */

        out.println(".empty {");
        out.println("background: white;");
        out.println("padding: 50px;");
        out.println("border-radius: 20px;");
        out.println("text-align: center;");
        out.println("box-shadow: 0 10px 25px rgba(0,0,0,0.08);");
        out.println("}");

        out.println(".empty h2 {");
        out.println("color: #667eea;");
        out.println("margin-bottom: 10px;");
        out.println("}");

        out.println(".empty p {");
        out.println("color: #777;");
        out.println("}");

        /* RESPONSIVE */

        out.println("@media(max-width: 800px) {");

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

        out.println("<div class='logo'>StudySync Mentor</div>");

        out.println("<div class='nav-buttons'>");

        out.println("<a class='nav-button' "
                + "href='mentor-dashboard'>");
        out.println("Dashboard");
        out.println("</a>");

        out.println("<a class='nav-button' "
                + "href='mentor'>");
        out.println("Profile");
        out.println("</a>");

        out.println("<a class='nav-button' "
                + "href='logout'>");
        out.println("Logout");
        out.println("</a>");

        out.println("</div>");

        out.println("</div>");

        /* MAIN */

        out.println("<div class='container'>");

        out.println("<div class='heading'>");

        out.println("<h1>My Students</h1>");

        out.println("<p>");
        out.println("View the students assigned to you and monitor their progress.");
        out.println("</p>");

        out.println("</div>");

        /* CHECK MENTOR */

        if (mentor == null) {

            out.println("<div class='empty'>");

            out.println("<h2>Mentor Profile Not Found</h2>");

            out.println("<p>");
            out.println("Your mentor profile has not been created yet.");
            out.println("</p>");

            out.println("</div>");

        } else {

            MentorStudentDAO mentorStudentDAO =
                    new MentorStudentDAOImpl();

            List<User> students =
                    mentorStudentDAO.getStudentsByMentorId(
                            mentor.getId()
                    );

            /* NO STUDENTS */

            if (students.isEmpty()) {

                out.println("<div class='empty'>");

                out.println("<h2>No Students Assigned</h2>");

                out.println("<p>");
                out.println("You currently do not have any students assigned to you.");
                out.println("</p>");

                out.println("</div>");

            } else {

                out.println("<div class='student-grid'>");

                /* STUDENT CARDS */

                for (User student : students) {

                    out.println("<div class='student-card'>");

                    out.println("<div class='student-icon'>");

                    String name =
                            student.getName();

                    if (name != null &&
                        !name.isEmpty()) {

                        out.println(
                                name.substring(0, 1).toUpperCase()
                        );

                    } else {

                        out.println("S");
                    }

                    out.println("</div>");

                    out.println("<h2>");

                    out.println(student.getName());

                    out.println("</h2>");

                    out.println("<p>");

                    out.println("<strong>Email:</strong> "
                            + student.getEmail());

                    out.println("</p>");

                    out.println("<p>");

                    out.println("<strong>Phone:</strong> "
                            + student.getPhone());

                    out.println("</p>");

                    /* VIEW PROGRESS */

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
        }

        out.println("</div>");

        out.println("</body>");

        out.println("</html>");
    }
}

