package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.dto.CoachDto;
import com.training.OnlineTraining.dto.UpdateCoachDTO;
import com.training.OnlineTraining.model.Coach;
import com.training.OnlineTraining.model.Contract;
import com.training.OnlineTraining.service.CoachService;
import com.training.OnlineTraining.service.ContractService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/coaches")
public class CoachController {

    private final CoachService coachService;

    private final ContractService contractService;
    private static final Logger logger = LoggerFactory.getLogger(CoachController.class);

    @GetMapping("/coach-page")
    public String getCoachPage(Model model, HttpSession session) {
        logger.info("Fetching coach page.");

        UUID coachID = (UUID) session.getAttribute("coachId");
        String coachName = (String) session.getAttribute("coachName");
        List<Contract> contractList = contractService.getAllContractsForCoach(coachID);

        model.addAttribute("coachesListContracts", contractList);
        model.addAttribute("coachName", coachName);

        return "coach/coach_page";
    }

    @GetMapping("/register")
    public String getBecomeCoachPage(@RequestParam UUID userId, Model model) {
        logger.info("Fetching become coach page.");

        model.addAttribute("userId", userId);
        model.addAttribute("coach", new CoachDto());

        return "coach/coach_register_page";
    }

    @PostMapping("/register")
    public String becomeCoach(@ModelAttribute CoachDto coachDto, @RequestParam UUID userId, Model model) {
        logger.info("Attempting to become a coach.");

        try {
            coachService.registerCoach(coachDto, userId);
            return "redirect:/login";

        } catch (RuntimeException e) {
            logger.error("Error during coach registration: {}", e.getMessage());

            model.addAttribute("error", e.getMessage());

            return "error_page";
        }
    }

    @GetMapping("/settings")
    public String getSettings(Model model, HttpSession session) {
        UUID coachId = (UUID) session.getAttribute("coachId");
        if (coachId == null) {
            return "auth/login_page";
        }
        Coach coach = coachService.getCoachById(coachId);
        model.addAttribute("coach", coach);
        model.addAttribute("genderOptions", Arrays.asList("Male", "Female"));

        return "coach/settings";
    }

    @PutMapping("/update")
    public String updateCoach(@ModelAttribute UpdateCoachDTO updateCoachDTO,
                              HttpSession session, Model model) {
        UUID coachId = (UUID) session.getAttribute("coachId");

        if (coachId == null) {
            return "auth/login_page";
        }
        try {
            coachService.updateCoach(coachId, updateCoachDTO);
            return "index";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "coach/settings";
        }
    }


}
