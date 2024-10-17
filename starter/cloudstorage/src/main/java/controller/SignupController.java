package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class SignupController {
    @GetMapping("/signup")
    public String getSignupPage(Model model){
        //model.addAttribute("welcomeMessage", "Login");
        return "signup";
    }
}
