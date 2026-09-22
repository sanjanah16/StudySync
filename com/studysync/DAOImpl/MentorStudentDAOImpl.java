package com.studysync.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.studysync.DAO.MentorStudentDAO;
import com.studysync.model.User;
import com.studysync.util.DBConnection;

public class MentorStudentDAOImpl implements MentorStudentDAO {

    @Override
    public void assignStudent(int mentorId, int studentId) {

        String sql =
                "INSERT INTO mentor_student (mentor_id, student_id) " +
                "VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, mentorId);
            ps.setInt(2, studentId);

            ps.executeUpdate();

            System.out.println("Student assigned successfully");

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    @Override
    public void removeStudent(int mentorId, int studentId) {

        String sql =
                "DELETE FROM mentor_student " +
                "WHERE mentor_id = ? AND student_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, mentorId);
            ps.setInt(2, studentId);

            ps.executeUpdate();

            System.out.println("Student removed successfully");

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    @Override
    public List<User> getStudentsByMentorId(int mentorId) {

        List<User> studentList = new ArrayList<>();

        String sql =
                "SELECT u.* " +
                "FROM user u " +
                "INNER JOIN mentor_student ms " +
                "ON u.id = ms.student_id " +
                "WHERE ms.mentor_id = ? " +
                "AND u.role = 'STUDENT' " +
                "ORDER BY u.name";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, mentorId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                User user = new User();

                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getString("role"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                user.setLastLogin(rs.getTimestamp("last_login"));

                studentList.add(user);
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return studentList;
    }

    @Override
    public List<User> getAllStudents() {

        List<User> studentList = new ArrayList<>();

        String sql =
                "SELECT * FROM user " +
                "WHERE role = 'STUDENT' " +
                "ORDER BY name";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                User user = new User();

                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getString("role"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                user.setLastLogin(rs.getTimestamp("last_login"));

                studentList.add(user);
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return studentList;
    }

    @Override
    public boolean isStudentAssigned(int mentorId, int studentId) {

        String sql =
                "SELECT id FROM mentor_student " +
                "WHERE mentor_id = ? AND student_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, mentorId);
            ps.setInt(2, studentId);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }
}