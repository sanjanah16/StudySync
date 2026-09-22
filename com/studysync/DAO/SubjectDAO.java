package com.studysync.DAO;

import java.util.List;

import com.studysync.model.Subject;

public interface SubjectDAO {

    void addSubject(Subject subject);

    Subject getSubject(int id);

    void updateSubject(Subject subject);

    void deleteSubject(int id);

    List<Subject> getAllSubjects();
}