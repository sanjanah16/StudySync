
package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.studysync.DAO.SubjectDAO;
import com.studysync.DAOImpl.SubjectDAOImpl;
import com.studysync.model.Subject;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/edit-subject")
public class EditSubjectServlet extends HttpServlet {

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

        if (idString == null ||
            idString.isEmpty()) {

            response.sendRedirect("admin-subjects");
            return;
        }

        int subjectId;

        try {

            subjectId = Integer.parseInt(idString);

        } catch (NumberFormatException e) {

            response.sendRedirect("admin-subjects");
            return;
        }

        SubjectDAO subjectDAO =
                new SubjectDAOImpl();

        Subject subject =
                subjectDAO.getSubject(subjectId);

        if (subject == null) {

            response.sendRedirect("admin-subjects");
            return;
        }

        response.setContentType(
                "text/html;charset=UTF-8");

        PrintWriter out =
                response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println("<meta name='viewport' "
                + "content='width=device-width, initial-scale=1.0'>");

        out.println("<title>StudySync - Edit Subject</title>");

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
        out.println("background: rgba(255,255,255,0.30);");
        out.println("}");

        out.println(".logout {");
        out.println("background: #ff416c !important;");
        out.println("}");

        /* CONTAINER */

        out.println(".container {");
        out.println("width: 90%;");
        out.println("max-width: 650px;");
        out.println("margin: 60px auto;");
        out.println("}");

        /* FORM CARD */

        out.println(".form-card {");
        out.println("background: white;");
        out.println("padding: 40px;");
        out.println("border-radius: 22px;");
        out.println("box-shadow: 0 12px 35px rgba(0,0,0,0.10);");
        out.println("}");

        /* HEADING */

        out.println(".heading {");
        out.println("text-align: center;");
        out.println("margin-bottom: 30px;");
        out.println("}");

        out.println(".heading h1 {");
        out.println("color: #667eea;");
        out.println("margin-bottom: 10px;");
        out.println("}");

        out.println(".heading p {");
        out.println("color: #777;");
        out.println("}");

        /* FORM */

        out.println(".form-group {");
        out.println("margin-bottom: 22px;");
        out.println("}");

        out.println(".form-group label {");
        out.println("display: block;");
        out.println("margin-bottom: 8px;");
        out.println("font-weight: bold;");
        out.println("color: #444;");
        out.println("}");

        out.println(".form-group input,");
        out.println(".form-group textarea {");
        out.println("width: 100%;");
        out.println("padding: 13px 15px;");
        out.println("border: 1px solid #ddd;");
        out.println("border-radius: 10px;");
        out.println("font-size: 15px;");
        out.println("outline: none;");
        out.println("}");

        out.println(".form-group input:focus,");
        out.println(".form-group textarea:focus {");
        out.println("border-color: #667eea;");
        out.println("box-shadow: 0 0 0 3px rgba(102,126,234,0.12);");
        out.println("}");

        out.println(".form-group textarea {");
        out.println("min-height: 120px;");
        out.println("resize: vertical;");
        out.println("}");

        /* BUTTON */

        out.println(".submit-button {");
        out.println("width: 100%;");
        out.println("border: none;");
        out.println("padding: 14px;");
        out.println("border-radius: 12px;");
        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("color: white;");
        out.println("font-size: 16px;");
        out.println("font-weight: bold;");
        out.println("cursor: pointer;");
        out.println("transition: 0.3s;");
        out.println("}");

        out.println(".submit-button:hover {");
        out.println("transform: translateY(-2px);");
        out.println("box-shadow: 0 8px 18px rgba(102,126,234,0.30);");
        out.println("}");

        /* BACK */

        out.println(".back-button {");
        out.println("display: block;");
        out.println("text-align: center;");
        out.println("margin-top: 18px;");
        out.println("color: #667eea;");
        out.println("text-decoration: none;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        /* NAVBAR */

        out.println("<div class='navbar'>");

        out.println("<div class='logo'>");
        out.println("StudySync Admin");
        out.println("</div>");

        out.println("<div class='nav-buttons'>");

        out.println("<a href='admin-dashboard'>");
        out.println("Admin Dashboard");
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

        out.println("<div class='form-card'>");

        out.println("<div class='heading'>");

        out.println("<h1>");
        out.println("Edit Subject");
        out.println("</h1>");

        out.println("<p>");
        out.println("Update the subject information below.");
        out.println("</p>");

        out.println("</div>");

        /* FORM */

        out.println("<form action='edit-subject' method='post'>");

        out.println("<input type='hidden' "
                + "name='id' "
                + "value='" + subject.getId() + "'>");

        out.println("<div class='form-group'>");

        out.println("<label for='name'>");
        out.println("Subject Name");
        out.println("</label>");

        out.println("<input "
                + "type='text' "
                + "id='name' "
                + "name='name' "
                + "value='" + subject.getName() + "' "
                + "required>");

        out.println("</div>");

        out.println("<div class='form-group'>");

        out.println("<label for='description'>");
        out.println("Description");
        out.println("</label>");

        out.println("<textarea "
                + "id='description' "
                + "name='description' "
                + "required>");

        out.println(subject.getDescription());

        out.println("</textarea>");

        out.println("</div>");

        out.println("<button "
                + "type='submit' "
                + "class='submit-button'>");

        out.println("Update Subject");

        out.println("</button>");

        out.println("</form>");

        out.println("<a href='admin-subjects' "
                + "class='back-button'>");

        out.println("← Back to Subjects");

        out.println("</a>");

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

        String name =
                request.getParameter("name");

        String description =
                request.getParameter("description");

        if (idString == null ||
            idString.isEmpty() ||
            name == null ||
            name.trim().isEmpty()) {

            response.sendRedirect("admin-subjects");
            return;
        }

        try {

            int id =
                    Integer.parseInt(idString);

            Subject subject =
                    new Subject(
                            id,
                            name,
                            description
                    );

            SubjectDAO subjectDAO =
                    new SubjectDAOImpl();

            subjectDAO.updateSubject(subject);

        } catch (NumberFormatException e) {

            e.printStackTrace();
        }

        response.sendRedirect("admin-subjects");
    }
}
