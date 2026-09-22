
package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.studysync.DAO.UserDAO;
import com.studysync.DAOImpl.UserDAOImpl;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/edit-student")
public class EditStudentServlet extends HttpServlet {

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

        String idString =
                request.getParameter("id");

        if (idString == null || idString.isEmpty()) {

            response.sendRedirect("admin-students");
            return;
        }

        int studentId =
                Integer.parseInt(idString);

        UserDAO userDAO =
                new UserDAOImpl();

        User student =
                userDAO.getUser(studentId);

        if (student == null ||
            !"STUDENT".equalsIgnoreCase(student.getRole())) {

            response.sendRedirect("admin-students");
            return;
        }

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

        out.println("<title>StudySync - Edit Student</title>");

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

        out.println(".nav-button {");
        out.println("color: white;");
        out.println("text-decoration: none;");
        out.println("padding: 10px 18px;");
        out.println("border-radius: 20px;");
        out.println("background: rgba(255,255,255,0.2);");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".container {");
        out.println("width: 90%;");
        out.println("max-width: 600px;");
        out.println("margin: 50px auto;");
        out.println("}");

        out.println(".card {");
        out.println("background: white;");
        out.println("padding: 35px;");
        out.println("border-radius: 20px;");
        out.println("box-shadow: 0 10px 30px rgba(0,0,0,0.10);");
        out.println("}");

        out.println("h1 {");
        out.println("text-align: center;");
        out.println("color: #333;");
        out.println("margin-bottom: 10px;");
        out.println("}");

        out.println(".subtitle {");
        out.println("text-align: center;");
        out.println("color: #777;");
        out.println("margin-bottom: 30px;");
        out.println("}");

        out.println(".form-group {");
        out.println("margin-bottom: 20px;");
        out.println("}");

        out.println("label {");
        out.println("display: block;");
        out.println("font-weight: bold;");
        out.println("color: #555;");
        out.println("margin-bottom: 8px;");
        out.println("}");

        out.println("input {");
        out.println("width: 100%;");
        out.println("padding: 13px;");
        out.println("border: 1px solid #ddd;");
        out.println("border-radius: 10px;");
        out.println("font-size: 15px;");
        out.println("outline: none;");
        out.println("}");

        out.println("input:focus {");
        out.println("border-color: #667eea;");
        out.println("box-shadow: 0 0 0 3px rgba(102,126,234,0.12);");
        out.println("}");

        out.println(".button-group {");
        out.println("display: flex;");
        out.println("gap: 12px;");
        out.println("margin-top: 25px;");
        out.println("}");

        out.println(".update-button {");
        out.println("flex: 1;");
        out.println("border: none;");
        out.println("padding: 13px;");
        out.println("border-radius: 10px;");
        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("color: white;");
        out.println("font-size: 15px;");
        out.println("font-weight: bold;");
        out.println("cursor: pointer;");
        out.println("}");

        out.println(".cancel-button {");
        out.println("flex: 1;");
        out.println("padding: 13px;");
        out.println("border-radius: 10px;");
        out.println("background: #eee;");
        out.println("color: #555;");
        out.println("text-decoration: none;");
        out.println("text-align: center;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println("@media(max-width: 600px) {");

        out.println(".navbar {");
        out.println("padding: 20px;");
        out.println("}");

        out.println(".card {");
        out.println("padding: 25px;");
        out.println("}");

        out.println(".button-group {");
        out.println("flex-direction: column;");
        out.println("}");

        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        out.println("<div class='navbar'>");

        out.println("<div class='logo'>");
        out.println("📚 StudySync Admin");
        out.println("</div>");

        out.println("<a class='nav-button' href='admin-students'>");
        out.println("← Manage Students");
        out.println("</a>");

        out.println("</div>");

        out.println("<div class='container'>");

        out.println("<div class='card'>");

        out.println("<h1>✏ Edit Student</h1>");

        out.println("<p class='subtitle'>");
        out.println("Update the student's information.");
        out.println("</p>");

        out.println("<form method='post' action='edit-student'>");

        out.println("<input type='hidden' "
                + "name='id' "
                + "value='" + student.getId() + "'>");

        out.println("<div class='form-group'>");

        out.println("<label>Student Name</label>");

        out.println("<input type='text' "
                + "name='name' "
                + "value='" + student.getName() + "' "
                + "required>");

        out.println("</div>");

        out.println("<div class='form-group'>");

        out.println("<label>Email</label>");

        out.println("<input type='email' "
                + "name='email' "
                + "value='" + student.getEmail() + "' "
                + "required>");

        out.println("</div>");

        out.println("<div class='form-group'>");

        out.println("<label>Password</label>");

        out.println("<input type='password' "
                + "name='password' "
                + "value='" + student.getPassword() + "' "
                + "required>");

        out.println("</div>");

        out.println("<div class='form-group'>");

        out.println("<label>Phone</label>");

        out.println("<input type='text' "
                + "name='phone' "
                + "value='" + student.getPhone() + "'>");

        out.println("</div>");

        out.println("<div class='button-group'>");

        out.println("<button class='update-button' "
                + "type='submit'>");

        out.println("Update Student");

        out.println("</button>");

        out.println("<a class='cancel-button' "
                + "href='admin-students'>");

        out.println("Cancel");

        out.println("</a>");

        out.println("</div>");

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

        int id =
                Integer.parseInt(
                        request.getParameter("id"));

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

        User student =
                userDAO.getUser(id);

        if (student == null ||
            !"STUDENT".equalsIgnoreCase(student.getRole())) {

            response.sendRedirect("admin-students");
            return;
        }

        student.setName(name);
        student.setEmail(email);
        student.setPassword(password);
        student.setPhone(phone);

        userDAO.updateUser(student);

        response.sendRedirect("admin-students");
    }
}

