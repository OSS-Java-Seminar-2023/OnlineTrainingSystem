package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.dto.ClientDto;
import com.training.OnlineTraining.dto.CoachDto;
import com.training.OnlineTraining.service.ClientService;
import com.training.OnlineTraining.service.CoachService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;
    private final CoachService coachService;

    @GetMapping("/client-page")
    public String getClientPage(Model model,
                                @RequestParam(name = "gender", required = false) String gender,
                                @RequestParam(name = "experience", required = false) Double experience,
                                @RequestParam(name = "age", required = false) Integer age,
                                @RequestParam(name = "education", required = false) String education,
                                @RequestParam(name = "monthlyPrice", required = false) Double monthlyPrice,
                                HttpSession session) {
        List<CoachDto> coaches;
        UUID clientId = (UUID) session.getAttribute("clientId");
        String clientName = (String) session.getAttribute("clientName");
        
        if (gender != null || experience != null || age != null || education != null || monthlyPrice != null) {
            coaches = coachService.filterCoaches(gender, experience, age, education, monthlyPrice);
        } else {
            coaches = coachService.getAllCoaches();
        }
        model.addAttribute("coaches", coaches);
        model.addAttribute("clientId", clientId);
        model.addAttribute("clientName", clientName);

        return "client_page";
    }

    @GetMapping("/register")
    public String getBecomeClientPage(@RequestParam UUID userId, Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("client", new ClientDto());
        return "client_register_page";
    }

    @PostMapping("/register")
    public String becomeClient(@ModelAttribute ClientDto clientDto, @RequestParam UUID userId, Model model) {
        try {
            clientService.registerClient(clientDto, userId);
            return "redirect:/clients/client-page";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "error_page";
        }
    }
}
