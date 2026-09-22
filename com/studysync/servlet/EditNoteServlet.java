
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

@WebServlet("/edit-note")
public class EditNoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        User user = (User) session.getAttribute("user");

        String idParam = request.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect("notes");
            return;
        }

        int noteId = Integer.parseInt(idParam);

        NoteDAO noteDAO = new NoteDAOImpl();
        Note note = noteDAO.getNote(noteId);

        if (note == null || note.getUserId() != user.getId()) {
            response.sendRedirect("notes");
            return;
        }

        SubjectDAO subjectDAO = new SubjectDAOImpl();
        List<Subject> subjects = subjectDAO.getAllSubjects();

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");

        out.println("<meta charset='UTF-8'>");
        out.println("<title>Edit Note - StudySync</title>");

        out.println("<style>");

        out.println("* {");
        out.println("    box-sizing: border-box;");
        out.println("}");

        out.println("body {");
        out.println("    margin: 0;");
        out.println("    font-family: Arial, sans-serif;");
        out.println("    background: linear-gradient(135deg, #eef2ff, #fdf2f8);");
        out.println("    min-height: 100vh;");
        out.println("}");

        out.println(".navbar {");
        out.println("    background: linear-gradient(90deg, #4f46e5, #7c3aed);");
        out.println("    padding: 18px 40px;");
        out.println("    color: white;");
        out.println("    display: flex;");
        out.println("    justify-content: space-between;");
        out.println("    align-items: center;");
        out.println("}");

        out.println(".logo {");
        out.println("    font-size: 25px;");
        out.println("    font-weight: bold;");
        out.println("}");

        out.println(".nav-links a {");
        out.println("    color: white;");
        out.println("    text-decoration: none;");
        out.println("    margin-left: 25px;");
        out.println("    font-size: 15px;");
        out.println("}");

        out.println(".container {");
        out.println("    width: 90%;");
        out.println("    max-width: 850px;");
        out.println("    margin: 50px auto;");
        out.println("}");

        out.println(".card {");
        out.println("    background: white;");
        out.println("    padding: 35px;");
        out.println("    border-radius: 20px;");
        out.println("    box-shadow: 0 12px 30px rgba(0,0,0,0.10);");
        out.println("}");

        out.println("h1 {");
        out.println("    color: #312e81;");
        out.println("    margin-top: 0;");
        out.println("}");

        out.println(".subtitle {");
        out.println("    color: #666;");
        out.println("    margin-bottom: 30px;");
        out.println("}");

        out.println("label {");
        out.println("    display: block;");
        out.println("    margin-top: 20px;");
        out.println("    margin-bottom: 8px;");
        out.println("    font-weight: bold;");
        out.println("    color: #333;");
        out.println("}");

        out.println("input, select, textarea {");
        out.println("    width: 100%;");
        out.println("    padding: 13px;");
        out.println("    border: 1px solid #d1d5db;");
        out.println("    border-radius: 10px;");
        out.println("    font-size: 15px;");
        out.println("}");

        out.println("textarea {");
        out.println("    min-height: 180px;");
        out.println("    resize: vertical;");
        out.println("}");

        out.println("input:focus, select:focus, textarea:focus {");
        out.println("    outline: none;");
        out.println("    border-color: #6366f1;");
        out.println("    box-shadow: 0 0 0 3px rgba(99,102,241,0.12);");
        out.println("}");

        out.println(".buttons {");
        out.println("    margin-top: 30px;");
        out.println("    display: flex;");
        out.println("    gap: 15px;");
        out.println("}");

        out.println(".btn {");
        out.println("    padding: 13px 25px;");
        out.println("    border-radius: 10px;");
        out.println("    border: none;");
        out.println("    cursor: pointer;");
        out.println("    text-decoration: none;");
        out.println("    font-size: 15px;");
        out.println("}");

        out.println(".save-btn {");
        out.println("    background: linear-gradient(90deg, #4f46e5, #7c3aed);");
        out.println("    color: white;");
        out.println("}");

        out.println(".cancel-btn {");
        out.println("    background: #e5e7eb;");
        out.println("    color: #333;");
        out.println("}");

        out.println("</style>");

        out.println("</head>");

        out.println("<body>");

        out.println("<div class='navbar'>");

        out.println("<div class='logo'>StudySync</div>");

        out.println("<div class='nav-links'>");
        out.println("<a href='dashboard'>Dashboard</a>");
        out.println("<a href='notes'>Notes</a>");
        out.println("<a href='tasks'>Tasks</a>");
        out.println("</div>");

        out.println("</div>");

        out.println("<div class='container'>");

        out.println("<div class='card'>");

        out.println("<h1>Edit Note</h1>");
        out.println("<p class='subtitle'>Update your study note and keep your knowledge organized.</p>");

        out.println("<form action='edit-note' method='post'>");

        out.println("<input type='hidden' name='id' value='" + note.getId() + "'>");

        out.println("<label>Note Title</label>");
        out.println("<input type='text' name='title' value='"
                + note.getTitle().replace("'", "&#39;")
                + "' required>");

        out.println("<label>Subject</label>");

        out.println("<select name='subjectId' required>");

        for (Subject subject : subjects) {

            String selected = "";

            if (subject.getId() == note.getSubjectId()) {
                selected = "selected";
            }

            out.println("<option value='" + subject.getId() + "' "
                    + selected + ">"
                    + subject.getName()
                    + "</option>");
        }

        out.println("</select>");

        out.println("<label>Note Content</label>");

        out.println("<textarea name='content' required>"
                + note.getContent()
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("'", "&#39;")
                + "</textarea>");

        out.println("<div class='buttons'>");

        out.println("<button type='submit' class='btn save-btn'>Update Note</button>");

        out.println("<a href='notes' class='btn cancel-btn'>Cancel</a>");

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

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        User user = (User) session.getAttribute("user");

        int id = Integer.parseInt(request.getParameter("id"));

        String title = request.getParameter("title");
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        String content = request.getParameter("content");

        NoteDAO noteDAO = new NoteDAOImpl();

        Note oldNote = noteDAO.getNote(id);

        if (oldNote == null || oldNote.getUserId() != user.getId()) {
            response.sendRedirect("notes");
            return;
        }

        Note updatedNote = new Note();

        updatedNote.setId(id);
        updatedNote.setUserId(user.getId());
        updatedNote.setSubjectId(subjectId);
        updatedNote.setTitle(title);
        updatedNote.setContent(content);

        noteDAO.updateNote(updatedNote);

        response.sendRedirect("notes");
    }
}

