
package com.studysync.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.studysync.DAO.SuggestionDAO;
import com.studysync.model.Suggestion;
import com.studysync.model.SuggestionView;
import com.studysync.util.DBConnection;

public class SuggestionDAOImpl implements SuggestionDAO {

    @Override
    public void addSuggestion(Suggestion suggestion) {

        String sql =
                "INSERT INTO suggestion "
                + "(mentor_id, student_id, title, message) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, suggestion.getMentorId());
            ps.setInt(2, suggestion.getStudentId());
            ps.setString(3, suggestion.getTitle());
            ps.setString(4, suggestion.getMessage());

            ps.executeUpdate();

            System.out.println("Suggestion added successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public Suggestion getSuggestion(int id) {

        Suggestion suggestion = null;

        String sql =
                "SELECT * FROM suggestion "
                + "WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                suggestion =
                        new Suggestion(
                                rs.getInt("id"),
                                rs.getInt("mentor_id"),
                                rs.getInt("student_id"),
                                rs.getString("title"),
                                rs.getString("message"),
                                rs.getTimestamp("created_at")
                        );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return suggestion;
    }


    @Override
    public void updateSuggestion(Suggestion suggestion) {

        String sql =
                "UPDATE suggestion SET "
                + "title = ?, "
                + "message = ? "
                + "WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, suggestion.getTitle());
            ps.setString(2, suggestion.getMessage());
            ps.setInt(3, suggestion.getId());

            ps.executeUpdate();

            System.out.println("Suggestion updated successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteSuggestion(int id) {

        String sql =
                "DELETE FROM suggestion "
                + "WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Suggestion deleted successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Suggestion> getAllSuggestions() {

        List<Suggestion> suggestionList =
                new ArrayList<>();

        String sql =
                "SELECT * FROM suggestion "
                + "ORDER BY created_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Suggestion suggestion =
                        new Suggestion(
                                rs.getInt("id"),
                                rs.getInt("mentor_id"),
                                rs.getInt("student_id"),
                                rs.getString("title"),
                                rs.getString("message"),
                                rs.getTimestamp("created_at")
                        );

                suggestionList.add(suggestion);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return suggestionList;
    }


    @Override
    public List<Suggestion> getSuggestionsByStudentId(
            int studentId) {

        List<Suggestion> suggestionList =
                new ArrayList<>();

        String sql =
                "SELECT * FROM suggestion "
                + "WHERE student_id = ? "
                + "ORDER BY created_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Suggestion suggestion =
                        new Suggestion(
                                rs.getInt("id"),
                                rs.getInt("mentor_id"),
                                rs.getInt("student_id"),
                                rs.getString("title"),
                                rs.getString("message"),
                                rs.getTimestamp("created_at")
                        );

                suggestionList.add(suggestion);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return suggestionList;
    }


    @Override
    public List<Suggestion> getSuggestionsByMentorId(
            int mentorId) {

        List<Suggestion> suggestionList =
                new ArrayList<>();

        String sql =
                "SELECT * FROM suggestion "
                + "WHERE mentor_id = ? "
                + "ORDER BY created_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, mentorId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Suggestion suggestion =
                        new Suggestion(
                                rs.getInt("id"),
                                rs.getInt("mentor_id"),
                                rs.getInt("student_id"),
                                rs.getString("title"),
                                rs.getString("message"),
                                rs.getTimestamp("created_at")
                        );

                suggestionList.add(suggestion);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return suggestionList;
    }


    /* =====================================================
       GET SUGGESTIONS WITH MENTOR NAME
       ===================================================== */

    @Override
    public List<SuggestionView> getSuggestionViewsByStudentId(
            int studentId) {

        List<SuggestionView> suggestionViews =
                new ArrayList<>();

        String sql =
                "SELECT "
                + "s.id, "
                + "u.name AS mentor_name, "
                + "s.title, "
                + "s.message, "
                + "s.created_at "
                + "FROM suggestion s "
                + "INNER JOIN mentor m "
                + "ON s.mentor_id = m.id "
                + "INNER JOIN user u "
                + "ON m.user_id = u.id "
                + "WHERE s.student_id = ? "
                + "ORDER BY s.created_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                SuggestionView suggestionView =
                        new SuggestionView(
                                rs.getInt("id"),
                                rs.getString("mentor_name"),
                                rs.getString("title"),
                                rs.getString("message"),
                                rs.getTimestamp("created_at")
                        );

                suggestionViews.add(suggestionView);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return suggestionViews;
    }
}

