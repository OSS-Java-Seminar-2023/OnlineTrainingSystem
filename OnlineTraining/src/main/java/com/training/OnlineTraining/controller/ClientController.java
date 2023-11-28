package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.dto.ClientDto;
import com.training.OnlineTraining.dto.CoachDto;
import com.training.OnlineTraining.service.ClientService;
import com.training.OnlineTraining.service.CoachService;
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
    public String getClientPage(Model model) {
        List<CoachDto> coaches = coachService.getAllCoaches();
        model.addAttribute("coaches", coaches);
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
