
package com.studysync.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.studysync.DAO.NoteDAO;
import com.studysync.DAO.SubjectDAO;
import com.studysync.DAOImpl.NoteDAOImpl;
import com.studysync.DAOImpl.SubjectDAOImpl;
import com.studysync.model.Note;
import com.studysync.model.Subject;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/add-note")
public class AddNoteServlet extends HttpServlet {

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

        out.println("<meta name='viewport' "
                + "content='width=device-width, initial-scale=1.0'>");

        out.println("<title>StudySync - Add Note</title>");

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

        out.println(".navbar {");

        out.println("height: 75px;");

        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");

        out.println("display: flex;");

        out.println("align-items: center;");

        out.println("justify-content: space-between;");

        out.println("padding: 0 50px;");

        out.println("color: white;");

        out.println("box-shadow: 0 4px 15px rgba(0,0,0,0.15);");

        out.println("}");

        out.println(".logo {");

        out.println("font-size: 28px;");

        out.println("font-weight: bold;");

        out.println("}");

        out.println(".back {");

        out.println("text-decoration: none;");

        out.println("color: white;");

        out.println("background: rgba(255,255,255,0.2);");

        out.println("padding: 10px 20px;");

        out.println("border-radius: 25px;");

        out.println("font-weight: bold;");

        out.println("}");

        out.println(".container {");

        out.println("width: 90%;");

        out.println("max-width: 750px;");

        out.println("margin: 45px auto;");

        out.println("}");

        out.println(".form-card {");

        out.println("background: white;");

        out.println("padding: 40px;");

        out.println("border-radius: 22px;");

        out.println("box-shadow: 0 10px 30px rgba(0,0,0,0.10);");

        out.println("}");

        out.println(".form-card h1 {");

        out.println("color: #667eea;");

        out.println("margin-bottom: 10px;");

        out.println("}");

        out.println(".subtitle {");

        out.println("color: #777;");

        out.println("margin-bottom: 30px;");

        out.println("}");

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

        out.println(".form-group textarea,");

        out.println(".form-group select {");

        out.println("width: 100%;");

        out.println("padding: 13px;");

        out.println("border: 1px solid #ddd;");

        out.println("border-radius: 10px;");

        out.println("font-size: 15px;");

        out.println("outline: none;");

        out.println("}");

        out.println(".form-group textarea {");

        out.println("height: 220px;");

        out.println("resize: vertical;");

        out.println("}");

        out.println(".save-button {");

        out.println("width: 100%;");

        out.println("padding: 14px;");

        out.println("border: none;");

        out.println("border-radius: 12px;");

        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");

        out.println("color: white;");

        out.println("font-size: 17px;");

        out.println("font-weight: bold;");

        out.println("cursor: pointer;");

        out.println("}");

        out.println("@media(max-width: 600px) {");

        out.println(".navbar {");

        out.println("padding: 0 20px;");

        out.println("}");

        out.println(".form-card {");

        out.println("padding: 25px;");

        out.println("}");

        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        // Navbar
        out.println("<div class='navbar'>");

        out.println("<div class='logo'>📚 StudySync</div>");

        out.println("<a href='notes' class='back'>");

        out.println("← My Notes");

        out.println("</a>");

        out.println("</div>");

        // Form
        out.println("<div class='container'>");

        out.println("<div class='form-card'>");

        out.println("<h1>📝 Create New Note</h1>");

        out.println("<p class='subtitle'>");

        out.println("Save important concepts and study information.");

        out.println("</p>");

        out.println("<form action='add-note' method='post'>");

        // Title
        out.println("<div class='form-group'>");

        out.println("<label>Note Title</label>");

        out.println("<input type='text' "
                + "name='title' "
                + "placeholder='Example: Java Collections' "
                + "required>");

        out.println("</div>");

        // Subject
        out.println("<div class='form-group'>");

        out.println("<label>Subject</label>");

        out.println("<select name='subjectId' required>");

        out.println("<option value=''>Select Subject</option>");

        for (Subject subject : subjects) {

            out.println("<option value='"
                    + subject.getId()
                    + "'>");

            out.println(subject.getName());

            out.println("</option>");
        }

        out.println("</select>");

        out.println("</div>");

        // Content
        out.println("<div class='form-group'>");

        out.println("<label>Note Content</label>");

        out.println("<textarea name='content' "
                + "placeholder='Write your study notes here...' "
                + "required></textarea>");

        out.println("</div>");

        // Button
        out.println("<button type='submit' "
                + "class='save-button'>");

        out.println("Save Note");

        out.println("</button>");

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

        // Get form values
        String title =
                request.getParameter("title");

        String subjectIdString =
                request.getParameter("subjectId");

        String content =
                request.getParameter("content");

        // Convert subject ID
        int subjectId =
                Integer.parseInt(subjectIdString);

        // Create Note object
        Note note =
                new Note(
                        user.getId(),
                        subjectId,
                        title,
                        content
                );

        // Save note
        NoteDAO noteDAO =
                new NoteDAOImpl();

        noteDAO.addNote(note);

        // Go back to Notes page
        response.sendRedirect("notes");
    }
}

