package com.training.OnlineTraining.controller;


import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@AllArgsConstructor
@RequestMapping("/admins")
public class AdminController {

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String getAdminPage() {
        return "admin/admin_page";
    }

}
