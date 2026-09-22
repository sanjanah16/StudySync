
package com.studysync.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.studysync.DAO.StudySessionDAO;
import com.studysync.model.StudySession;
import com.studysync.util.DBConnection;

public class StudySessionDAOImpl implements StudySessionDAO {

    @Override
    public void addStudySession(StudySession session) {

        String sql =
                "INSERT INTO study_session " +
                "(user_id, subject_id, session_date, start_time, " +
                "end_time, duration_minutes, topic, notes) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, session.getUserId());
            ps.setInt(2, session.getSubjectId());
            ps.setDate(3, session.getSessionDate());
            ps.setTime(4, session.getStartTime());
            ps.setTime(5, session.getEndTime());
            ps.setInt(6, session.getDurationMinutes());
            ps.setString(7, session.getTopic());
            ps.setString(8, session.getNotes());

            ps.executeUpdate();

            System.out.println("Study Session Added Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public StudySession getStudySession(int id) {

        String sql =
                "SELECT * FROM study_session WHERE id = ?";

        StudySession session = null;

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                session = new StudySession(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("subject_id"),
                        rs.getDate("session_date"),
                        rs.getTime("start_time"),
                        rs.getTime("end_time"),
                        rs.getInt("duration_minutes"),
                        rs.getString("topic"),
                        rs.getString("notes"),
                        rs.getTimestamp("created_at")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return session;
    }


    @Override
    public void updateStudySession(StudySession session) {

        String sql =
                "UPDATE study_session SET " +
                "subject_id = ?, " +
                "session_date = ?, " +
                "start_time = ?, " +
                "end_time = ?, " +
                "duration_minutes = ?, " +
                "topic = ?, " +
                "notes = ? " +
                "WHERE id = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, session.getSubjectId());
            ps.setDate(2, session.getSessionDate());
            ps.setTime(3, session.getStartTime());
            ps.setTime(4, session.getEndTime());
            ps.setInt(5, session.getDurationMinutes());
            ps.setString(6, session.getTopic());
            ps.setString(7, session.getNotes());
            ps.setInt(8, session.getId());

            ps.executeUpdate();

            System.out.println("Study Session Updated Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteStudySession(int id) {

        String sql =
                "DELETE FROM study_session WHERE id = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Study Session Deleted Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<StudySession> getAllStudySessions() {

        List<StudySession> sessions =
                new ArrayList<>();

        String sql =
                "SELECT * FROM study_session";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                StudySession session =
                        new StudySession(
                                rs.getInt("id"),
                                rs.getInt("user_id"),
                                rs.getInt("subject_id"),
                                rs.getDate("session_date"),
                                rs.getTime("start_time"),
                                rs.getTime("end_time"),
                                rs.getInt("duration_minutes"),
                                rs.getString("topic"),
                                rs.getString("notes"),
                                rs.getTimestamp("created_at")
                        );

                sessions.add(session);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sessions;
    }


    @Override
    public List<StudySession> getStudySessionsByUserId(int userId) {

        List<StudySession> sessions =
                new ArrayList<>();

        String sql =
                "SELECT * FROM study_session " +
                "WHERE user_id = ? " +
                "ORDER BY session_date DESC";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                StudySession session =
                        new StudySession(
                                rs.getInt("id"),
                                rs.getInt("user_id"),
                                rs.getInt("subject_id"),
                                rs.getDate("session_date"),
                                rs.getTime("start_time"),
                                rs.getTime("end_time"),
                                rs.getInt("duration_minutes"),
                                rs.getString("topic"),
                                rs.getString("notes"),
                                rs.getTimestamp("created_at")
                        );

                sessions.add(session);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sessions;
    }
}

