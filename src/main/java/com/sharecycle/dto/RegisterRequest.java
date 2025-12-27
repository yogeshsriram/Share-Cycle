package com.sharecycle.dto;

import com.sharecycle.entity.UserRole;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private UserRole role;
    private String location;
    private String phoneNumber;
}