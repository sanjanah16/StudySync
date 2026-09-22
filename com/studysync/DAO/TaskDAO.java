package com.studysync.DAO;

import java.util.List;

import com.studysync.model.Task;

public interface TaskDAO {

    void addTask(Task task);

    Task getTask(int id);

    void updateTask(Task task);

    void deleteTask(int id);

    List<Task> getAllTasks();

    List<Task> getTasksByUserId(int userId);
}