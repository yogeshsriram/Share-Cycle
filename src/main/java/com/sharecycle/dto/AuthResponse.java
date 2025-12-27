package com.sharecycle.dto;

public class AuthResponse {
    public String message;
    public String token; // will be filled once we add JWT
    public Long userId;
    public String role;

    public AuthResponse(String message, String token, Long userId, String role) {
        this.message = message;
        this.token = token;
        this.userId = userId;
        this.role = role;
    }
}