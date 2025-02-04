package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping
public class HomeController {
    private final FileService fileService;
    private final CredentialService credentialService;
    private final NoteService noteService;
    private final UserService userService;

    public HomeController(FileService fileService, CredentialService credentialService, NoteService noteService, UserService userService) {
        this.fileService = fileService;
        this.credentialService = credentialService;
        this.noteService = noteService;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String index(Model model, Principal principal) {
        // Verify if the principal in valid
        if (principal == null || principal.getName() == null){
            System.out.println("Principal is null - Redirecting to login");
            model.addAttribute("error", "You must be logged in to access this page.");
            return "redirect:/login";
        }
        // Fetch the user from the database
        System.out.println("Authenticated user: " + principal.getName());
        User user = userService.getUser(principal.getName());
        if (user == null) {
            model.addAttribute("error", "User not found. Please log in again.");
            return "redirect:/login";
        }
   //    List<Files> userFiles = fileService.getFilesByUser(user.getUserId());
     //   List<Note> userNotes = noteService.getNotesByUser(user.getUserId());
//        List<Credential> userCredentials = credentialService.getCredentialByUser(user.getUserId());

        // Add fetched data to the model
        model.addAttribute("userFiles",fileService.getFilesByUser(user.getUserId()));
        model.addAttribute("userNotes", noteService.getNotesByUser(user.getUserId()));
        model.addAttribute("userCredentials", credentialService.getCredentialByUser(user.getUserId()));
        return "home";
    }

}

