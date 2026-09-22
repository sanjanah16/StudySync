
package com.studysync.DAO;

import java.util.List;

import com.studysync.model.StudyPlan;

public interface StudyPlanDAO {

    void addStudyPlan(StudyPlan studyPlan);

    StudyPlan getStudyPlan(int id);

    void updateStudyPlan(StudyPlan studyPlan);

    void deleteStudyPlan(int id);

    List<StudyPlan> getAllStudyPlans();

    List<StudyPlan> getStudyPlansByUserId(int userId);
}

