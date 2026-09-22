
package com.studysync.model;

import java.sql.Timestamp;

public class SuggestionView {

    private int id;
    private String mentorName;
    private String title;
    private String message;
    private Timestamp createdAt;

    public SuggestionView() {
    }

    public SuggestionView(int id,
                          String mentorName,
                          String title,
                          String message,
                          Timestamp createdAt) {

        this.id = id;
        this.mentorName = mentorName;
        this.title = title;
        this.message = message;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}

