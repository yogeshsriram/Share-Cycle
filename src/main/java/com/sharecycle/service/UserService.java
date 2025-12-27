package com.sharecycle.service;

import com.sharecycle.entity.User;

public interface UserService {
    User createUser(User user);
    User authenticate(String username, String password);
    User findByEmail(String email);
}