package ua.dev13.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.dev13.entity.Note;
import ua.dev13.service.NoteService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/note")
public class NoteController {
    private static final String BASE_REDIRECT = "redirect:/note/list";
    private final NoteService noteService;

    @GetMapping("/list")
    public ModelAndView getAllNotes() {
        ModelAndView result = new ModelAndView("list_notes");
        result.addObject("notes", noteService.listAll());
        return result;
    }

    @PostMapping("/delete")
    public String deleteNoteById(@RequestParam("id") Long id) {
        noteService.deleteById(id);
        return BASE_REDIRECT;
    }

    @GetMapping("/edit")
    public ModelAndView editNoteById(@RequestParam("id") Long id) {
        ModelAndView result = new ModelAndView("edit_note");
        Note note = noteService.getById(id);
        result.addObject("note", note);
        return result;
    }

    @PostMapping("/edit")
    public String saveEditingNoteById(@ModelAttribute("note") Note note) {
        noteService.update(note);
        return BASE_REDIRECT;
    }

    @GetMapping("/create")
    public ModelAndView createNote() {
        return new ModelAndView("create_note", "note", new Note());
    }

    @PostMapping("/create")
    public String saveCreatingNote(@ModelAttribute("note") Note note) {
        noteService.add(note);
        return BASE_REDIRECT;
    }
}
