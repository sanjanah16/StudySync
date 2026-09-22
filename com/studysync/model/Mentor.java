package com.studysync.model;

import java.sql.Timestamp;

public class Mentor {

    private int id;
    private int userId;
    private String userName;   // NEW
    private String specialization;
    private int experienceYears;
    private String bio;
    private Timestamp createdAt;

    // Default constructor
    public Mentor() {
    }

    // Constructor without id and createdAt
    public Mentor(int userId,
                  String specialization,
                  int experienceYears,
                  String bio) {

        this.userId = userId;
        this.specialization = specialization;
        this.experienceYears = experienceYears;
        this.bio = bio;
    }

    // Full constructor
    public Mentor(int id,
                  int userId,
                  String specialization,
                  int experienceYears,
                  String bio,
                  Timestamp createdAt) {

        this.id = id;
        this.userId = userId;
        this.specialization = specialization;
        this.experienceYears = experienceYears;
        this.bio = bio;
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

    // NEW: User Name
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}