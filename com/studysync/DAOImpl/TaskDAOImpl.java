package com.studysync.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.studysync.DAO.TaskDAO;
import com.studysync.model.Task;
import com.studysync.util.DBConnection;

public class TaskDAOImpl implements TaskDAO {

    // ================= ADD TASK =================

    @Override
    public void addTask(Task task) {

        String sql = "INSERT INTO task "
                   + "(user_id, subject_id, title, description, "
                   + "due_date, priority, status) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, task.getUserId());
            ps.setInt(2, task.getSubjectId());
            ps.setString(3, task.getTitle());
            ps.setString(4, task.getDescription());
            ps.setDate(5, task.getDueDate());
            ps.setString(6, task.getPriority());
            ps.setString(7, task.getStatus());

            ps.executeUpdate();

            System.out.println("Task Added Successfully");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ================= GET TASK =================

    @Override
    public Task getTask(int id) {

        Task task = null;

        String sql = "SELECT * FROM task WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                task = new Task(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getInt("subject_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getDate("due_date"),
                    rs.getString("priority"),
                    rs.getString("status"),
                    rs.getTimestamp("created_at")
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return task;
    }

    // ================= UPDATE TASK =================

    @Override
    public void updateTask(Task task) {

        String sql = "UPDATE task SET "
                   + "subject_id=?, "
                   + "title=?, "
                   + "description=?, "
                   + "due_date=?, "
                   + "priority=?, "
                   + "status=? "
                   + "WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, task.getSubjectId());
            ps.setString(2, task.getTitle());
            ps.setString(3, task.getDescription());
            ps.setDate(4, task.getDueDate());
            ps.setString(5, task.getPriority());
            ps.setString(6, task.getStatus());
            ps.setInt(7, task.getId());

            ps.executeUpdate();

            System.out.println("Task Updated Successfully");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ================= DELETE TASK =================

    @Override
    public void deleteTask(int id) {

        String sql = "DELETE FROM task WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Task Deleted Successfully");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ================= GET ALL TASKS =================

    @Override
    public List<Task> getAllTasks() {

        List<Task> tasks = new ArrayList<>();

        String sql = "SELECT * FROM task";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Task task = new Task(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getInt("subject_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getDate("due_date"),
                    rs.getString("priority"),
                    rs.getString("status"),
                    rs.getTimestamp("created_at")
                );

                tasks.add(task);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return tasks;
    }

    // ================= GET TASKS BY USER =================

    @Override
    public List<Task> getTasksByUserId(int userId) {

        List<Task> tasks = new ArrayList<>();

        String sql = "SELECT * FROM task WHERE user_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Task task = new Task(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getInt("subject_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getDate("due_date"),
                    rs.getString("priority"),
                    rs.getString("status"),
                    rs.getTimestamp("created_at")
                );

                tasks.add(task);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return tasks;
    }
}