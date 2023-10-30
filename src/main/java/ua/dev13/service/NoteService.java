package ua.dev13.service;

import org.springframework.stereotype.Service;
import ua.dev13.entity.Note;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {
    private final List<Note> noteList = new ArrayList<>();

    public List<Note> listAll() {
        return noteList;
    }

    public Note add(Note note) {
        Long id = generatorId();
        note.setId(id);
        noteList.add(note);
        return note;
    }

    public Note getById(long id) {
        for (Note note : noteList) {
            if (note.getId() == id) {
                return note;
            }
        }
       throw new IllegalArgumentException();
    }

    public void deleteById(long id) {
        Note note = getById(id);
        if (note == null) {
            throw new IllegalArgumentException();
        } else {
            noteList.remove(note);
        }
    }

    public void update(Note note){
        Note existingNote = getById(note.getId());
        if(existingNote == null){
            throw new IllegalArgumentException();
        } else {
            existingNote.setTitle(note.getTitle());
            existingNote.setContent(note.getContent());
        }
    }

    private long generatorId() {
        if (noteList.isEmpty()) {
            return 0L;
        } else {
            return noteList.size();
        }
    }
}
