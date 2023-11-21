package com.training.OnlineTraining.controller;

import com.training.OnlineTraining.model.Client;
import com.training.OnlineTraining.model.User;
import com.training.OnlineTraining.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@AllArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/register")
    public String getBecomeClientPage(@RequestParam UUID userId, Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("client", new Client());
        return "client_register_page";
    }

    @PostMapping("/register")
    public String becomeClient(@ModelAttribute Client client, @RequestParam UUID userId, Model model) {
        try {
            client.setUser(new User(userId));
            clientService.registerClient(client);
            return "redirect:/user-page";

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "error_page";
        }
    }
}
