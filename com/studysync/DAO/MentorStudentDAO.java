package com.studysync.DAO;

import java.util.List;

import com.studysync.model.User;

public interface MentorStudentDAO {

    void assignStudent(int mentorId, int studentId);

    void removeStudent(int mentorId, int studentId);

    List<User> getStudentsByMentorId(int mentorId);

    List<User> getAllStudents();

    boolean isStudentAssigned(int mentorId, int studentId);
}