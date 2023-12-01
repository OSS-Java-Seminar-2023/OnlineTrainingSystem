package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.dto.UserDto;
import com.training.OnlineTraining.model.*;
import com.training.OnlineTraining.service.ClientService;
import com.training.OnlineTraining.service.CoachService;
import com.training.OnlineTraining.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final ClientService clientService;
    private final CoachService coachService;

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
    public String register(@ModelAttribute UserDto request, @RequestParam String confirmPassword, Model model) {
        try {
            if (!request.getPassword().equals(confirmPassword)) {
                throw new RuntimeException("Passwords do not match");
            }

            User registeredUser = userService.registerUser(request);
            return registeredUser == null ? "error_page" : "redirect:/login";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("registerRequest", request);
            return "register_page";
        }
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model, HttpSession session) {
        try {
            User authenticated = userService.authenticate(user.getEmail(), user.getPassword());
            boolean isClient = clientService.isClient(authenticated);
            boolean isCoach = coachService.isCoach(authenticated);

            if (isClient) {
                Client client = clientService.getClientByUserId(authenticated.getId());
                session.setAttribute("clientId", client.getId());
                session.setAttribute("clientName", authenticated.getFirstName());
                return "redirect:clients/client-page";
            } else if (isCoach) {
                session.setAttribute("coachId", authenticated.getId());
                return "redirect:/user-page";
            } else {
                model.addAttribute("userId", authenticated.getId());
                return "become_client_or_coach_page";
            }
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "login_page";
        }
    }
}

