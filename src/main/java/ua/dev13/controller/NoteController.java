package ua.dev13.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.dev13.entity.Note;
import ua.dev13.service.NoteService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;
    private static final String BASE_REDIRECT = "redirect:/note/list";

    @GetMapping("/list")
    public ModelAndView getAllNotes(){
        ModelAndView result = new ModelAndView("list_notes");
        result.addObject("notes", noteService.listAll());
        return result;
    }

    @PostMapping("/delete")
    public String deleteNoteById(@RequestParam("id")Long id){
        noteService.deleteById(id);
        return BASE_REDIRECT;
    }

    @GetMapping("/edit")
    public ModelAndView editNoteById(@RequestParam("id")Long id){
        ModelAndView result = new ModelAndView("edit_note");
        Note note = noteService.getById(id);
        result.addObject("note", note);
        return result;
    }

    @PostMapping("/edit")
    public String saveEditingNoteById(Note note) {
        Note existingNote = noteService.getById(note.getId());
        if (existingNote != null) {
            existingNote.setTitle(note.getTitle());
            existingNote.setContent(note.getContent());
            noteService.update(existingNote);
        }
        return BASE_REDIRECT;
    }

    @GetMapping("/create")
    public ModelAndView createNote(){
        return new ModelAndView("create_note");
    }


    @PostMapping("/create")
    public String saveCreatingNote(@RequestParam("title")String title,@RequestParam("content")String content){
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        noteService.add(note);
        return BASE_REDIRECT;
    }
}
