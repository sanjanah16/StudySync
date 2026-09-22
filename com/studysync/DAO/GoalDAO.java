
package com.studysync.DAO;

import java.util.List;

import com.studysync.model.Goal;

public interface GoalDAO {

    void addGoal(Goal goal);

    Goal getGoal(int id);

    void updateGoal(Goal goal);

    void deleteGoal(int id);

    List<Goal> getAllGoals();

    List<Goal> getGoalsByUserId(int userId);
}

