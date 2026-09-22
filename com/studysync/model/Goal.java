
package com.studysync.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Goal {

    private int id;
    private int userId;
    private String title;
    private String description;
    private Date targetDate;
    private String status;
    private Timestamp createdAt;

    // No-argument constructor
    public Goal() {
    }

    // Constructor for adding a goal
    public Goal(int userId, String title, String description,
                Date targetDate, String status) {

        this.userId = userId;
        this.title = title;
        this.description = description;
        this.targetDate = targetDate;
        this.status = status;
    }

    // Full constructor
    public Goal(int id, int userId, String title, String description,
                Date targetDate, String status, Timestamp createdAt) {

        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.targetDate = targetDate;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}

