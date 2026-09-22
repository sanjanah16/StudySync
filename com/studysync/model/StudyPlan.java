
package com.studysync.model;

import java.sql.Date;
import java.sql.Timestamp;

public class StudyPlan {

    private int id;
    private int userId;
    private int subjectId;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private String status;
    private Timestamp createdAt;

    public StudyPlan() {
    }

    public StudyPlan(int userId, int subjectId, String title,
                     String description, Date startDate,
                     Date endDate, String status) {

        this.userId = userId;
        this.subjectId = subjectId;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public StudyPlan(int id, int userId, int subjectId,
                     String title, String description,
                     Date startDate, Date endDate,
                     String status, Timestamp createdAt) {

        this.id = id;
        this.userId = userId;
        this.subjectId = subjectId;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

