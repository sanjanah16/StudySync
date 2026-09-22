package com.studysync.util;

import java.sql.Date;
import java.util.List;

import com.studysync.DAO.TaskDAO;
import com.studysync.DAOImpl.TaskDAOImpl;
import com.studysync.model.Task;

public class App {

    public static void main(String[] args) {

        TaskDAO taskDAO = new TaskDAOImpl();

        // Create a new task
        Task task = new Task(
                1,                         // user_id
                1,                         // subject_id
                "Complete Java OOP",       // title
                "Practice inheritance and polymorphism",
                Date.valueOf("2026-09-20"),
                "HIGH",
                "PENDING"
        );

        // Add task
        taskDAO.addTask(task);

        // Get tasks of user 1
        List<Task> tasks = taskDAO.getTasksByUserId(1);

        System.out.println();
        System.out.println("========== MY TASKS ==========");

        for (Task t : tasks) {

            System.out.println("Task ID: " + t.getId());
            System.out.println("Title: " + t.getTitle());
            System.out.println("Description: " + t.getDescription());
            System.out.println("Due Date: " + t.getDueDate());
            System.out.println("Priority: " + t.getPriority());
            System.out.println("Status: " + t.getStatus());

            System.out.println("------------------------------");
        }
    }
}