
package com.studysync.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.studysync.DAO.ProgressDAO;
import com.studysync.model.Progress;
import com.studysync.util.DBConnection;

public class ProgressDAOImpl implements ProgressDAO {

    @Override
    public void addProgress(Progress progress) {

        String sql =
                "INSERT INTO progress " +
                "(user_id, subject_id, completed_tasks, " +
                "total_tasks, progress_percentage) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, progress.getUserId());
            ps.setInt(2, progress.getSubjectId());
            ps.setInt(3, progress.getCompletedTasks());
            ps.setInt(4, progress.getTotalTasks());
            ps.setDouble(5, progress.getProgressPercentage());

            ps.executeUpdate();

            System.out.println("Progress Added Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public Progress getProgress(int id) {

        String sql =
                "SELECT * FROM progress WHERE id = ?";

        Progress progress = null;

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                progress = new Progress(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("subject_id"),
                        rs.getInt("completed_tasks"),
                        rs.getInt("total_tasks"),
                        rs.getDouble("progress_percentage"),
                        rs.getTimestamp("updated_at")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return progress;
    }


    @Override
    public void updateProgress(Progress progress) {

        String sql =
                "UPDATE progress SET " +
                "subject_id = ?, " +
                "completed_tasks = ?, " +
                "total_tasks = ?, " +
                "progress_percentage = ? " +
                "WHERE id = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, progress.getSubjectId());
            ps.setInt(2, progress.getCompletedTasks());
            ps.setInt(3, progress.getTotalTasks());
            ps.setDouble(4, progress.getProgressPercentage());
            ps.setInt(5, progress.getId());

            ps.executeUpdate();

            System.out.println("Progress Updated Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteProgress(int id) {

        String sql =
                "DELETE FROM progress WHERE id = ?";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Progress Deleted Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Progress> getAllProgress() {

        List<Progress> progressList =
                new ArrayList<>();

        String sql =
                "SELECT * FROM progress";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Progress progress = new Progress(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("subject_id"),
                        rs.getInt("completed_tasks"),
                        rs.getInt("total_tasks"),
                        rs.getDouble("progress_percentage"),
                        rs.getTimestamp("updated_at")
                );

                progressList.add(progress);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return progressList;
    }


    @Override
    public List<Progress> getProgressByUserId(int userId) {

        List<Progress> progressList =
                new ArrayList<>();

        String sql =
                "SELECT * FROM progress " +
                "WHERE user_id = ? " +
                "ORDER BY updated_at DESC";

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Progress progress = new Progress(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("subject_id"),
                        rs.getInt("completed_tasks"),
                        rs.getInt("total_tasks"),
                        rs.getDouble("progress_percentage"),
                        rs.getTimestamp("updated_at")
                );

                progressList.add(progress);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return progressList;
    }


    @Override
    public int getTotalTasks(int userId, int subjectId) {

        String sql =
                "SELECT COUNT(*) FROM task " +
                "WHERE user_id = ? AND subject_id = ?";

        int totalTasks = 0;

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, userId);
            ps.setInt(2, subjectId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                totalTasks = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalTasks;
    }


    @Override
    public int getCompletedTasks(int userId, int subjectId) {

        String sql =
                "SELECT COUNT(*) FROM task " +
                "WHERE user_id = ? " +
                "AND subject_id = ? " +
                "AND status = 'COMPLETED'";

        int completedTasks = 0;

        try (
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, userId);
            ps.setInt(2, subjectId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                completedTasks = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return completedTasks;
    }

}

