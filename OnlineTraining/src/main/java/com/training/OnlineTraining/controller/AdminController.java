package com.training.OnlineTraining.controller;


import com.training.OnlineTraining.dto.UserDto;
import com.training.OnlineTraining.model.enums.Role;
import com.training.OnlineTraining.service.AdminService;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Controller
@AllArgsConstructor
@RequestMapping("/admins")
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAdminPage() {
        return "admin/admin_page";
    }


    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAllUsers(@RequestParam(name = "roleFilter", required = false) String roleFilter, Model model) {

        List<UserDto> users = StringUtils.isEmpty(roleFilter) ? adminService.getAllUsers() :
                adminService.filterUsersByRole(String.valueOf(Role.valueOf(roleFilter)));

        model.addAttribute("users", users);
        model.addAttribute("availableRoles", Role.values());
        return "admin/users";
    }

    @DeleteMapping("/users/delete/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteUser(@PathVariable UUID userId) {
        adminService.deleteUser(userId);
        return "/admin/admin_page";
    }


}
