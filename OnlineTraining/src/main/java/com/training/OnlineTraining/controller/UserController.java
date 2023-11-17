package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.model.*;
import com.training.OnlineTraining.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new User());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new User());
        return "login_page";
    }

    @GetMapping("/user-page")
    public String getUserPage() {
        return "user_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User request, @RequestParam String confirmPassword, Model model) {
        if (!request.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            model.addAttribute("registerRequest", request);
            return "register_page";
        }
        User registeredUser = userService.registerUser(request);
        System.out.println("REGISTERED USER " + registeredUser);
        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user) {
        System.out.println("login request " + user);
        User authenticated = userService.authenticate(user.getEmail(), user.getPassword());
        return authenticated == null ? "error_page" : "redirect:/user-page";
    }
}

