
package com.studysync.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.studysync.DAO.GoalDAO;
import com.studysync.model.Goal;
import com.studysync.util.DBConnection;

public class GoalDAOImpl implements GoalDAO {

    // Add Goal
    @Override
    public void addGoal(Goal goal) {

        String sql = "INSERT INTO goal "
                + "(user_id, title, description, target_date, status) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, goal.getUserId());
            ps.setString(2, goal.getTitle());
            ps.setString(3, goal.getDescription());
            ps.setDate(4, goal.getTargetDate());
            ps.setString(5, goal.getStatus());

            ps.executeUpdate();

            System.out.println("Goal Added Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Get Goal by ID
    @Override
    public Goal getGoal(int id) {

        String sql = "SELECT * FROM goal WHERE id = ?";

        Goal goal = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                goal = new Goal(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getDate("target_date"),
                    rs.getString("status"),
                    rs.getTimestamp("created_at")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return goal;
    }


    // Update Goal
    @Override
    public void updateGoal(Goal goal) {

        String sql = "UPDATE goal SET "
                + "title = ?, "
                + "description = ?, "
                + "target_date = ?, "
                + "status = ? "
                + "WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, goal.getTitle());
            ps.setString(2, goal.getDescription());
            ps.setDate(3, goal.getTargetDate());
            ps.setString(4, goal.getStatus());
            ps.setInt(5, goal.getId());

            ps.executeUpdate();

            System.out.println("Goal Updated Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Delete Goal
    @Override
    public void deleteGoal(int id) {

        String sql = "DELETE FROM goal WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Goal Deleted Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Get All Goals
    @Override
    public List<Goal> getAllGoals() {

        List<Goal> goals = new ArrayList<>();

        String sql = "SELECT * FROM goal ORDER BY created_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Goal goal = new Goal(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getDate("target_date"),
                    rs.getString("status"),
                    rs.getTimestamp("created_at")
                );

                goals.add(goal);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return goals;
    }


    // Get Goals by User ID
    @Override
    public List<Goal> getGoalsByUserId(int userId) {

        List<Goal> goals = new ArrayList<>();

        String sql = "SELECT * FROM goal "
                + "WHERE user_id = ? "
                + "ORDER BY created_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Goal goal = new Goal(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getDate("target_date"),
                    rs.getString("status"),
                    rs.getTimestamp("created_at")
                );

                goals.add(goal);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return goals;
    }
}

