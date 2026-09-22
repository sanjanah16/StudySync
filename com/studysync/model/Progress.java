package com.studysync.model;

import java.sql.Timestamp;

public class Progress {

    private int id;
    private int userId;
    private int subjectId;
    private int completedTasks;
    private int totalTasks;
    private double progressPercentage;
    private Timestamp updatedAt;

    // Default Constructor
    public Progress() {
    }

    // Constructor for adding progress
    public Progress(int userId,
                    int subjectId,
                    int completedTasks,
                    int totalTasks,
                    double progressPercentage) {

        this.userId = userId;
        this.subjectId = subjectId;
        this.completedTasks = completedTasks;
        this.totalTasks = totalTasks;
        this.progressPercentage = progressPercentage;
    }

    // Full Constructor
    public Progress(int id,
                    int userId,
                    int subjectId,
                    int completedTasks,
                    int totalTasks,
                    double progressPercentage,
                    Timestamp updatedAt) {

        this.id = id;
        this.userId = userId;
        this.subjectId = subjectId;
        this.completedTasks = completedTasks;
        this.totalTasks = totalTasks;
        this.progressPercentage = progressPercentage;
        this.updatedAt = updatedAt;
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

    public int getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(int completedTasks) {
        this.completedTasks = completedTasks;
    }

    public int getTotalTasks() {
        return totalTasks;
    }

    public void setTotalTasks(int totalTasks) {
        this.totalTasks = totalTasks;
    }

    public double getProgressPercentage() {
        return progressPercentage;
    }

    public void setProgressPercentage(double progressPercentage) {
        this.progressPercentage = progressPercentage;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}