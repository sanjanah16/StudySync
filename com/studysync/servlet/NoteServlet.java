
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

@WebServlet("/notes")
public class NoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Check login session
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

        // Get notes for current user
        NoteDAO noteDAO =
                new NoteDAOImpl();

        List<Note> notes =
                noteDAO.getNotesByUserId(
                        user.getId()
                );

        // Get subjects
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

        out.println("<title>StudySync - My Notes</title>");

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

        out.println("color: #333;");

        out.println("}");

        // Navbar
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

        out.println(".nav-links {");

        out.println("display: flex;");

        out.println("gap: 12px;");

        out.println("}");

        out.println(".nav-links a {");

        out.println("text-decoration: none;");

        out.println("color: white;");

        out.println("background: rgba(255,255,255,0.18);");

        out.println("padding: 10px 18px;");

        out.println("border-radius: 20px;");

        out.println("font-weight: bold;");

        out.println("}");

        // Main container
        out.println(".container {");

        out.println("width: 90%;");

        out.println("max-width: 1200px;");

        out.println("margin: 40px auto;");

        out.println("}");

        out.println(".header {");

        out.println("display: flex;");

        out.println("justify-content: space-between;");

        out.println("align-items: center;");

        out.println("margin-bottom: 30px;");

        out.println("}");

        out.println(".header h1 {");

        out.println("font-size: 34px;");

        out.println("color: #4f46e5;");

        out.println("}");

        out.println(".header p {");

        out.println("color: #777;");

        out.println("margin-top: 8px;");

        out.println("}");

        // Add button
        out.println(".add-button {");

        out.println("text-decoration: none;");

        out.println("background: linear-gradient(135deg, #667eea, #764ba2);");

        out.println("color: white;");

        out.println("padding: 13px 22px;");

        out.println("border-radius: 12px;");

        out.println("font-weight: bold;");

        out.println("box-shadow: 0 6px 15px rgba(102,126,234,0.3);");

        out.println("}");

        // Notes grid
        out.println(".notes-grid {");

        out.println("display: grid;");

        out.println("grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));");

        out.println("gap: 25px;");

        out.println("}");

        // Note card
        out.println(".note-card {");

        out.println("background: white;");

        out.println("border-radius: 20px;");

        out.println("padding: 25px;");

        out.println("box-shadow: 0 8px 25px rgba(0,0,0,0.08);");

        out.println("transition: 0.3s;");

        out.println("}");

        out.println(".note-card:hover {");

        out.println("transform: translateY(-5px);");

        out.println("box-shadow: 0 12px 30px rgba(0,0,0,0.12);");

        out.println("}");

        out.println(".note-icon {");

        out.println("font-size: 32px;");

        out.println("margin-bottom: 15px;");

        out.println("}");

        out.println(".note-card h2 {");

        out.println("color: #4f46e5;");

        out.println("margin-bottom: 10px;");

        out.println("font-size: 21px;");

        out.println("}");

        out.println(".subject {");

        out.println("display: inline-block;");

        out.println("background: #eef2ff;");

        out.println("color: #4f46e5;");

        out.println("padding: 6px 12px;");

        out.println("border-radius: 15px;");

        out.println("font-size: 13px;");

        out.println("font-weight: bold;");

        out.println("margin-bottom: 15px;");

        out.println("}");

        out.println(".content {");

        out.println("color: #555;");

        out.println("line-height: 1.6;");

        out.println("margin-bottom: 20px;");

        out.println("white-space: pre-wrap;");

        out.println("}");

        // Buttons
        out.println(".actions {");

        out.println("display: flex;");

        out.println("gap: 10px;");

        out.println("}");

        out.println(".edit {");

        out.println("text-decoration: none;");

        out.println("background: #e0e7ff;");

        out.println("color: #4338ca;");

        out.println("padding: 9px 16px;");

        out.println("border-radius: 9px;");

        out.println("font-weight: bold;");

        out.println("}");

        out.println(".delete {");

        out.println("text-decoration: none;");

        out.println("background: #fee2e2;");

        out.println("color: #dc2626;");

        out.println("padding: 9px 16px;");

        out.println("border-radius: 9px;");

        out.println("font-weight: bold;");

        out.println("}");

        // Empty state
        out.println(".empty {");

        out.println("background: white;");

        out.println("padding: 50px;");

        out.println("text-align: center;");

        out.println("border-radius: 20px;");

        out.println("box-shadow: 0 8px 25px rgba(0,0,0,0.08);");

        out.println("}");

        out.println(".empty h2 {");

        out.println("color: #667eea;");

        out.println("margin-bottom: 10px;");

        out.println("}");

        // Responsive
        out.println("@media(max-width: 700px) {");

        out.println(".navbar {");

        out.println("padding: 0 20px;");

        out.println("}");

        out.println(".logo {");

        out.println("font-size: 22px;");

        out.println("}");

        out.println(".nav-links a {");

        out.println("padding: 8px 12px;");

        out.println("}");

        out.println(".header {");

        out.println("flex-direction: column;");

        out.println("align-items: flex-start;");

        out.println("gap: 20px;");

        out.println("}");

        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        // Navbar
        out.println("<div class='navbar'>");

        out.println("<div class='logo'>📚 StudySync</div>");

        out.println("<div class='nav-links'>");

        out.println("<a href='dashboard'>Dashboard</a>");

        out.println("<a href='tasks'>Tasks</a>");

        out.println("</div>");

        out.println("</div>");

        // Main
        out.println("<div class='container'>");

        out.println("<div class='header'>");

        out.println("<div>");

        out.println("<h1>📝 My Notes</h1>");

        out.println("<p>Keep your important study notes organized.</p>");

        out.println("</div>");

        out.println("<a href='add-note' class='add-button'>");

        out.println("+ Add Note");

        out.println("</a>");

        out.println("</div>");

        // Check notes
        if (notes.isEmpty()) {

            out.println("<div class='empty'>");

            out.println("<h2>No Notes Yet</h2>");

            out.println("<p>Create your first study note to get started.</p>");

            out.println("</div>");

        } else {

            out.println("<div class='notes-grid'>");

            for (Note note : notes) {

                String subjectName =
                        "General";

                for (Subject subject : subjects) {

                    if (subject.getId()
                            == note.getSubjectId()) {

                        subjectName =
                                subject.getName();

                        break;
                    }
                }

                out.println("<div class='note-card'>");

                out.println("<div class='note-icon'>📖</div>");

                out.println("<h2>"
                        + note.getTitle()
                        + "</h2>");

                out.println("<span class='subject'>"
                        + subjectName
                        + "</span>");

                out.println("<div class='content'>"
                        + note.getContent()
                        + "</div>");

                out.println("<div class='actions'>");

                out.println("<a class='edit' "
                        + "href='edit-note?id="
                        + note.getId()
                        + "'>");

                out.println("Edit");

                out.println("</a>");

                out.println("<a class='delete' "
                        + "href='delete-note?id="
                        + note.getId()
                        + "' "
                        + "onclick=\"return confirm('Are you sure you want to delete this note?');\">");

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

