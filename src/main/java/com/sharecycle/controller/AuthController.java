package com.sharecycle.controller;

import com.sharecycle.dto.AuthResponse;
import com.sharecycle.dto.LoginRequest;
import com.sharecycle.dto.RegisterRequest;
import com.sharecycle.entity.User;
import com.sharecycle.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        User user = authService.register(request);
        return ResponseEntity.ok(new AuthResponse(
                "Registered successfully",
                null, // token will be added after JWT
                user.getId(),
                user.getRole().name()
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        User user = authService.login(request);
        return ResponseEntity.ok(new AuthResponse(
                "Login successful",
                null, // token after JWT
                user.getId(),
                user.getRole().name()
        ));
    }
}