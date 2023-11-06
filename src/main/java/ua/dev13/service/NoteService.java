package ua.dev13.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.dev13.entity.Note;
import ua.dev13.repository.NoteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository repository;

    public List<Note> listAll() {
        return repository.findAll();
    }

    public Note add(Note note) {
        return repository.save(note);
    }

    public Note getById(long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public void update(Note note) {
        if (repository.existsById(note.getId())) {
            repository.save(note);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
