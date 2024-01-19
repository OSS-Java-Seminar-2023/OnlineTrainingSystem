package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.dto.UserDto;
import com.training.OnlineTraining.model.*;
import com.training.OnlineTraining.service.ClientService;
import com.training.OnlineTraining.service.CoachService;
import com.training.OnlineTraining.service.MailService;
import com.training.OnlineTraining.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.concurrent.CompletableFuture;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final ClientService clientService;
    private final CoachService coachService;
    private final MailService mailService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @GetMapping("/register")
    public String getRegisterPage(Model model) {

        model.addAttribute("registerRequest", new UserDto());
        return "registration/register_page";
    }


    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new UserDto());
        return "auth/login_page";
    }


    @PostMapping("/register")
    public String register(@ModelAttribute UserDto request, @RequestParam String confirmPassword, Model model) {
        try {
            if (!request.getPassword().equals(confirmPassword)) {
                throw new RuntimeException("Passwords do not match");
            }

            User registeredUser = userService.registerUser(request);
            mailService.sendEmailAsync(
                    registeredUser.getEmail(),
                    "Welcome to OnlineTrainingSystem!",
                    " Registration Confirmation"
            );

            model.addAttribute("userId", registeredUser.getId());
            return "/auth/become_client_or_coach_page";

        } catch (RuntimeException | MessagingException e){
            model.addAttribute("error", e.getMessage());
            model.addAttribute("registerRequest", request);
            return "registration/register_page";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }


}

