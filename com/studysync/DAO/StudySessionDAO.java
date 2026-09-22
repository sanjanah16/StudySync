
package com.studysync.DAO;

import java.util.List;

import com.studysync.model.StudySession;

public interface StudySessionDAO {

    void addStudySession(StudySession session);

    StudySession getStudySession(int id);

    void updateStudySession(StudySession session);

    void deleteStudySession(int id);

    List<StudySession> getAllStudySessions();

    List<StudySession> getStudySessionsByUserId(int userId);
}

