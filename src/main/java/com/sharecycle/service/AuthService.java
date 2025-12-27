package com.sharecycle.service;

import com.sharecycle.dto.RegisterRequest;
import com.sharecycle.dto.LoginRequest;
import com.sharecycle.entity.User;

public interface AuthService {
    User register(RegisterRequest request);
    User login(LoginRequest request);
}