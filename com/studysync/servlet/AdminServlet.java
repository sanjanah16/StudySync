package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.studysync.DAO.MentorDAO;
import com.studysync.DAO.MentorStudentDAO;
import com.studysync.DAO.SubjectDAO;
import com.studysync.DAOImpl.MentorDAOImpl;
import com.studysync.DAOImpl.MentorStudentDAOImpl;
import com.studysync.DAOImpl.SubjectDAOImpl;
import com.studysync.model.Mentor;
import com.studysync.model.Subject;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin-dashboard")
public class AdminServlet extends HttpServlet {

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

        User loggedInUser =
                (User) session.getAttribute("user");

        if (!"ADMIN".equalsIgnoreCase(loggedInUser.getRole())) {

            response.sendRedirect("dashboard");
            return;
        }

        MentorDAO mentorDAO =
                new MentorDAOImpl();

        SubjectDAO subjectDAO =
                new SubjectDAOImpl();

        MentorStudentDAO mentorStudentDAO =
                new MentorStudentDAOImpl();

        List<User> students =
                mentorStudentDAO.getAllStudents();

        List<Mentor> mentors =
                mentorDAO.getAllMentors();

        List<Subject> subjects =
                subjectDAO.getAllSubjects();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out =
                response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println("<meta name='viewport' " +
                "content='width=device-width, initial-scale=1.0'>");

        out.println("<title>StudySync - Admin Dashboard</title>");

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

        /* NAVBAR */

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
        out.println("}");

        out.println(".nav-button:hover {");
        out.println("background: rgba(255,255,255,0.35);");
        out.println("}");

        /* CONTAINER */

        out.println(".container {");
        out.println("width: 90%;");
        out.println("max-width: 1200px;");
        out.println("margin: 45px auto;");
        out.println("}");

        out.println(".heading {");
        out.println("text-align: center;");
        out.println("margin-bottom: 35px;");
        out.println("}");

        out.println(".heading h1 {");
        out.println("font-size: 38px;");
        out.println("color: #333;");
        out.println("margin-bottom: 10px;");
        out.println("}");

        out.println(".heading p {");
        out.println("color: #777;");
        out.println("font-size: 17px;");
        out.println("}");

        /* MANAGEMENT BUTTONS */

        out.println(".management-buttons {");
        out.println("display: flex;");
        out.println("justify-content: center;");
        out.println("gap: 15px;");
        out.println("margin-bottom: 40px;");
        out.println("flex-wrap: wrap;");
        out.println("}");

        out.println(".management-button {");
        out.println("display: inline-block;");
        out.println("padding: 13px 22px;");
        out.println("border-radius: 12px;");
        out.println("text-decoration: none;");
        out.println("color: white;");
        out.println("font-weight: bold;");
        out.println("font-size: 15px;");
        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("box-shadow: 0 6px 15px rgba(102,126,234,0.25);");
        out.println("transition: 0.3s;");
        out.println("}");

        out.println(".management-button:hover {");
        out.println("transform: translateY(-3px);");
        out.println("box-shadow: 0 10px 20px rgba(102,126,234,0.35);");
        out.println("}");

        /* STAT CARDS */

        out.println(".stats {");
        out.println("display: grid;");
        out.println("grid-template-columns: repeat(3, 1fr);");
        out.println("gap: 25px;");
        out.println("margin-bottom: 40px;");
        out.println("}");

        out.println(".stat-card {");
        out.println("background: white;");
        out.println("padding: 30px;");
        out.println("border-radius: 20px;");
        out.println("text-align: center;");
        out.println("box-shadow: 0 10px 25px rgba(0,0,0,0.08);");
        out.println("}");

        out.println(".stat-number {");
        out.println("font-size: 42px;");
        out.println("font-weight: bold;");
        out.println("color: #667eea;");
        out.println("margin-bottom: 8px;");
        out.println("}");

        out.println(".stat-label {");
        out.println("font-size: 17px;");
        out.println("color: #666;");
        out.println("}");

        /* SECTIONS */

        out.println(".section {");
        out.println("background: white;");
        out.println("padding: 30px;");
        out.println("border-radius: 20px;");
        out.println("box-shadow: 0 10px 25px rgba(0,0,0,0.08);");
        out.println("margin-bottom: 30px;");
        out.println("}");

        out.println(".section h2 {");
        out.println("color: #667eea;");
        out.println("margin-bottom: 20px;");
        out.println("}");

        /* TABLE */

        out.println("table {");
        out.println("width: 100%;");
        out.println("border-collapse: collapse;");
        out.println("}");

        out.println("th {");
        out.println("background: #667eea;");
        out.println("color: white;");
        out.println("padding: 13px;");
        out.println("text-align: left;");
        out.println("}");

        out.println("td {");
        out.println("padding: 13px;");
        out.println("border-bottom: 1px solid #eee;");
        out.println("color: #555;");
        out.println("}");

        out.println("tr:hover {");
        out.println("background: #f8f9ff;");
        out.println("}");

        /* BADGE */

        out.println(".badge {");
        out.println("display: inline-block;");
        out.println("padding: 5px 12px;");
        out.println("border-radius: 15px;");
        out.println("background: #e8eaff;");
        out.println("color: #667eea;");
        out.println("font-weight: bold;");
        out.println("font-size: 13px;");
        out.println("}");

        /* RESPONSIVE */

        out.println("@media(max-width: 800px) {");

        out.println(".navbar {");
        out.println("padding: 20px;");
        out.println("}");

        out.println(".stats {");
        out.println("grid-template-columns: 1fr;");
        out.println("}");

        out.println(".section {");
        out.println("overflow-x: auto;");
        out.println("}");

        out.println(".management-buttons {");
        out.println("flex-direction: column;");
        out.println("align-items: center;");
        out.println("}");

        out.println(".management-button {");
        out.println("width: 90%;");
        out.println("text-align: center;");
        out.println("}");

        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        /* NAVBAR */

        out.println("<div class='navbar'>");

        out.println("<div class='logo'>");
        out.println("📚 StudySync Admin");
        out.println("</div>");

        out.println("<div class='nav-buttons'>");

        out.println("<a class='nav-button' href='logout'>");
        out.println("Logout");
        out.println("</a>");

        out.println("</div>");

        out.println("</div>");

        /* MAIN */

        out.println("<div class='container'>");

        out.println("<div class='heading'>");

        out.println("<h1>Admin Dashboard</h1>");

        out.println("<p>");
        out.println("Manage students, mentors, subjects and mentor assignments.");
        out.println("</p>");

        out.println("</div>");

        /* MANAGEMENT BUTTONS */

        out.println("<div class='management-buttons'>");

        out.println("<a class='management-button' " +
                "href='admin-students'>");

        out.println("👨‍🎓 Manage Students");

        out.println("</a>");

        out.println("<a class='management-button' " +
                "href='admin-mentors'>");

        out.println("👨‍🏫 Manage Mentors");

        out.println("</a>");

        out.println("<a class='management-button' " +
                "href='admin-subjects'>");

        out.println("📚 Manage Subjects");

        out.println("</a>");

        out.println("<a class='management-button' " +
                "href='mentor-student'>");

        out.println("🔗 Mentor–Student Management");

        out.println("</a>");

        out.println("</div>");

        /* STATISTICS */

        out.println("<div class='stats'>");

        out.println("<div class='stat-card'>");

        out.println("<div class='stat-number'>");
        out.println(students.size());
        out.println("</div>");

        out.println("<div class='stat-label'>");
        out.println("Students");
        out.println("</div>");

        out.println("</div>");

        out.println("<div class='stat-card'>");

        out.println("<div class='stat-number'>");
        out.println(mentors.size());
        out.println("</div>");

        out.println("<div class='stat-label'>");
        out.println("Mentors");
        out.println("</div>");

        out.println("</div>");

        out.println("<div class='stat-card'>");

        out.println("<div class='stat-number'>");
        out.println(subjects.size());
        out.println("</div>");

        out.println("<div class='stat-label'>");
        out.println("Subjects");
        out.println("</div>");

        out.println("</div>");

        out.println("</div>");

        /* STUDENTS */

        out.println("<div class='section'>");

        out.println("<h2>Students</h2>");

        out.println("<table>");

        out.println("<tr>");

        out.println("<th>ID</th>");
        out.println("<th>Name</th>");
        out.println("<th>Email</th>");
        out.println("<th>Role</th>");

        out.println("</tr>");

        for (User student : students) {

            out.println("<tr>");

            out.println("<td>");
            out.println(student.getId());
            out.println("</td>");

            out.println("<td>");
            out.println(student.getName());
            out.println("</td>");

            out.println("<td>");
            out.println(student.getEmail());
            out.println("</td>");

            out.println("<td>");

            out.println("<span class='badge'>");
            out.println(student.getRole());
            out.println("</span>");

            out.println("</td>");

            out.println("</tr>");
        }

        out.println("</table>");

        out.println("</div>");

        /* MENTORS */

        out.println("<div class='section'>");

        out.println("<h2>Mentors</h2>");

        out.println("<table>");

        out.println("<tr>");

        out.println("<th>ID</th>");
        out.println("<th>Mentor Name</th>");
        out.println("<th>Specialization</th>");
        out.println("<th>Experience</th>");

        out.println("</tr>");

        for (Mentor mentor : mentors) {

            out.println("<tr>");

            out.println("<td>");
            out.println(mentor.getId());
            out.println("</td>");

            out.println("<td>");
            out.println(mentor.getUserName());
            out.println("</td>");

            out.println("<td>");
            out.println(mentor.getSpecialization());
            out.println("</td>");

            out.println("<td>");
            out.println(mentor.getExperienceYears());
            out.println(" years");
            out.println("</td>");

            out.println("</tr>");
        }

        out.println("</table>");

        out.println("</div>");

        /* SUBJECTS */

        out.println("<div class='section'>");

        out.println("<h2>Subjects</h2>");

        out.println("<table>");

        out.println("<tr>");

        out.println("<th>ID</th>");
        out.println("<th>Name</th>");
        out.println("<th>Description</th>");

        out.println("</tr>");

        for (Subject subject : subjects) {

            out.println("<tr>");

            out.println("<td>");
            out.println(subject.getId());
            out.println("</td>");

            out.println("<td>");
            out.println(subject.getName());
            out.println("</td>");

            out.println("<td>");
            out.println(subject.getDescription());
            out.println("</td>");

            out.println("</tr>");
        }

        out.println("</table>");

        out.println("</div>");

        out.println("</div>");

        out.println("</body>");

        out.println("</html>");
    }
}