package controller;

import model.Note;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
@Controller
public class NoteController {
    @GetMapping("/note")
    public String getNotePage(Model model){
        Note[] notes = new Note[0];
        model.addAttribute("notes", notes);
        return "notes";
    }
}
