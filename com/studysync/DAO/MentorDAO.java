
package com.studysync.DAO;

import java.util.List;

import com.studysync.model.Mentor;

public interface MentorDAO {

    void addMentor(Mentor mentor);

    Mentor getMentor(int id);

    Mentor getMentorByUserId(int userId);

    void updateMentor(Mentor mentor);

    void deleteMentor(int id);

    List<Mentor> getAllMentors();
}

