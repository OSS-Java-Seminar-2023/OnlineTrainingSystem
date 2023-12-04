package com.training.OnlineTraining.controller;


import com.training.OnlineTraining.service.ContractService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;


@Controller
@AllArgsConstructor
@RequestMapping("/contracts")
public class ContractController {


    private ContractService contractService;

    @GetMapping("/{coachId}")
    public String viewContract(@PathVariable UUID coachId, Model model, HttpSession session) {

        UUID clientId = (UUID) session.getAttribute("clientId");

        model.addAttribute("coachId", coachId);
        model.addAttribute("clientId", clientId);

        return "contract_page";
    }

}
