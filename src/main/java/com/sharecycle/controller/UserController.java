package com.sharecycle.controller;

import com.sharecycle.dto.RegisterRequest;
import com.sharecycle.entity.User;
import com.sharecycle.entity.UserRole;
import com.sharecycle.service.AuthService;
import com.sharecycle.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.sharecycle.dto.LoginRequest;
import com.sharecycle.dto.RegisterRequest;

@Controller
@RequestMapping("/users")
public class UserController {

    private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    // Show register form
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register"; // must match register.html
    }

    // Handle register
    @PostMapping("/register")
    public String handleRegister(@ModelAttribute RegisterRequest request) {
        authService.register(request);
        return "redirect:/users/login"; // after register, go to login
    }

    // Show login form
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login"; // must match login.html
    }

    // Handle login
    @PostMapping("/login")
    public String handleLogin(@ModelAttribute LoginRequest request) {
        User user = authService.login(request);

        if (user.getRole() == UserRole.DONOR) {
            return "redirect:/donations/dashboard/" + user.getId();
        } else {
            return "redirect:/receiver/dashboard/" + user.getId();
        }
    }
}