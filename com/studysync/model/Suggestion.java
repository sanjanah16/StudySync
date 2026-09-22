
package com.studysync.model;

import java.sql.Timestamp;

public class Suggestion {

    private int id;
    private int mentorId;
    private int studentId;
    private String title;
    private String message;
    private Timestamp createdAt;

    public Suggestion() {
    }

    public Suggestion(int mentorId,
                      int studentId,
                      String title,
                      String message) {

        this.mentorId = mentorId;
        this.studentId = studentId;
        this.title = title;
        this.message = message;
    }

    public Suggestion(int id,
                      int mentorId,
                      int studentId,
                      String title,
                      String message,
                      Timestamp createdAt) {

        this.id = id;
        this.mentorId = mentorId;
        this.studentId = studentId;
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

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

