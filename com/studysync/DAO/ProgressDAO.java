
package com.studysync.DAO;

import java.util.List;

import com.studysync.model.Progress;

public interface ProgressDAO {

    void addProgress(Progress progress);

    Progress getProgress(int id);

    void updateProgress(Progress progress);

    void deleteProgress(int id);

    List<Progress> getAllProgress();

    List<Progress> getProgressByUserId(int userId);

    int getTotalTasks(int userId, int subjectId);

    int getCompletedTasks(int userId, int subjectId);

}
