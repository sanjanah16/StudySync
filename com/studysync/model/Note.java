
package com.studysync.model;

import java.sql.Timestamp;

public class Note {

    private int id;
    private int userId;
    private int subjectId;
    private String title;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // No-argument constructor
    public Note() {
    }

    // Constructor for adding a new note
    public Note(int userId,
                int subjectId,
                String title,
                String content) {

        this.userId = userId;
        this.subjectId = subjectId;
        this.title = title;
        this.content = content;
    }

    // Full constructor
    public Note(int id,
                int userId,
                int subjectId,
                String title,
                String content,
                Timestamp createdAt,
                Timestamp updatedAt) {

        this.id = id;
        this.userId = userId;
        this.subjectId = subjectId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}

