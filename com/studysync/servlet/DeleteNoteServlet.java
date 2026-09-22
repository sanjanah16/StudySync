
package com.studysync.servlet;

import java.io.IOException;

import com.studysync.DAO.NoteDAO;
import com.studysync.DAOImpl.NoteDAOImpl;
import com.studysync.model.Note;
import com.studysync.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/delete-note")
public class DeleteNoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Check whether user is logged in
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.html");
            return;
        }

        User user = (User) session.getAttribute("user");

        String idParam = request.getParameter("id");

        // Check note ID
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect("notes");
            return;
        }

        int noteId = Integer.parseInt(idParam);

        NoteDAO noteDAO = new NoteDAOImpl();

        // Get note
        Note note = noteDAO.getNote(noteId);

        // Check whether note exists
        if (note == null) {
            response.sendRedirect("notes");
            return;
        }

        // Make sure the logged-in user owns this note
        if (note.getUserId() != user.getId()) {
            response.sendRedirect("notes");
            return;
        }

        // Delete note
        noteDAO.deleteNote(noteId);

        // Go back to Notes page
        response.sendRedirect("notes");
    }
}

