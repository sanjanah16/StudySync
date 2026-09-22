
package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.studysync.DAO.MentorDAO;
import com.studysync.DAO.UserDAO;
import com.studysync.DAOImpl.MentorDAOImpl;
import com.studysync.DAOImpl.UserDAOImpl;
import com.studysync.model.Mentor;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin-mentors")
public class AdminMentorServlet extends HttpServlet {

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

        User admin = (User) session.getAttribute("user");

        if (!"ADMIN".equalsIgnoreCase(admin.getRole())) {

            response.sendRedirect("dashboard");
            return;
        }

        MentorDAO mentorDAO = new MentorDAOImpl();
        UserDAO userDAO = new UserDAOImpl();

        List<Mentor> mentors =
                mentorDAO.getAllMentors();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println("<meta name='viewport' "
                + "content='width=device-width, initial-scale=1.0'>");

        out.println("<title>StudySync - Manage Mentors</title>");

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
        out.println("}");

        out.println(".logo {");
        out.println("font-size: 27px;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".nav-buttons {");
        out.println("display: flex;");
        out.println("gap: 10px;");
        out.println("flex-wrap: wrap;");
        out.println("}");

        out.println(".nav-button {");
        out.println("color: white;");
        out.println("text-decoration: none;");
        out.println("padding: 10px 18px;");
        out.println("border-radius: 20px;");
        out.println("font-weight: bold;");
        out.println("background: rgba(255,255,255,0.2);");
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

        /* FORM */

        out.println(".form-card {");
        out.println("background: white;");
        out.println("padding: 30px;");
        out.println("border-radius: 18px;");
        out.println("box-shadow: 0 8px 25px rgba(0,0,0,0.08);");
        out.println("margin-bottom: 35px;");
        out.println("}");

        out.println(".form-card h2 {");
        out.println("color: #667eea;");
        out.println("margin-bottom: 20px;");
        out.println("}");

        out.println(".form-grid {");
        out.println("display: grid;");
        out.println("grid-template-columns: repeat(2, 1fr);");
        out.println("gap: 18px;");
        out.println("}");

        out.println(".form-group {");
        out.println("display: flex;");
        out.println("flex-direction: column;");
        out.println("}");

        out.println(".form-group label {");
        out.println("font-weight: bold;");
        out.println("margin-bottom: 7px;");
        out.println("color: #444;");
        out.println("}");

        out.println(".form-group input {");
        out.println("padding: 12px;");
        out.println("border: 1px solid #ddd;");
        out.println("border-radius: 10px;");
        out.println("font-size: 15px;");
        out.println("}");

        out.println(".submit-button {");
        out.println("margin-top: 20px;");
        out.println("padding: 12px 25px;");
        out.println("border: none;");
        out.println("border-radius: 10px;");
        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("color: white;");
        out.println("font-weight: bold;");
        out.println("font-size: 15px;");
        out.println("cursor: pointer;");
        out.println("}");

        /* TABLE */

        out.println(".table-card {");
        out.println("background: white;");
        out.println("padding: 30px;");
        out.println("border-radius: 18px;");
        out.println("box-shadow: 0 8px 25px rgba(0,0,0,0.08);");
        out.println("}");

        out.println(".table-card h2 {");
        out.println("color: #667eea;");
        out.println("margin-bottom: 20px;");
        out.println("}");

        out.println("table {");
        out.println("width: 100%;");
        out.println("border-collapse: collapse;");
        out.println("}");

        out.println("th {");
        out.println("background: #667eea;");
        out.println("color: white;");
        out.println("padding: 14px;");
        out.println("text-align: left;");
        out.println("}");

        out.println("td {");
        out.println("padding: 14px;");
        out.println("border-bottom: 1px solid #eee;");
        out.println("}");

        out.println("tr:hover {");
        out.println("background: #f8f8ff;");
        out.println("}");

        /* ACTION BUTTONS */

        out.println(".action-buttons {");
        out.println("display: flex;");
        out.println("gap: 8px;");
        out.println("flex-wrap: wrap;");
        out.println("}");

        out.println(".edit-button {");
        out.println("background: #667eea;");
        out.println("color: white;");
        out.println("padding: 8px 14px;");
        out.println("border-radius: 8px;");
        out.println("text-decoration: none;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".delete-button {");
        out.println("background: #ff416c;");
        out.println("color: white;");
        out.println("padding: 8px 14px;");
        out.println("border-radius: 8px;");
        out.println("text-decoration: none;");
        out.println("font-weight: bold;");
        out.println("}");

        /* RESPONSIVE */

        out.println("@media(max-width: 800px) {");

        out.println(".navbar {");
        out.println("padding: 20px;");
        out.println("flex-direction: column;");
        out.println("gap: 15px;");
        out.println("}");

        out.println(".form-grid {");
        out.println("grid-template-columns: 1fr;");
        out.println("}");

        out.println("table {");
        out.println("display: block;");
        out.println("overflow-x: auto;");
        out.println("}");

        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        /* NAVBAR */

        out.println("<div class='navbar'>");

        out.println("<div class='logo'>📚 StudySync Admin</div>");

        out.println("<div class='nav-buttons'>");

        out.println("<a class='nav-button' href='admin-dashboard'>");
        out.println("Admin Dashboard");
        out.println("</a>");

        out.println("<a class='nav-button' href='admin-students'>");
        out.println("Manage Students");
        out.println("</a>");

        out.println("<a class='nav-button' href='admin-subjects'>");
        out.println("Manage Subjects");
        out.println("</a>");

        out.println("<a class='nav-button logout' href='logout'>");
        out.println("Logout");
        out.println("</a>");

        out.println("</div>");

        out.println("</div>");

        /* MAIN */

        out.println("<div class='container'>");

        out.println("<div class='heading'>");

        out.println("<h1>👨‍🏫 Manage Mentors</h1>");

        out.println("<p>");
        out.println("Add, edit and manage StudySync mentors.");
        out.println("</p>");

        out.println("</div>");

        /* ADD MENTOR */

        out.println("<div class='form-card'>");

        out.println("<h2>➕ Add New Mentor</h2>");

        out.println("<form method='post' action='admin-mentors'>");

        out.println("<div class='form-grid'>");

        out.println("<div class='form-group'>");

        out.println("<label>Name</label>");

        out.println("<input type='text' name='name' "
                + "placeholder='Enter mentor name' required>");

        out.println("</div>");

        out.println("<div class='form-group'>");

        out.println("<label>Email</label>");

        out.println("<input type='email' name='email' "
                + "placeholder='Enter mentor email' required>");

        out.println("</div>");

        out.println("<div class='form-group'>");

        out.println("<label>Password</label>");

        out.println("<input type='password' name='password' "
                + "placeholder='Enter password' required>");

        out.println("</div>");

        out.println("<div class='form-group'>");

        out.println("<label>Phone</label>");

        out.println("<input type='text' name='phone' "
                + "placeholder='Enter phone number'>");

        out.println("</div>");

        out.println("</div>");

        out.println("<button class='submit-button' type='submit'>");
        out.println("Add Mentor");
        out.println("</button>");

        out.println("</form>");

        out.println("</div>");

        /* MENTOR TABLE */

        out.println("<div class='table-card'>");

        out.println("<h2>📋 Registered Mentors</h2>");

        if (mentors.isEmpty()) {

            out.println("<p>No mentors registered yet.</p>");

        } else {

            out.println("<table>");

            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Email</th>");
            out.println("<th>Phone</th>");
            out.println("<th>Specialization</th>");
            out.println("<th>Experience</th>");
            out.println("<th>Actions</th>");
            out.println("</tr>");

            for (Mentor mentor : mentors) {

                User mentorUser =
                        userDAO.getUser(mentor.getUserId());

                out.println("<tr>");

                out.println("<td>"
                        + mentor.getId()
                        + "</td>");

                if (mentorUser != null) {

                    out.println("<td>"
                            + mentorUser.getName()
                            + "</td>");

                    out.println("<td>"
                            + mentorUser.getEmail()
                            + "</td>");

                    out.println("<td>"
                            + mentorUser.getPhone()
                            + "</td>");

                } else {

                    out.println("<td>Unknown</td>");
                    out.println("<td>Unknown</td>");
                    out.println("<td>Unknown</td>");
                }

                out.println("<td>"
                        + mentor.getSpecialization()
                        + "</td>");

                out.println("<td>"
                        + mentor.getExperienceYears()
                        + " years</td>");

                out.println("<td>");

                out.println("<div class='action-buttons'>");

                /* EDIT */

                out.println("<a class='edit-button' "
                        + "href='edit-mentor?id="
                        + mentor.getId()
                        + "'>");

                out.println("✏ Edit");

                out.println("</a>");

                /* DELETE */

                out.println("<a class='delete-button' "
                        + "href='admin-delete-mentor?id="
                        + mentor.getId()
                        + "' "
                        + "onclick=\"return confirm('Are you sure you want to delete this mentor?');\">");

                out.println("🗑 Delete");

                out.println("</a>");

                out.println("</div>");

                out.println("</td>");

                out.println("</tr>");
            }

            out.println("</table>");
        }

        out.println("</div>");

        out.println("</div>");

        out.println("</body>");

        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null ||
            session.getAttribute("user") == null) {

            response.sendRedirect("login.html");
            return;
        }

        User admin = (User) session.getAttribute("user");

        if (!"ADMIN".equalsIgnoreCase(admin.getRole())) {

            response.sendRedirect("dashboard");
            return;
        }

        String name =
                request.getParameter("name");

        String email =
                request.getParameter("email");

        String password =
                request.getParameter("password");

        String phone =
                request.getParameter("phone");

        UserDAO userDAO =
                new UserDAOImpl();

        User mentorUser =
                new User(
                        name,
                        email,
                        password,
                        phone,
                        "MENTOR"
                );

        userDAO.addUser(mentorUser);

        response.sendRedirect("admin-mentors");
    }
}

