package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class CredentialController {
    private final CredentialService credentialService;
    private final EncryptionService encryptionService;
    private final UserService userService;

    public CredentialController(CredentialService credentialService, EncryptionService encryptionService, UserService userService) {
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
        this.userService = userService;
    }

    @PostMapping("/credential")
    public String store(@ModelAttribute Credential form, Principal principal, RedirectAttributes redirectAttrs) {
        String error = null;
        User user = this.userService.getUser(principal.getName());
        if (user == null) {
            redirectAttrs.addFlashAttribute("error", "User not found. Please log in again.");
            return "redirect:/home";
        }

        String key = encryptionService.getEncryptionKey();
        Credential credential = new Credential(null, form.getUrl(), form.getUsername(), key,
                encryptionService.encryptValue(form.getPassword(), key), user.getUserId());

        int rowsAdded = credentialService.createCredential(credential);
        if (rowsAdded > 0) {
            redirectAttrs.addFlashAttribute("success", "Credential for " + form.getUrl() + " successfully created.");
        } else {
            redirectAttrs.addFlashAttribute("error", "Error creating credential. Please try again.");
        }
        return "redirect:/home";
    }

    @GetMapping("/credential/delete/{id}")
    public String deleteCredential(@PathVariable String id, RedirectAttributes redirectAttrs) {
        int credentialId = Integer.parseInt(id);
        if (credentialService.getCredentialById(credentialId) != null){
            int rowsDeleted = credentialService.deleteRecordById(credentialId);
            if (rowsDeleted > 0) {
                redirectAttrs.addFlashAttribute("success", "Credential successfully deleted.");
            } else {
                redirectAttrs.addFlashAttribute("error", "Error deleting credential. Please try again.");
            }

            }else{
            redirectAttrs.addFlashAttribute("error", "Credential not found.");
        }

        return "redirect:/home";
    }

    @GetMapping("/credential/edit/{id}")
    public String showUpdateForm(@PathVariable String id, Model model) {
        Credential credential = credentialService.getCredentialById(Integer.parseInt(id));
        if (credential != null) {
            credential.setPassword(encryptionService.decryptValue(credential.getPassword(), credential.getKey()));
            model.addAttribute("credentialEdit", credential);
            return "update-credential";
        }
        return "redirect:/home";
    }

    @PostMapping("/credential/update/{id}")
    public String update(@PathVariable String id, @Valid Credential credential, BindingResult result, RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            return "update-credential";
        }
        credential.setKey(encryptionService.getEncryptionKey());
        int updateRow = credentialService.updateCredential(credential, Integer.parseInt(id));
        if (updateRow > 0) {
            redirectAttrs.addFlashAttribute("success", "Credential successfully updated.");
        } else {
            redirectAttrs.addFlashAttribute("error", "Error updating credential. Please try again.");
        }
        return "redirect:/home";
    }
}
