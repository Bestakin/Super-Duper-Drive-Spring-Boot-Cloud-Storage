package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/files")
public class FileController {
        private final FileService fileService;
        private final UserService userService;

        public FileController(FileService fileService, UserService userService) {
            this.fileService = fileService;
            this.userService = userService;
        }

        @PostMapping("/upload")
        public String uploadFile(@RequestParam("fileUpload") MultipartFile file, Principal principal, RedirectAttributes redirectAttrs) {
            User user = userService.getUser(principal.getName());

            if (file.isEmpty()) {
                redirectAttrs.addFlashAttribute("error", "Please select a file to upload.");
                return "redirect:/home";
            }

            if (!fileService.isFileByUserAndFileNameAvailabe(user.getUserId(), file.getOriginalFilename())) {
                redirectAttrs.addFlashAttribute("error", "A file with the same name already exists.");
                return "redirect:/home";
            }

            try {
                Files newFile = new Files(null, file.getOriginalFilename(), file.getContentType(),
                        String.valueOf(file.getSize()), file.getBytes(), user.getUserId());
                int rowsAdded = fileService.createFile(newFile);

                if (rowsAdded > 0) {
                    redirectAttrs.addFlashAttribute("success", "File uploaded successfully.");
                } else {
                    redirectAttrs.addFlashAttribute("error", "Error uploading file. Please try again.");
                }
            } catch (IOException e) {
                redirectAttrs.addFlashAttribute("error", "File processing error. Please try again.");
            }

            return "redirect:/home";
        }

        @GetMapping("/download/{id}")
        public ResponseEntity<?> downloadFile(@PathVariable String id, Principal principal) {
            User user = userService.getUser(principal.getName());
            Files file = fileService.getFile(Integer.parseInt(id));
            if (file == null) {
                return ResponseEntity.badRequest().body("Unauthorized access or file does not exist.");
            }

            if (!file.getUserId().equals(user.getUserId())) {
                return ResponseEntity.badRequest().body("Unauthorized access.");
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                    .body(file.getFileData());
        }

        @GetMapping("/delete/{id}")
        public String deleteFile(@PathVariable String id, RedirectAttributes redirectAttrs) {
            int rowsDeleted = fileService.deleteRecordById(Integer.parseInt(id));
            if (rowsDeleted > 0) {
                redirectAttrs.addFlashAttribute("success", "File deleted successfully.");
            } else {
                redirectAttrs.addFlashAttribute("error", "Error deleting file. Please try again.");
            }
            return "redirect:/home";
        }
}

