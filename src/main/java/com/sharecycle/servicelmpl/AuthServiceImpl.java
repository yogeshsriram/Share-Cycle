package com.sharecycle.serviceImpl;

import com.sharecycle.dto.RegisterRequest;
import com.sharecycle.dto.LoginRequest;
import com.sharecycle.entity.User;
import com.sharecycle.repository.UserRepository;
import com.sharecycle.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {   // ✅ use getter
            throw new IllegalArgumentException("Email already registered");
        }
        if (userRepository.existsByUsername(request.getUsername())) {   // ✅ use getter
            throw new IllegalArgumentException("Username already taken");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .location(request.getLocation())       // ✅ now valid
                .phoneNumber(request.getPhoneNumber()) // ✅ now valid
                .build();

        return userRepository.save(user);
    }

    @Override
    public User login(LoginRequest request) {
        // Safely unwrap Optional<User>
        User user = userRepository.findByUsername(request.getUsername())   // ✅ use getter
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        // Verify password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {   // ✅ use getter
            throw new IllegalArgumentException("Invalid credentials");
        }

        return user;
    }
}

