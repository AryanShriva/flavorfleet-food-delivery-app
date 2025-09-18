package com.flavorfleet.user_service.controller;

import com.flavorfleet.user_service.dto.RegisterRequest;
import com.flavorfleet.user_service.dto.LoginRequest;
import com.flavorfleet.user_service.dto.LoginResponse;
import com.flavorfleet.user_service.model.User;
import com.flavorfleet.user_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/test")
    public String test() {
        return "User Service is up!";
    }
}