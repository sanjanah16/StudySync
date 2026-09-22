
package com.studysync.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.studysync.DAO.NoteDAO;
import com.studysync.model.Note;
import com.studysync.util.DBConnection;

public class NoteDAOImpl implements NoteDAO {

    @Override
    public void addNote(Note note) {

        String sql = "INSERT INTO note "
                + "(user_id, subject_id, title, content) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, note.getUserId());
            ps.setInt(2, note.getSubjectId());
            ps.setString(3, note.getTitle());
            ps.setString(4, note.getContent());

            ps.executeUpdate();

            System.out.println("Note Added Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public Note getNote(int id) {

        String sql = "SELECT * FROM note WHERE id = ?";

        Note note = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                note = new Note(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("subject_id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return note;
    }


    @Override
    public void updateNote(Note note) {

        String sql = "UPDATE note SET "
                + "subject_id = ?, "
                + "title = ?, "
                + "content = ? "
                + "WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, note.getSubjectId());
            ps.setString(2, note.getTitle());
            ps.setString(3, note.getContent());
            ps.setInt(4, note.getId());

            ps.executeUpdate();

            System.out.println("Note Updated Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteNote(int id) {

        String sql = "DELETE FROM note WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Note Deleted Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Note> getAllNotes() {

        List<Note> notes = new ArrayList<>();

        String sql = "SELECT * FROM note ORDER BY created_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Note note = new Note(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("subject_id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );

                notes.add(note);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return notes;
    }


    @Override
    public List<Note> getNotesByUserId(int userId) {

        List<Note> notes = new ArrayList<>();

        String sql = "SELECT * FROM note "
                + "WHERE user_id = ? "
                + "ORDER BY created_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Note note = new Note(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("subject_id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );

                notes.add(note);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return notes;
    }
}

