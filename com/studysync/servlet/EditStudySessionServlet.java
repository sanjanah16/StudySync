
package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import com.studysync.DAO.SubjectDAO;
import com.studysync.DAO.StudySessionDAO;
import com.studysync.DAOImpl.SubjectDAOImpl;
import com.studysync.DAOImpl.StudySessionDAOImpl;
import com.studysync.model.Subject;
import com.studysync.model.StudySession;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/edit-study-session")
public class EditStudySessionServlet extends HttpServlet {

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

        String idString = request.getParameter("id");

        if (idString == null) {
            response.sendRedirect("study-sessions");
            return;
        }

        int id = Integer.parseInt(idString);

        StudySessionDAO sessionDAO =
                new StudySessionDAOImpl();

        StudySession studySession =
                sessionDAO.getStudySession(id);

        if (studySession == null) {
            response.sendRedirect("study-sessions");
            return;
        }

        User user = (User) session.getAttribute("user");

        if (studySession.getUserId() != user.getId()) {
            response.sendRedirect("study-sessions");
            return;
        }

        SubjectDAO subjectDAO =
                new SubjectDAOImpl();

        List<Subject> subjects =
                subjectDAO.getAllSubjects();

        response.setContentType("text/html");

        PrintWriter out =
                response.getWriter();

        out.println("<!DOCTYPE html>");

        out.println("<html>");

        out.println("<head>");

        out.println("<meta charset='UTF-8'>");

        out.println("<meta name='viewport' " +
                "content='width=device-width, initial-scale=1.0'>");

        out.println("<title>StudySync - Edit Study Session</title>");

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

        out.println(".back-button {");
        out.println("background: rgba(255,255,255,0.2);");
        out.println("color: white;");
        out.println("text-decoration: none;");
        out.println("padding: 10px 18px;");
        out.println("border-radius: 20px;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println(".container {");
        out.println("width: 90%;");
        out.println("max-width: 700px;");
        out.println("margin: 40px auto;");
        out.println("}");

        out.println(".form-card {");
        out.println("background: white;");
        out.println("padding: 35px;");
        out.println("border-radius: 20px;");
        out.println("box-shadow: 0 10px 30px rgba(0,0,0,0.08);");
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

        out.println(".form-group {");
        out.println("margin-bottom: 20px;");
        out.println("}");

        out.println("label {");
        out.println("display: block;");
        out.println("margin-bottom: 8px;");
        out.println("font-weight: bold;");
        out.println("color: #444;");
        out.println("}");

        out.println("input, select, textarea {");
        out.println("width: 100%;");
        out.println("padding: 12px;");
        out.println("border: 1px solid #ddd;");
        out.println("border-radius: 10px;");
        out.println("font-size: 15px;");
        out.println("outline: none;");
        out.println("}");

        out.println("textarea {");
        out.println("height: 100px;");
        out.println("resize: vertical;");
        out.println("}");

        out.println(".time-row {");
        out.println("display: grid;");
        out.println("grid-template-columns: 1fr 1fr;");
        out.println("gap: 15px;");
        out.println("}");

        out.println(".button-row {");
        out.println("display: flex;");
        out.println("gap: 15px;");
        out.println("margin-top: 25px;");
        out.println("}");

        out.println(".save-button {");
        out.println("flex: 1;");
        out.println("border: none;");
        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");
        out.println("color: white;");
        out.println("padding: 14px;");
        out.println("border-radius: 12px;");
        out.println("font-size: 16px;");
        out.println("font-weight: bold;");
        out.println("cursor: pointer;");
        out.println("}");

        out.println(".cancel-button {");
        out.println("flex: 1;");
        out.println("text-align: center;");
        out.println("text-decoration: none;");
        out.println("background: #eee;");
        out.println("color: #555;");
        out.println("padding: 14px;");
        out.println("border-radius: 12px;");
        out.println("font-weight: bold;");
        out.println("}");

        out.println("@media(max-width: 600px) {");

        out.println(".navbar {");
        out.println("padding: 20px;");
        out.println("}");

        out.println(".form-card {");
        out.println("padding: 25px;");
        out.println("}");

        out.println(".time-row {");
        out.println("grid-template-columns: 1fr;");
        out.println("}");

        out.println(".button-row {");
        out.println("flex-direction: column;");
        out.println("}");

        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        out.println("<div class='navbar'>");

        out.println("<div class='logo'>StudySync</div>");

        out.println("<a class='back-button' href='study-sessions'>");
        out.println("Study Sessions");
        out.println("</a>");

        out.println("</div>");

        out.println("<div class='container'>");

        out.println("<div class='form-card'>");

        out.println("<div class='heading'>");

        out.println("<h1>Edit Study Session</h1>");

        out.println("<p>");
        out.println("Update your study session details.");
        out.println("</p>");

        out.println("</div>");

        out.println("<form action='edit-study-session' method='post'>");

        out.println("<input type='hidden' " +
                "name='id' value='" +
                studySession.getId() +
                "'>");

        // Subject
        out.println("<div class='form-group'>");

        out.println("<label>Subject</label>");

        out.println("<select name='subjectId' required>");

        for (Subject subject : subjects) {

            String selected = "";

            if (subject.getId() ==
                    studySession.getSubjectId()) {

                selected = " selected";
            }

            out.println("<option value='" +
                    subject.getId() +
                    "'" +
                    selected +
                    ">");

            out.println(subject.getName());

            out.println("</option>");
        }

        out.println("</select>");

        out.println("</div>");

        // Date
        out.println("<div class='form-group'>");

        out.println("<label>Session Date</label>");

        out.println("<input type='date' " +
                "name='sessionDate' value='" +
                studySession.getSessionDate() +
                "' required>");

        out.println("</div>");

        // Time
        out.println("<div class='time-row'>");

        out.println("<div class='form-group'>");

        out.println("<label>Start Time</label>");

        out.println("<input type='time' " +
                "name='startTime' value='" +
                studySession.getStartTime().toString().substring(0, 5) +
                "' required>");

        out.println("</div>");

        out.println("<div class='form-group'>");

        out.println("<label>End Time</label>");

        out.println("<input type='time' " +
                "name='endTime' value='" +
                studySession.getEndTime().toString().substring(0, 5) +
                "' required>");

        out.println("</div>");

        out.println("</div>");

        // Duration
        out.println("<div class='form-group'>");

        out.println("<label>Duration (Minutes)</label>");

        out.println("<input type='number' " +
                "name='durationMinutes' " +
                "value='" +
                studySession.getDurationMinutes() +
                "' min='1' required>");

        out.println("</div>");

        // Topic
        out.println("<div class='form-group'>");

        out.println("<label>Topic</label>");

        out.println("<input type='text' " +
                "name='topic' " +
                "value='" +
                studySession.getTopic() +
                "'>");

        out.println("</div>");

        // Notes
        out.println("<div class='form-group'>");

        out.println("<label>Notes</label>");

        out.println("<textarea name='notes'>");

        if (studySession.getNotes() != null) {
            out.println(studySession.getNotes());
        }

        out.println("</textarea>");

        out.println("</div>");

        // Buttons
        out.println("<div class='button-row'>");

        out.println("<button type='submit' class='save-button'>");
        out.println("Update Study Session");
        out.println("</button>");

        out.println("<a href='study-sessions' class='cancel-button'>");
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

        HttpSession session =
                request.getSession(false);

        if (session == null ||
            session.getAttribute("user") == null) {

            response.sendRedirect("login.html");
            return;
        }

        User user =
                (User) session.getAttribute("user");

        int id =
                Integer.parseInt(
                        request.getParameter("id")
                );

        int subjectId =
                Integer.parseInt(
                        request.getParameter("subjectId")
                );

        Date sessionDate =
                Date.valueOf(
                        request.getParameter("sessionDate")
                );

        Time startTime =
                Time.valueOf(
                        request.getParameter("startTime") + ":00"
                );

        Time endTime =
                Time.valueOf(
                        request.getParameter("endTime") + ":00"
                );

        int durationMinutes =
                Integer.parseInt(
                        request.getParameter("durationMinutes")
                );

        String topic =
                request.getParameter("topic");

        String notes =
                request.getParameter("notes");

        StudySessionDAO sessionDAO =
                new StudySessionDAOImpl();

        StudySession existingSession =
                sessionDAO.getStudySession(id);

        if (existingSession == null ||
            existingSession.getUserId() != user.getId()) {

            response.sendRedirect("study-sessions");
            return;
        }

        StudySession studySession =
                new StudySession(
                        id,
                        user.getId(),
                        subjectId,
                        sessionDate,
                        startTime,
                        endTime,
                        durationMinutes,
                        topic,
                        notes,
                        existingSession.getCreatedAt()
                );

        sessionDAO.updateStudySession(studySession);

        response.sendRedirect("study-sessions");
    }
}
