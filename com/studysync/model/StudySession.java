
package com.studysync.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class StudySession {

    private int id;
    private int userId;
    private int subjectId;
    private Date sessionDate;
    private Time startTime;
    private Time endTime;
    private int durationMinutes;
    private String topic;
    private String notes;
    private Timestamp createdAt;

    // No-argument constructor
    public StudySession() {
    }

    // Constructor for adding a new study session
    public StudySession(int userId,
                        int subjectId,
                        Date sessionDate,
                        Time startTime,
                        Time endTime,
                        int durationMinutes,
                        String topic,
                        String notes) {

        this.userId = userId;
        this.subjectId = subjectId;
        this.sessionDate = sessionDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationMinutes = durationMinutes;
        this.topic = topic;
        this.notes = notes;
    }

    // Full constructor
    public StudySession(int id,
                        int userId,
                        int subjectId,
                        Date sessionDate,
                        Time startTime,
                        Time endTime,
                        int durationMinutes,
                        String topic,
                        String notes,
                        Timestamp createdAt) {

        this.id = id;
        this.userId = userId;
        this.subjectId = subjectId;
        this.sessionDate = sessionDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.durationMinutes = durationMinutes;
        this.topic = topic;
        this.notes = notes;
        this.createdAt = createdAt;
    }

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

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}

