package com.studysync.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Task {

    private int id;
    private int userId;
    private int subjectId;
    private String title;
    private String description;
    private Date dueDate;
    private String priority;
    private String status;
    private Timestamp createdAt;

    // No argument constructor
    public Task() {
    }

    // Constructor for adding task
    public Task(int userId,
                int subjectId,
                String title,
                String description,
                Date dueDate,
                String priority,
                String status) {

        this.userId = userId;
        this.subjectId = subjectId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
    }

    // Full constructor
    public Task(int id,
                int userId,
                int subjectId,
                String title,
                String description,
                Date dueDate,
                String priority,
                String status,
                Timestamp createdAt) {

        this.id = id;
        this.userId = userId;
        this.subjectId = subjectId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
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

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
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