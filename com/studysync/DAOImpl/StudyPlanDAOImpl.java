
package com.studysync.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.studysync.DAO.StudyPlanDAO;
import com.studysync.model.StudyPlan;
import com.studysync.util.DBConnection;

public class StudyPlanDAOImpl implements StudyPlanDAO {

    @Override
    public void addStudyPlan(StudyPlan studyPlan) {

        String sql = "INSERT INTO study_plan "
                + "(user_id, subject_id, title, description, "
                + "start_date, end_date, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studyPlan.getUserId());
            ps.setInt(2, studyPlan.getSubjectId());
            ps.setString(3, studyPlan.getTitle());
            ps.setString(4, studyPlan.getDescription());
            ps.setDate(5, studyPlan.getStartDate());
            ps.setDate(6, studyPlan.getEndDate());
            ps.setString(7, studyPlan.getStatus());

            ps.executeUpdate();

            System.out.println("Study Plan Added Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public StudyPlan getStudyPlan(int id) {

        StudyPlan studyPlan = null;

        String sql = "SELECT * FROM study_plan WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                studyPlan = new StudyPlan(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("subject_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return studyPlan;
    }

    @Override
    public void updateStudyPlan(StudyPlan studyPlan) {

        String sql = "UPDATE study_plan SET "
                + "subject_id = ?, "
                + "title = ?, "
                + "description = ?, "
                + "start_date = ?, "
                + "end_date = ?, "
                + "status = ? "
                + "WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studyPlan.getSubjectId());
            ps.setString(2, studyPlan.getTitle());
            ps.setString(3, studyPlan.getDescription());
            ps.setDate(4, studyPlan.getStartDate());
            ps.setDate(5, studyPlan.getEndDate());
            ps.setString(6, studyPlan.getStatus());
            ps.setInt(7, studyPlan.getId());

            ps.executeUpdate();

            System.out.println("Study Plan Updated Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudyPlan(int id) {

        String sql = "DELETE FROM study_plan WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Study Plan Deleted Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<StudyPlan> getAllStudyPlans() {

        List<StudyPlan> studyPlans = new ArrayList<>();

        String sql = "SELECT * FROM study_plan "
                + "ORDER BY created_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                StudyPlan studyPlan = new StudyPlan(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("subject_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at")
                );

                studyPlans.add(studyPlan);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return studyPlans;
    }

    @Override
    public List<StudyPlan> getStudyPlansByUserId(int userId) {

        List<StudyPlan> studyPlans = new ArrayList<>();

        String sql = "SELECT * FROM study_plan "
                + "WHERE user_id = ? "
                + "ORDER BY created_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                StudyPlan studyPlan = new StudyPlan(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("subject_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getString("status"),
                        rs.getTimestamp("created_at")
                );

                studyPlans.add(studyPlan);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return studyPlans;
    }
}

