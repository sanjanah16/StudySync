package com.studysync.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.studysync.DAO.MentorDAO;
import com.studysync.model.Mentor;
import com.studysync.util.DBConnection;

public class MentorDAOImpl implements MentorDAO {

    // ADD MENTOR
    @Override
    public void addMentor(Mentor mentor) {

        String sql =
                "INSERT INTO mentor "
                + "(user_id, specialization, experience_years, bio) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, mentor.getUserId());
            ps.setString(2, mentor.getSpecialization());
            ps.setInt(3, mentor.getExperienceYears());
            ps.setString(4, mentor.getBio());

            ps.executeUpdate();

            System.out.println("Mentor added successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // GET MENTOR BY ID
    @Override
    public Mentor getMentor(int id) {

        Mentor mentor = null;

        String sql =
                "SELECT m.*, u.name AS user_name "
                + "FROM mentor m "
                + "JOIN user u ON m.user_id = u.id "
                + "WHERE m.id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                mentor = new Mentor(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("specialization"),
                        rs.getInt("experience_years"),
                        rs.getString("bio"),
                        rs.getTimestamp("created_at")
                );

                mentor.setUserName(rs.getString("user_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mentor;
    }


    // GET MENTOR BY USER ID
    @Override
    public Mentor getMentorByUserId(int userId) {

        Mentor mentor = null;

        String sql =
                "SELECT m.*, u.name AS user_name "
                + "FROM mentor m "
                + "JOIN user u ON m.user_id = u.id "
                + "WHERE m.user_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                mentor = new Mentor(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("specialization"),
                        rs.getInt("experience_years"),
                        rs.getString("bio"),
                        rs.getTimestamp("created_at")
                );

                mentor.setUserName(rs.getString("user_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mentor;
    }


    // UPDATE MENTOR
    @Override
    public void updateMentor(Mentor mentor) {

        String sql =
                "UPDATE mentor SET "
                + "specialization = ?, "
                + "experience_years = ?, "
                + "bio = ? "
                + "WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, mentor.getSpecialization());
            ps.setInt(2, mentor.getExperienceYears());
            ps.setString(3, mentor.getBio());
            ps.setInt(4, mentor.getId());

            ps.executeUpdate();

            System.out.println("Mentor updated successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // DELETE MENTOR
    @Override
    public void deleteMentor(int id) {

        String sql =
                "DELETE FROM mentor WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Mentor deleted successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // GET ALL MENTORS
    @Override
    public List<Mentor> getAllMentors() {

        List<Mentor> mentorList = new ArrayList<>();

        String sql =
                "SELECT m.*, u.name AS user_name "
                + "FROM mentor m "
                + "JOIN user u ON m.user_id = u.id "
                + "ORDER BY m.created_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Mentor mentor = new Mentor(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("specialization"),
                        rs.getInt("experience_years"),
                        rs.getString("bio"),
                        rs.getTimestamp("created_at")
                );

                // Get mentor name from user table
                mentor.setUserName(rs.getString("user_name"));

                mentorList.add(mentor);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return mentorList;
    }
}