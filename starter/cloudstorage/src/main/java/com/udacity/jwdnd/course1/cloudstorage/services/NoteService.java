package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class NoteService {
    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    // Create a new note
    public int createNote(Note note) {
        return noteMapper.insert(note);
    }

    public Note getNote(int noteId){
        return noteMapper.getNote(noteId);
    }

    // Fetch a single note by its ID
    public Note getNoteById(int noteId) {
        return noteMapper.getNote(noteId);
    }

    // Fetch all notes for a specific user
    public List<Note> getNotesByUser(int userId) {
        List<Note> notes = noteMapper.getNotesByUserId(userId);
        return notes != null ? notes : Collections.emptyList();
    }

    // Delete a note by its ID
    public int deleteRecordById(int noteId) {
        return noteMapper.deleteNote(noteId);
    }

    // Fetch all notes (using getAllNotes from your mapper)
    public List<Note> getAllNotes() {
        List<Note> notes = noteMapper.getAllNotes(); // Since your mapper returns a single Note
        return notes != null ? notes : Collections.emptyList(); // Return an empty list if null
    }

    // Update an existing note
    public int updateNote(String noteTitle, String noteDescription, int noteId) {
        return noteMapper.updateNote(noteTitle, noteDescription, noteId);
    }
}
