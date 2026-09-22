
package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

@WebServlet("/admin-subjects")
public class AdminSubjectServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
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

        SubjectDAO subjectDAO =
                new SubjectDAOImpl();

        List<Subject> subjects =
                subjectDAO.getAllSubjects();

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

        out.println("<title>StudySync - Manage Subjects</title>");

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
        out.println("flex-wrap: wrap;");
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
        out.println("max-width: 1100px;");
        out.println("margin: 40px auto;");
        out.println("}");

        /* HEADING */

        out.println(".heading {");
        out.println("display: flex;");
        out.println("justify-content: space-between;");
        out.println("align-items: center;");
        out.println("gap: 20px;");
        out.println("margin-bottom: 30px;");
        out.println("}");

        out.println(".heading-text h1 {");
        out.println("font-size: 32px;");
        out.println("margin-bottom: 8px;");
        out.println("}");

        out.println(".heading-text p {");
        out.println("color: #777;");
        out.println("font-size: 16px;");
        out.println("}");

        /* ADD BUTTON */

        out.println(".add-button {");
        out.println("display: inline-block;");
        out.println("background: linear-gradient(135deg, #00b894, #00cec9);");
        out.println("color: white;");
        out.println("text-decoration: none;");
        out.println("padding: 13px 22px;");
        out.println("border-radius: 12px;");
        out.println("font-weight: bold;");
        out.println("box-shadow: 0 7px 18px rgba(0,184,148,0.25);");
        out.println("transition: 0.3s;");
        out.println("white-space: nowrap;");
        out.println("}");

        out.println(".add-button:hover {");
        out.println("transform: translateY(-3px);");
        out.println("box-shadow: 0 10px 22px rgba(0,184,148,0.35);");
        out.println("}");

        /* SUBJECT GRID */

        out.println(".subject-grid {");
        out.println("display: grid;");
        out.println("grid-template-columns: repeat(3, 1fr);");
        out.println("gap: 25px;");
        out.println("}");

        /* SUBJECT CARD */

        out.println(".subject-card {");
        out.println("background: white;");
        out.println("padding: 25px;");
        out.println("border-radius: 18px;");
        out.println("box-shadow: 0 8px 25px rgba(0,0,0,0.08);");
        out.println("transition: 0.3s;");
        out.println("border-top: 5px solid #667eea;");
        out.println("}");

        out.println(".subject-card:hover {");
        out.println("transform: translateY(-6px);");
        out.println("box-shadow: 0 15px 30px rgba(0,0,0,0.12);");
        out.println("}");

        out.println(".subject-icon {");
        out.println("font-size: 38px;");
        out.println("margin-bottom: 15px;");
        out.println("}");

        out.println(".subject-card h2 {");
        out.println("color: #667eea;");
        out.println("margin-bottom: 10px;");
        out.println("}");

        out.println(".subject-card p {");
        out.println("color: #666;");
        out.println("line-height: 1.6;");
        out.println("}");

        out.println(".subject-id {");
        out.println("display: inline-block;");
        out.println("margin-top: 15px;");
        out.println("padding: 5px 12px;");
        out.println("border-radius: 15px;");
        out.println("background: #eeeaff;");
        out.println("color: #667eea;");
        out.println("font-size: 13px;");
        out.println("font-weight: bold;");
        out.println("}");

        /* ACTION BUTTONS */

        out.println(".action-buttons {");
        out.println("display: flex;");
        out.println("gap: 10px;");
        out.println("margin-top: 20px;");
        out.println("}");

        /* EDIT BUTTON */

        out.println(".edit-button {");
        out.println("flex: 1;");
        out.println("text-align: center;");
        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("color: white;");
        out.println("text-decoration: none;");
        out.println("padding: 11px;");
        out.println("border-radius: 10px;");
        out.println("font-weight: bold;");
        out.println("transition: 0.3s;");
        out.println("}");

        out.println(".edit-button:hover {");
        out.println("transform: translateY(-2px);");
        out.println("box-shadow: 0 7px 15px rgba(102,126,234,0.30);");
        out.println("}");

        /* DELETE BUTTON */

        out.println(".delete-button {");
        out.println("flex: 1;");
        out.println("text-align: center;");
        out.println("background: linear-gradient(135deg, #ff416c, #ff4b2b);");
        out.println("color: white;");
        out.println("text-decoration: none;");
        out.println("padding: 11px;");
        out.println("border-radius: 10px;");
        out.println("font-weight: bold;");
        out.println("transition: 0.3s;");
        out.println("}");

        out.println(".delete-button:hover {");
        out.println("transform: translateY(-2px);");
        out.println("box-shadow: 0 7px 15px rgba(255,65,108,0.30);");
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

        out.println("@media(max-width: 800px) {");

        out.println(".subject-grid {");
        out.println("grid-template-columns: 1fr;");
        out.println("}");

        out.println(".navbar {");
        out.println("padding: 20px;");
        out.println("flex-direction: column;");
        out.println("gap: 15px;");
        out.println("}");

        out.println(".nav-buttons {");
        out.println("justify-content: center;");
        out.println("}");

        out.println(".heading {");
        out.println("flex-direction: column;");
        out.println("align-items: flex-start;");
        out.println("}");

        out.println(".add-button {");
        out.println("width: 100%;");
        out.println("text-align: center;");
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

        out.println("<div class='logo'>");
        out.println("StudySync Admin");
        out.println("</div>");

        out.println("<div class='nav-buttons'>");

        out.println("<a href='admin-dashboard'>");
        out.println("Admin Dashboard");
        out.println("</a>");

        out.println("<a href='admin-students'>");
        out.println("Manage Students");
        out.println("</a>");

        out.println("<a href='admin-mentors'>");
        out.println("Manage Mentors");
        out.println("</a>");

        out.println("<a class='logout' href='logout'>");
        out.println("Logout");
        out.println("</a>");

        out.println("</div>");

        out.println("</div>");

        /* MAIN */

        out.println("<div class='container'>");

        /* HEADING */

        out.println("<div class='heading'>");

        out.println("<div class='heading-text'>");

        out.println("<h1>");
        out.println("Manage Subjects");
        out.println("</h1>");

        out.println("<p>");
        out.println("View and manage subjects available in StudySync.");
        out.println("</p>");

        out.println("</div>");

        out.println("<a class='add-button' "
                + "href='admin-add-subject.html'>");

        out.println("+ Add Subject");

        out.println("</a>");

        out.println("</div>");

        /* SUBJECT LIST */

        if (subjects.isEmpty()) {

            out.println("<div class='empty'>");

            out.println("<h2>");
            out.println("No Subjects Found");
            out.println("</h2>");

            out.println("<p>");
            out.println("Click '+ Add Subject' to create your first subject.");
            out.println("</p>");

            out.println("</div>");

        } else {

            out.println("<div class='subject-grid'>");

            for (Subject subject : subjects) {

                out.println("<div class='subject-card'>");

                out.println("<div class='subject-icon'>");
                out.println("📚");
                out.println("</div>");

                out.println("<h2>");
                out.println(subject.getName());
                out.println("</h2>");

                out.println("<p>");
                out.println(subject.getDescription());
                out.println("</p>");

                out.println("<span class='subject-id'>");

                out.println("Subject ID: ");

                out.println(subject.getId());

                out.println("</span>");

                /* ACTION BUTTONS */

                out.println("<div class='action-buttons'>");

                /* EDIT */

                out.println("<a class='edit-button' "
                        + "href='edit-subject?id="
                        + subject.getId()
                        + "'>");

                out.println("✏ Edit Subject");

                out.println("</a>");

                /* DELETE */

                out.println("<a class='delete-button' "
                        + "href='delete-subject?id="
                        + subject.getId()
                        + "' "
                        + "onclick=\"return confirm('Are you sure you want to delete this subject?');\">");

                out.println("🗑 Delete Subject");

                out.println("</a>");

                out.println("</div>");

                out.println("</div>");
            }

            out.println("</div>");
        }

        out.println("</div>");

        out.println("</body>");

        out.println("</html>");
    }
}

