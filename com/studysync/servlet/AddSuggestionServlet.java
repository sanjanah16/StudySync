
package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import com.studysync.DAO.MentorDAO;
import com.studysync.DAO.SuggestionDAO;
import com.studysync.DAOImpl.MentorDAOImpl;
import com.studysync.DAOImpl.SuggestionDAOImpl;
import com.studysync.model.Mentor;
import com.studysync.model.Suggestion;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/add-suggestion")
public class AddSuggestionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // =========================
        // CHECK LOGIN
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

        User user =
                (User) session.getAttribute("user");

        // Only mentor can give suggestions
        if (!"MENTOR".equalsIgnoreCase(
                user.getRole())) {

            response.sendRedirect("dashboard");
            return;
        }

        // =========================
        // GET STUDENT ID
        // =========================

        String studentIdString =
                request.getParameter("studentId");

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
        // GET MENTOR
        // =========================

        MentorDAO mentorDAO =
                new MentorDAOImpl();

        Mentor mentor =
                mentorDAO.getMentorByUserId(
                        user.getId()
                );

        if (mentor == null) {

            response.sendRedirect("mentor");
            return;
        }

        // =========================
        // HTML RESPONSE
        // =========================

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out =
                response.getWriter();

        // =========================
        // HTML
        // =========================

        out.println("<!DOCTYPE html>");

        out.println("<html>");

        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println("<meta name='viewport' "
                + "content='width=device-width, initial-scale=1.0'>");

        out.println("<title>StudySync - Give Suggestion</title>");

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
        out.println("}");

        // =========================
        // CONTAINER
        // =========================

        out.println(".container {");
        out.println("width: 90%;");
        out.println("max-width: 750px;");
        out.println("margin: 50px auto;");
        out.println("}");

        // =========================
        // HEADING
        // =========================

        out.println(".heading {");
        out.println("text-align: center;");
        out.println("margin-bottom: 30px;");
        out.println("}");

        out.println(".heading h1 {");
        out.println("font-size: 34px;");
        out.println("color: #333;");
        out.println("margin-bottom: 10px;");
        out.println("}");

        out.println(".heading p {");
        out.println("color: #777;");
        out.println("font-size: 16px;");
        out.println("}");

        // =========================
        // FORM CARD
        // =========================

        out.println(".form-card {");
        out.println("background: white;");
        out.println("padding: 35px;");
        out.println("border-radius: 22px;");
        out.println("box-shadow: 0 12px 30px rgba(0,0,0,0.10);");
        out.println("}");

        // =========================
        // STUDENT INFORMATION
        // =========================

        out.println(".student-info {");
        out.println("background: #f5f3ff;");
        out.println("padding: 20px;");
        out.println("border-radius: 15px;");
        out.println("margin-bottom: 25px;");
        out.println("border-left: 5px solid #667eea;");
        out.println("}");

        out.println(".student-info h2 {");
        out.println("color: #4c1d95;");
        out.println("margin-bottom: 10px;");
        out.println("}");

        out.println(".student-info p {");
        out.println("color: #555;");
        out.println("margin-top: 6px;");
        out.println("}");

        // =========================
        // FORM GROUP
        // =========================

        out.println(".form-group {");
        out.println("margin-bottom: 22px;");
        out.println("}");

        out.println(".form-group label {");
        out.println("display: block;");
        out.println("font-weight: bold;");
        out.println("color: #444;");
        out.println("margin-bottom: 8px;");
        out.println("}");

        out.println(".form-group input,");
        out.println(".form-group textarea {");
        out.println("width: 100%;");
        out.println("padding: 13px;");
        out.println("border: 1px solid #ddd;");
        out.println("border-radius: 10px;");
        out.println("font-size: 15px;");
        out.println("font-family: Arial, sans-serif;");
        out.println("outline: none;");
        out.println("}");

        out.println(".form-group input:focus,");
        out.println(".form-group textarea:focus {");
        out.println("border-color: #667eea;");
        out.println("box-shadow: 0 0 0 3px rgba(102,126,234,0.12);");
        out.println("}");

        out.println(".form-group textarea {");
        out.println("height: 160px;");
        out.println("resize: vertical;");
        out.println("}");

        // =========================
        // BUTTONS
        // =========================

        out.println(".button-group {");
        out.println("display: flex;");
        out.println("gap: 12px;");
        out.println("margin-top: 25px;");
        out.println("}");

        out.println(".submit-button {");
        out.println("flex: 1;");
        out.println("border: none;");
        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("color: white;");
        out.println("padding: 14px;");
        out.println("border-radius: 10px;");
        out.println("font-size: 16px;");
        out.println("font-weight: bold;");
        out.println("cursor: pointer;");
        out.println("transition: 0.3s;");
        out.println("}");

        out.println(".submit-button:hover {");
        out.println("transform: translateY(-2px);");
        out.println("box-shadow: 0 8px 18px rgba(102,126,234,0.30);");
        out.println("}");

        out.println(".cancel-button {");
        out.println("flex: 1;");
        out.println("text-align: center;");
        out.println("background: #eee;");
        out.println("color: #555;");
        out.println("padding: 14px;");
        out.println("border-radius: 10px;");
        out.println("text-decoration: none;");
        out.println("font-weight: bold;");
        out.println("}");

        // =========================
        // RESPONSIVE
        // =========================

        out.println("@media(max-width: 600px) {");

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

        out.println(".form-card {");
        out.println("padding: 25px;");
        out.println("}");

        out.println(".button-group {");
        out.println("flex-direction: column;");
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
                + "href='mentor-dashboard'>");

        out.println("Dashboard");

        out.println("</a>");

        out.println("<a class='nav-button' "
                + "href='mentor-my-students'>");

        out.println("My Students");

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

        out.println("<div class='heading'>");

        out.println("<h1>Give Suggestion</h1>");

        out.println("<p>");

        out.println(
                "Provide useful guidance to your student."
        );

        out.println("</p>");

        out.println("</div>");

        // =========================
        // FORM CARD
        // =========================

        out.println("<div class='form-card'>");

        // =========================
        // STUDENT INFORMATION
        // =========================

        out.println("<div class='student-info'>");

        out.println("<h2>");

        out.println("Student ID: " + studentId);

        out.println("</h2>");

        out.println("<p>");

        out.println(
                "You are giving feedback to this student."
        );

        out.println("</p>");

        out.println("</div>");

        // =========================
        // FORM
        // =========================

        out.println("<form method='post' "
                + "action='add-suggestion'>");

        // Hidden student ID
        out.println("<input type='hidden' "
                + "name='studentId' "
                + "value='" + studentId + "'>");

        // Title
        out.println("<div class='form-group'>");

        out.println("<label>Suggestion Title</label>");

        out.println("<input type='text' "
                + "name='title' "
                + "placeholder='Example: Improve Java Collections' "
                + "required>");

        out.println("</div>");

        // Message
        out.println("<div class='form-group'>");

        out.println("<label>Suggestion Message</label>");

        out.println("<textarea "
                + "name='message' "
                + "placeholder='Write your guidance for the student...' "
                + "required></textarea>");

        out.println("</div>");

        // Buttons
        out.println("<div class='button-group'>");

        out.println("<button type='submit' "
                + "class='submit-button'>");

        out.println("Send Suggestion");

        out.println("</button>");

        out.println("<a href='student-progress?id="
                + studentId
                + "' "
                + "class='cancel-button'>");

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

        // =========================
        // CHECK LOGIN
        // =========================

        HttpSession session =
                request.getSession(false);

        if (session == null ||
            session.getAttribute("user") == null) {

            response.sendRedirect("login.html");
            return;
        }

        // =========================
        // GET MENTOR
        // =========================

        User user =
                (User) session.getAttribute("user");

        if (!"MENTOR".equalsIgnoreCase(
                user.getRole())) {

            response.sendRedirect("dashboard");
            return;
        }

        // =========================
        // GET FORM DATA
        // =========================

        String studentIdString =
                request.getParameter("studentId");

        String title =
                request.getParameter("title");

        String message =
                request.getParameter("message");

        if (studentIdString == null ||
            title == null ||
            message == null ||
            title.trim().isEmpty() ||
            message.trim().isEmpty()) {

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
        // GET MENTOR PROFILE
        // =========================

        MentorDAO mentorDAO =
                new MentorDAOImpl();

        Mentor mentor =
                mentorDAO.getMentorByUserId(
                        user.getId()
                );

        if (mentor == null) {

            response.sendRedirect("mentor");
            return;
        }

        // =========================
        // CREATE SUGGESTION
        // =========================

        Suggestion suggestion =
                new Suggestion(
                        mentor.getId(),
                        studentId,
                        title.trim(),
                        message.trim()
                );

        // =========================
        // SAVE TO DATABASE
        // =========================

        SuggestionDAO suggestionDAO =
                new SuggestionDAOImpl();

        suggestionDAO.addSuggestion(
                suggestion
        );

        // =========================
        // RETURN TO STUDENT PROGRESS
        // =========================

        response.sendRedirect(
                "student-progress?id="
                + studentId
        );
    }
}
