package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.model.Coach;
import com.training.OnlineTraining.model.User;
import com.training.OnlineTraining.model.enums.Education;
import com.training.OnlineTraining.service.CoachService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/coaches")
public class CoachController {

    private final CoachService coachService;

    @GetMapping("/register")
    public String getBecomeCoachPage(@RequestParam UUID userId, Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("coach", new Coach());
        return "coach_register_page";
    }

    @PostMapping("/register")
    public String becomeCoach(@ModelAttribute Coach coach, @RequestParam UUID userId, Model model) {
        try {
            coach.setUser(new User(userId));
            coachService.registerCoach(coach);
            return "user_page";
        } catch (RuntimeException e) {
            return "error_page";
        }
    }

}
