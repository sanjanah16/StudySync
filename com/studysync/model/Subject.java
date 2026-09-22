
package com.studysync.model;

import java.sql.Timestamp;

public class Subject {

    private int id;

    private String name;

    private String description;

    private Timestamp createdAt;


    // Default constructor
    public Subject() {

    }


    // Constructor for adding a new subject
    public Subject(String name, String description) {

        this.name = name;

        this.description = description;
    }


    // Constructor for editing a subject
    public Subject(int id, String name, String description) {

        this.id = id;

        this.name = name;

        this.description = description;
    }


    // Constructor for getting complete subject from database
    public Subject(int id,
                   String name,
                   String description,
                   Timestamp createdAt) {

        this.id = id;

        this.name = name;

        this.description = description;

        this.createdAt = createdAt;
    }


    // Getter for ID
    public int getId() {

        return id;
    }


    // Setter for ID
    public void setId(int id) {

        this.id = id;
    }


    // Getter for name
    public String getName() {

        return name;
    }


    // Setter for name
    public void setName(String name) {

        this.name = name;
    }


    // Getter for description
    public String getDescription() {

        return description;
    }


    // Setter for description
    public void setDescription(String description) {

        this.description = description;
    }


    // Getter for createdAt
    public Timestamp getCreatedAt() {

        return createdAt;
    }


    // Setter for createdAt
    public void setCreatedAt(Timestamp createdAt) {

        this.createdAt = createdAt;
    }
}

