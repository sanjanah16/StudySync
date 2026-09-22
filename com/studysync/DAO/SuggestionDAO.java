
package com.studysync.DAO;

import java.util.List;

import com.studysync.model.Suggestion;
import com.studysync.model.SuggestionView;

public interface SuggestionDAO {

    void addSuggestion(Suggestion suggestion);

    Suggestion getSuggestion(int id);

    void updateSuggestion(Suggestion suggestion);

    void deleteSuggestion(int id);

    List<Suggestion> getAllSuggestions();

    List<Suggestion> getSuggestionsByStudentId(int studentId);

    List<Suggestion> getSuggestionsByMentorId(int mentorId);

    List<SuggestionView> getSuggestionViewsByStudentId(int studentId);
}

