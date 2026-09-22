
package com.studysync.DAO;

import java.util.List;

import com.studysync.model.Note;

public interface NoteDAO {

    void addNote(Note note);

    Note getNote(int id);

    void updateNote(Note note);

    void deleteNote(int id);

    List<Note> getAllNotes();

    List<Note> getNotesByUserId(int userId);
}
