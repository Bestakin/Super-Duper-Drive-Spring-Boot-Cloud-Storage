package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;

@Controller
public class FileController {
    @GetMapping("/files")
    public String getFilesPage(Model model){
        File[] files = new File[0];
        model.addAttribute("files", files);
        return "files";
    }
}
