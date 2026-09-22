
package com.studysync.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.studysync.DAO.NotificationDAO;
import com.studysync.model.Notification;
import com.studysync.util.DBConnection;

public class NotificationDAOImpl implements NotificationDAO {

    // Add Notification
    @Override
    public void addNotification(Notification notification) {

        String sql = "INSERT INTO notification "
                   + "(user_id, title, message, type, is_read) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, notification.getUserId());
            ps.setString(2, notification.getTitle());
            ps.setString(3, notification.getMessage());
            ps.setString(4, notification.getType());
            ps.setBoolean(5, notification.isRead());

            ps.executeUpdate();

            System.out.println("Notification Added Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get Notification by ID
    @Override
    public Notification getNotification(int id) {

        Notification notification = null;

        String sql = "SELECT * FROM notification WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                notification = new Notification(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("title"),
                    rs.getString("message"),
                    rs.getString("type"),
                    rs.getBoolean("is_read"),
                    rs.getTimestamp("created_at")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return notification;
    }

    // Update Notification
    @Override
    public void updateNotification(Notification notification) {

        String sql = "UPDATE notification SET "
                   + "title = ?, message = ?, type = ?, is_read = ? "
                   + "WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, notification.getTitle());
            ps.setString(2, notification.getMessage());
            ps.setString(3, notification.getType());
            ps.setBoolean(4, notification.isRead());
            ps.setInt(5, notification.getId());

            ps.executeUpdate();

            System.out.println("Notification Updated Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete Notification
    @Override
    public void deleteNotification(int id) {

        String sql = "DELETE FROM notification WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Notification Deleted Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get All Notifications
    @Override
    public List<Notification> getAllNotifications() {

        List<Notification> notifications = new ArrayList<>();

        String sql = "SELECT * FROM notification "
                   + "ORDER BY created_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Notification notification = new Notification(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("title"),
                    rs.getString("message"),
                    rs.getString("type"),
                    rs.getBoolean("is_read"),
                    rs.getTimestamp("created_at")
                );

                notifications.add(notification);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return notifications;
    }

    // Get Notifications by User ID
    @Override
    public List<Notification> getNotificationsByUserId(int userId) {

        List<Notification> notifications = new ArrayList<>();

        String sql = "SELECT * FROM notification "
                   + "WHERE user_id = ? "
                   + "ORDER BY created_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Notification notification = new Notification(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getString("title"),
                    rs.getString("message"),
                    rs.getString("type"),
                    rs.getBoolean("is_read"),
                    rs.getTimestamp("created_at")
                );

                notifications.add(notification);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return notifications;
    }

    // Mark Notification as Read
    @Override
    public void markAsRead(int id) {

        String sql = "UPDATE notification "
                   + "SET is_read = TRUE "
                   + "WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Notification Marked as Read");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

