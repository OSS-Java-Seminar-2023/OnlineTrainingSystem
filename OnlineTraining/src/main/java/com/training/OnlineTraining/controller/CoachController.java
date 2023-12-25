package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.dto.CoachDto;
import com.training.OnlineTraining.model.Contract;
import com.training.OnlineTraining.service.CoachService;
import com.training.OnlineTraining.service.ContractService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/coaches")
public class CoachController {

    private final CoachService coachService;

    private final ContractService contractService;

    @GetMapping("/coach-page")
    public String getCoachPage(Model model,  HttpSession session) {
        UUID coachID = (UUID) session.getAttribute("coachId");

        System.out.println("Coach id : " + coachID);

        List<Contract> contractList = contractService.getAllContractsForCoach(coachID);
        model.addAttribute( "coachesListContracts", contractList);
        return "coach/coach_page";
    }

    @GetMapping("/register")
    public String getBecomeCoachPage(@RequestParam UUID userId, Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("coach", new CoachDto());
        return "coach/coach_register_page";
    }

    @PostMapping("/register")
    public String becomeCoach(@ModelAttribute CoachDto coachDto, @RequestParam UUID userId, Model model) {
        try {
            coachService.registerCoach(coachDto, userId);
            return "redirect:/login";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "error_page";
        }
    }
}
