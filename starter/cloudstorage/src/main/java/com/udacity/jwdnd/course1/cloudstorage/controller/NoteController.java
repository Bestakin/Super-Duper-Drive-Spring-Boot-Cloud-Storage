package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;
    private final UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping
    public String store(@ModelAttribute Note formNote, Principal principal, RedirectAttributes redirectAttrs) {
        User user = userService.getUser(principal.getName());
        formNote.setUserId(user.getUserId());

        int rowsAdded = noteService.createNote(formNote);
        if (rowsAdded > 0) {
            redirectAttrs.addFlashAttribute("success", "Note successfully created.");
        } else {
            redirectAttrs.addFlashAttribute("error", "Error creating note. Please try again.");
        }
        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes redirectAttrs) {
        int rowsDeleted = noteService.deleteRecordById(Integer.parseInt(id));
        if (rowsDeleted > 0) {
            redirectAttrs.addFlashAttribute("success", "Note successfully deleted.");
        } else {
            redirectAttrs.addFlashAttribute("error", "Error deleting note. Please try again.");
        }
        return "redirect:/home";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("noteEdit", noteService.getNote(Integer.parseInt(id)));
        return "update-note";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable String id, @Valid Note note, BindingResult result, RedirectAttributes redirectAttrs) {
        if (result.hasErrors()){
            return "update-note";
        }

        int rowsUpdated = noteService.updateNote(
                note.getNoteTitle(),
                note.getNoteDescription(),
                Integer.parseInt(id)
        );
        if (rowsUpdated > 0) {
            redirectAttrs.addFlashAttribute("success", "Note successfully updated.");
        } else {
            redirectAttrs.addFlashAttribute("error", "Error updating note. Please try again.");
        }
        return "redirect:/home";
    }
}
