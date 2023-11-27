package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.dto.CoachDto;
import com.training.OnlineTraining.service.CoachService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/coaches")
public class CoachController {

    private final CoachService coachService;

    @GetMapping("/register")
    public String getBecomeCoachPage(@RequestParam UUID userId, Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("coach", new CoachDto());
        return "coach_register_page";
    }

    @PostMapping("/register")
    public String becomeCoach(@ModelAttribute CoachDto coachDto, @RequestParam UUID userId, Model model) {
        try {
            coachService.registerCoach(coachDto, userId);
            return "user_page";
        } catch (RuntimeException e) {
            return "error_page";
        }
    }
}
