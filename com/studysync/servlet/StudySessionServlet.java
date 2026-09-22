
package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.studysync.DAO.StudySessionDAO;
import com.studysync.DAOImpl.StudySessionDAOImpl;
import com.studysync.model.StudySession;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/study-sessions")
public class StudySessionServlet extends HttpServlet {

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

        User user = (User) session.getAttribute("user");

        StudySessionDAO sessionDAO =
                new StudySessionDAOImpl();

        List<StudySession> sessions =
                sessionDAO.getStudySessionsByUserId(user.getId());

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");

        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println("<meta name='viewport' " +
                "content='width=device-width, initial-scale=1.0'>");

        out.println("<title>StudySync - Study Sessions</title>");

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
        out.println("}");

        out.println(".nav-button {");
        out.println("text-decoration: none;");
        out.println("padding: 10px 18px;");
        out.println("border-radius: 20px;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".dashboard {");
        out.println("background: rgba(255,255,255,0.2);");
        out.println("color: white;");
        out.println("}");

        out.println(".add-button {");
        out.println("background: white;");
        out.println("color: #764ba2;");
        out.println("}");

        /* CONTAINER */

        out.println(".container {");
        out.println("width: 90%;");
        out.println("max-width: 1100px;");
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

        /* SESSION GRID */

        out.println(".session-grid {");
        out.println("display: grid;");
        out.println("grid-template-columns: repeat(3, 1fr);");
        out.println("gap: 25px;");
        out.println("}");

        /* SESSION CARD */

        out.println(".session-card {");
        out.println("background: white;");
        out.println("padding: 25px;");
        out.println("border-radius: 18px;");
        out.println("box-shadow: 0 8px 20px rgba(0,0,0,0.08);");
        out.println("transition: 0.3s;");
        out.println("}");

        out.println(".session-card:hover {");
        out.println("transform: translateY(-6px);");
        out.println("box-shadow: 0 15px 30px rgba(0,0,0,0.12);");
        out.println("}");

        out.println(".session-icon {");
        out.println("font-size: 18px;");
        out.println("margin-bottom: 15px;");
        out.println("color: #764ba2;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".session-card h2 {");
        out.println("color: #667eea;");
        out.println("margin-bottom: 15px;");
        out.println("}");

        out.println(".session-details {");
        out.println("line-height: 2;");
        out.println("color: #555;");
        out.println("}");

        /* TOPIC */

        out.println(".topic {");
        out.println("margin-top: 15px;");
        out.println("padding: 12px;");
        out.println("background: #f3f0ff;");
        out.println("border-radius: 10px;");
        out.println("color: #5b3fa3;");
        out.println("}");

        /* NOTES */

        out.println(".notes {");
        out.println("margin-top: 10px;");
        out.println("padding: 12px;");
        out.println("background: #fff8e7;");
        out.println("border-radius: 10px;");
        out.println("color: #765d20;");
        out.println("}");

        /* ACTION BUTTONS */

        out.println(".action-buttons {");
        out.println("display: flex;");
        out.println("gap: 10px;");
        out.println("margin-top: 20px;");
        out.println("}");

        out.println(".edit-button {");
        out.println("flex: 1;");
        out.println("text-align: center;");
        out.println("text-decoration: none;");
        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("color: white;");
        out.println("padding: 11px;");
        out.println("border-radius: 10px;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".edit-button:hover {");
        out.println("opacity: 0.9;");
        out.println("}");

        out.println(".delete-button {");
        out.println("flex: 1;");
        out.println("text-align: center;");
        out.println("text-decoration: none;");
        out.println("background: #ff4d4d;");
        out.println("color: white;");
        out.println("padding: 11px;");
        out.println("border-radius: 10px;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".delete-button:hover {");
        out.println("background: #e63939;");
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

        out.println(".session-grid {");
        out.println("grid-template-columns: 1fr;");
        out.println("}");

        out.println(".navbar {");
        out.println("padding: 20px;");
        out.println("}");

        out.println(".nav-buttons {");
        out.println("flex-direction: column;");
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

        out.println("<div class='logo'>StudySync</div>");

        out.println("<div class='nav-buttons'>");

        out.println("<a class='nav-button add-button' " +
                "href='add-study-session'>");

        out.println("+ Add Study Session");

        out.println("</a>");

        out.println("<a class='nav-button dashboard' " +
                "href='dashboard'>");

        out.println("Dashboard");

        out.println("</a>");

        out.println("</div>");

        out.println("</div>");

        /* MAIN CONTAINER */

        out.println("<div class='container'>");

        out.println("<div class='heading'>");

        out.println("<h1>My Study Sessions</h1>");

        out.println("<p>");
        out.println("Track your study time and learning progress.");
        out.println("</p>");

        out.println("</div>");

        /* IF NO SESSIONS */

        if (sessions.isEmpty()) {

            out.println("<div class='empty'>");

            out.println("<h2>No study sessions yet</h2>");

            out.println("<p>");

            out.println(
                "Click '+ Add Study Session' to record your first session."
            );

            out.println("</p>");

            out.println("</div>");

        } else {

            out.println("<div class='session-grid'>");

            /* DISPLAY EACH SESSION */

            for (StudySession studySession : sessions) {

                out.println("<div class='session-card'>");

                out.println("<div class='session-icon'>");
                out.println("Study Session");
                out.println("</div>");

                out.println("<h2>");

                if (studySession.getTopic() != null &&
                    !studySession.getTopic().isEmpty()) {

                    out.println(studySession.getTopic());

                } else {

                    out.println("Study Session");
                }

                out.println("</h2>");

                /* SESSION DETAILS */

                out.println("<div class='session-details'>");

                out.println("<div>");

                out.println("<strong>Date:</strong> " +
                        studySession.getSessionDate());

                out.println("</div>");

                out.println("<div>");

                out.println("<strong>Start:</strong> " +
                        studySession.getStartTime());

                out.println("</div>");

                out.println("<div>");

                out.println("<strong>End:</strong> " +
                        studySession.getEndTime());

                out.println("</div>");

                out.println("<div>");

                out.println("<strong>Duration:</strong> " +
                        studySession.getDurationMinutes() +
                        " minutes");

                out.println("</div>");

                out.println("</div>");

                /* TOPIC */

                if (studySession.getTopic() != null &&
                    !studySession.getTopic().isEmpty()) {

                    out.println("<div class='topic'>");

                    out.println("<strong>Topic:</strong> " +
                            studySession.getTopic());

                    out.println("</div>");
                }

                /* NOTES */

                if (studySession.getNotes() != null &&
                    !studySession.getNotes().isEmpty()) {

                    out.println("<div class='notes'>");

                    out.println("<strong>Notes:</strong> " +
                            studySession.getNotes());

                    out.println("</div>");
                }

                /* EDIT AND DELETE BUTTONS */

                out.println("<div class='action-buttons'>");

                /* EDIT */

                out.println("<a class='edit-button' " +
                        "href='edit-study-session?id=" +
                        studySession.getId() +
                        "'>");

                out.println("Edit");

                out.println("</a>");

                /* DELETE */

                out.println("<a class='delete-button' " +
                        "href='delete-study-session?id=" +
                        studySession.getId() +
                        "' " +
                        "onclick=\"return confirm('Are you sure you want to delete this study session?');\">");

                out.println("Delete");

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

