
package com.studysync.DAO;

import java.util.List;

import com.studysync.model.Notification;

public interface NotificationDAO {

    void addNotification(Notification notification);

    Notification getNotification(int id);

    void updateNotification(Notification notification);

    void deleteNotification(int id);

    List<Notification> getAllNotifications();

    List<Notification> getNotificationsByUserId(int userId);

    void markAsRead(int id);
}

