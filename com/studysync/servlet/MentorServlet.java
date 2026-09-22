
package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.studysync.DAO.MentorDAO;
import com.studysync.DAOImpl.MentorDAOImpl;
import com.studysync.model.Mentor;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/mentor")
public class MentorServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Check login
        HttpSession session =
                request.getSession(false);

        if (session == null ||
            session.getAttribute("user") == null) {

            response.sendRedirect("login.html");
            return;
        }

        // Get logged-in user
        User user =
                (User) session.getAttribute("user");

        // Only mentor can access this page
        if (!"MENTOR".equalsIgnoreCase(
                user.getRole())) {

            response.sendRedirect("dashboard");
            return;
        }

        // Get mentor details
        MentorDAO mentorDAO =
                new MentorDAOImpl();

        Mentor mentor =
                mentorDAO.getMentorByUserId(
                        user.getId()
                );

        // If mentor profile doesn't exist
        if (mentor == null) {

            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");

            PrintWriter out =
                    response.getWriter();

            out.println("<h2>Mentor profile not found.</h2>");
            out.println("<a href='mentor-dashboard'>Back to Dashboard</a>");

            return;
        }

        // =========================
        // HTML
        // =========================

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out =
                response.getWriter();

        out.println("<!DOCTYPE html>");

        out.println("<html>");

        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println("<meta name='viewport' "
                + "content='width=device-width, initial-scale=1.0'>");

        out.println("<title>StudySync - Mentor Profile</title>");

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

        // NAVBAR

        out.println(".navbar {");
        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("padding: 20px 50px;");
        out.println("display: flex;");
        out.println("justify-content: space-between;");
        out.println("align-items: center;");
        out.println("color: white;");
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
        out.println("}");

        // CONTAINER

        out.println(".container {");
        out.println("width: 90%;");
        out.println("max-width: 900px;");
        out.println("margin: 50px auto;");
        out.println("}");

        // PROFILE CARD

        out.println(".profile-card {");
        out.println("background: white;");
        out.println("padding: 40px;");
        out.println("border-radius: 25px;");
        out.println("box-shadow: 0 12px 30px rgba(0,0,0,0.10);");
        out.println("}");

        out.println(".profile-title {");
        out.println("text-align: center;");
        out.println("margin-bottom: 30px;");
        out.println("}");

        out.println(".profile-title h1 {");
        out.println("color: #667eea;");
        out.println("margin-bottom: 8px;");
        out.println("}");

        out.println(".profile-title p {");
        out.println("color: #777;");
        out.println("}");

        // INFO

        out.println(".info-grid {");
        out.println("display: grid;");
        out.println("grid-template-columns: repeat(2, 1fr);");
        out.println("gap: 20px;");
        out.println("}");

        out.println(".info-box {");
        out.println("background: #f8f7ff;");
        out.println("padding: 20px;");
        out.println("border-radius: 15px;");
        out.println("border-left: 5px solid #667eea;");
        out.println("}");

        out.println(".info-box h3 {");
        out.println("color: #667eea;");
        out.println("margin-bottom: 8px;");
        out.println("}");

        out.println(".info-box p {");
        out.println("color: #555;");
        out.println("line-height: 1.5;");
        out.println("}");

        out.println(".bio {");
        out.println("grid-column: 1 / -1;");
        out.println("}");

        // RESPONSIVE

        out.println("@media(max-width: 700px) {");

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

        out.println(".info-grid {");
        out.println("grid-template-columns: 1fr;");
        out.println("}");

        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        // =========================
        // NAVBAR
        // =========================

        out.println("<div class='navbar'>");

        out.println("<div class='logo'>");
        out.println("StudySync");
        out.println("</div>");

        out.println("<div class='nav-buttons'>");

        // Dashboard
        out.println("<a class='nav-button' "
                + "href='mentor-dashboard'>");

        out.println("Dashboard");

        out.println("</a>");

        // My Students
        out.println("<a class='nav-button' "
                + "href='mentor-my-students'>");

        out.println("My Students");

        out.println("</a>");

        // Logout
        out.println("<a class='nav-button' "
                + "href='logout'>");

        out.println("Logout");

        out.println("</a>");

        out.println("</div>");

        out.println("</div>");

        // =========================
        // MAIN
        // =========================

        out.println("<div class='container'>");

        out.println("<div class='profile-card'>");

        out.println("<div class='profile-title'>");

        out.println("<h1>Mentor Profile</h1>");

        out.println("<p>StudySync Mentor Information</p>");

        out.println("</div>");

        out.println("<div class='info-grid'>");

        // Name
        out.println("<div class='info-box'>");

        out.println("<h3>Name</h3>");

        out.println("<p>");

        out.println(user.getName());

        out.println("</p>");

        out.println("</div>");

        // Email
        out.println("<div class='info-box'>");

        out.println("<h3>Email</h3>");

        out.println("<p>");

        out.println(user.getEmail());

        out.println("</p>");

        out.println("</div>");

        // Phone
        out.println("<div class='info-box'>");

        out.println("<h3>Phone</h3>");

        out.println("<p>");

        out.println(user.getPhone());

        out.println("</p>");

        out.println("</div>");

        // Specialization
        out.println("<div class='info-box'>");

        out.println("<h3>Specialization</h3>");

        out.println("<p>");

        out.println(mentor.getSpecialization());

        out.println("</p>");

        out.println("</div>");

        // Experience
        out.println("<div class='info-box'>");

        out.println("<h3>Experience</h3>");

        out.println("<p>");

        out.println(mentor.getExperienceYears());

        out.println(" years");

        out.println("</p>");

        out.println("</div>");

        // Bio
        out.println("<div class='info-box bio'>");

        out.println("<h3>About Mentor</h3>");

        out.println("<p>");

        out.println(mentor.getBio());

        out.println("</p>");

        out.println("</div>");

        out.println("</div>");

        out.println("</div>");

        out.println("</div>");

        out.println("</body>");

        out.println("</html>");
    }
}
