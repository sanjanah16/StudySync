
package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.studysync.DAO.UserDAO;
import com.studysync.DAOImpl.UserDAOImpl;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin-students")
public class AdminStudentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session =
                request.getSession(false);

        // Check admin login
        if (session == null ||
            session.getAttribute("user") == null) {

            response.sendRedirect("login.html");
            return;
        }

        User loggedInUser =
                (User) session.getAttribute("user");

        // Check admin role
        if (!"ADMIN".equalsIgnoreCase(loggedInUser.getRole())) {

            response.sendRedirect("dashboard");
            return;
        }

        UserDAO userDAO =
                new UserDAOImpl();

        // Get all users
        List<User> allUsers =
                userDAO.getAllUsers();

        // Keep only students
        List<User> students =
                new java.util.ArrayList<>();

        for (User user : allUsers) {

            if ("STUDENT".equalsIgnoreCase(user.getRole())) {

                students.add(user);
            }
        }

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out =
                response.getWriter();

        out.println("<!DOCTYPE html>");

        out.println("<html>");

        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println("<meta name='viewport' "
                + "content='width=device-width, initial-scale=1.0'>");

        out.println("<title>StudySync - Manage Students</title>");

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

        out.println("gap: 12px;");

        out.println("}");

        out.println(".nav-buttons a {");

        out.println("color: white;");

        out.println("text-decoration: none;");

        out.println("padding: 10px 18px;");

        out.println("border-radius: 20px;");

        out.println("font-weight: bold;");

        out.println("background: rgba(255,255,255,0.18);");

        out.println("}");

        out.println(".nav-buttons a:hover {");

        out.println("background: rgba(255,255,255,0.35);");

        out.println("}");

        out.println(".logout {");

        out.println("background: #ff416c !important;");

        out.println("}");

        /* CONTAINER */

        out.println(".container {");

        out.println("width: 90%;");

        out.println("max-width: 1200px;");

        out.println("margin: 40px auto;");

        out.println("}");

        out.println(".heading {");

        out.println("margin-bottom: 30px;");

        out.println("}");

        out.println(".heading h1 {");

        out.println("font-size: 32px;");

        out.println("margin-bottom: 8px;");

        out.println("color: #333;");

        out.println("}");

        out.println(".heading p {");

        out.println("color: #777;");

        out.println("font-size: 16px;");

        out.println("}");

        /* ADD STUDENT */

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

        out.println("color: #555;");

        out.println("}");

        out.println(".form-group input {");

        out.println("padding: 12px;");

        out.println("border: 1px solid #ddd;");

        out.println("border-radius: 10px;");

        out.println("font-size: 15px;");

        out.println("outline: none;");

        out.println("}");

        out.println(".form-group input:focus {");

        out.println("border-color: #667eea;");

        out.println("box-shadow: 0 0 5px rgba(102,126,234,0.2);");

        out.println("}");

        out.println(".add-button {");

        out.println("margin-top: 20px;");

        out.println("padding: 12px 25px;");

        out.println("border: none;");

        out.println("border-radius: 10px;");

        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");

        out.println("color: white;");

        out.println("font-size: 16px;");

        out.println("font-weight: bold;");

        out.println("cursor: pointer;");

        out.println("}");

        out.println(".add-button:hover {");

        out.println("transform: translateY(-2px);");

        out.println("}");

        /* STUDENT TABLE */

        out.println(".table-card {");

        out.println("background: white;");

        out.println("padding: 30px;");

        out.println("border-radius: 18px;");

        out.println("box-shadow: 0 8px 25px rgba(0,0,0,0.08);");

        out.println("overflow-x: auto;");

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

        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");

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

        /* ROLE */

        out.println(".role {");

        out.println("display: inline-block;");

        out.println("background: #e8eaff;");

        out.println("color: #667eea;");

        out.println("padding: 5px 12px;");

        out.println("border-radius: 15px;");

        out.println("font-size: 13px;");

        out.println("font-weight: bold;");

        out.println("}");

        /* ACTION BUTTONS */

        out.println(".action-buttons {");

        out.println("display: flex;");

        out.println("gap: 8px;");

        out.println("flex-wrap: wrap;");

        out.println("}");

        /* EDIT */

        out.println(".edit-button {");

        out.println("display: inline-block;");

        out.println("background: #667eea;");

        out.println("color: white;");

        out.println("text-decoration: none;");

        out.println("padding: 8px 14px;");

        out.println("border-radius: 8px;");

        out.println("font-weight: bold;");

        out.println("}");

        out.println(".edit-button:hover {");

        out.println("background: #5568d9;");

        out.println("}");

        /* DELETE */

        out.println(".delete-button {");

        out.println("display: inline-block;");

        out.println("background: linear-gradient(135deg, #ff416c, #ff4b2b);");

        out.println("color: white;");

        out.println("text-decoration: none;");

        out.println("padding: 8px 14px;");

        out.println("border-radius: 8px;");

        out.println("font-weight: bold;");

        out.println("}");

        out.println(".delete-button:hover {");

        out.println("transform: translateY(-2px);");

        out.println("}");

        /* EMPTY */

        out.println(".empty {");

        out.println("text-align: center;");

        out.println("padding: 30px;");

        out.println("color: #777;");

        out.println("}");

        /* RESPONSIVE */

        out.println("@media(max-width: 700px) {");

        out.println(".navbar {");

        out.println("padding: 20px;");

        out.println("flex-direction: column;");

        out.println("gap: 15px;");

        out.println("}");

        out.println(".nav-buttons {");

        out.println("flex-wrap: wrap;");

        out.println("justify-content: center;");

        out.println("}");

        out.println(".form-grid {");

        out.println("grid-template-columns: 1fr;");

        out.println("}");

        out.println(".container {");

        out.println("width: 95%;");

        out.println("}");

        out.println(".action-buttons {");

        out.println("flex-direction: column;");

        out.println("}");

        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        /* NAVBAR */

        out.println("<div class='navbar'>");

        out.println("<div class='logo'>StudySync Admin</div>");

        out.println("<div class='nav-buttons'>");

        out.println("<a href='admin-dashboard'>");

        out.println("Admin Dashboard");

        out.println("</a>");

        out.println("<a href='admin-mentors'>");

        out.println("Manage Mentors");

        out.println("</a>");

        out.println("<a href='admin-subjects'>");

        out.println("Manage Subjects");

        out.println("</a>");

        out.println("<a class='logout' href='logout'>");

        out.println("Logout");

        out.println("</a>");

        out.println("</div>");

        out.println("</div>");

        /* MAIN */

        out.println("<div class='container'>");

        out.println("<div class='heading'>");

        out.println("<h1>Manage Students</h1>");

        out.println("<p>");

        out.println("Add, view and manage StudySync students.");

        out.println("</p>");

        out.println("</div>");

        /* ADD STUDENT FORM */

        out.println("<div class='form-card'>");

        out.println("<h2>Add New Student</h2>");

        out.println("<form method='post' action='admin-students'>");

        out.println("<div class='form-grid'>");

        out.println("<div class='form-group'>");

        out.println("<label>Student Name</label>");

        out.println("<input type='text' name='name' "
                + "placeholder='Enter student name' required>");

        out.println("</div>");

        out.println("<div class='form-group'>");

        out.println("<label>Email</label>");

        out.println("<input type='email' name='email' "
                + "placeholder='Enter email' required>");

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

        out.println("<button class='add-button' type='submit'>");

        out.println("+ Add Student");

        out.println("</button>");

        out.println("</form>");

        out.println("</div>");

        /* STUDENT TABLE */

        out.println("<div class='table-card'>");

        out.println("<h2>Registered Students</h2>");

        if (students.isEmpty()) {

            out.println("<div class='empty'>");

            out.println("<h3>No students found</h3>");

            out.println("<p>Add a student using the form above.</p>");

            out.println("</div>");

        } else {

            out.println("<table>");

            out.println("<tr>");

            out.println("<th>ID</th>");

            out.println("<th>Name</th>");

            out.println("<th>Email</th>");

            out.println("<th>Phone</th>");

            out.println("<th>Role</th>");

            out.println("<th>Action</th>");

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

                out.println(student.getPhone());

                out.println("</td>");

                out.println("<td>");

                out.println("<span class='role'>");

                out.println(student.getRole());

                out.println("</span>");

                out.println("</td>");

                out.println("<td>");

                out.println("<div class='action-buttons'>");

                // EDIT BUTTON

                out.println("<a class='edit-button' "
                        + "href='edit-student?id="
                        + student.getId()
                        + "'>");

                out.println("Edit");

                out.println("</a>");

                // DELETE BUTTON

                out.println("<a class='delete-button' "
                        + "href='admin-delete-student?id="
                        + student.getId()
                        + "' "
                        + "onclick=\"return confirm('Are you sure you want to delete this student?');\">");

                out.println("Delete");

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

        HttpSession session =
                request.getSession(false);

        // Check admin login

        if (session == null ||
            session.getAttribute("user") == null) {

            response.sendRedirect("login.html");
            return;
        }

        User loggedInUser =
                (User) session.getAttribute("user");

        // Check admin role

        if (!"ADMIN".equalsIgnoreCase(loggedInUser.getRole())) {

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

        User student =
                new User(
                        name,
                        email,
                        password,
                        phone,
                        "STUDENT"
                );

        UserDAO userDAO =
                new UserDAOImpl();

        userDAO.addUser(student);

        response.sendRedirect("admin-students");
    }
}

