package com.flavorfleet.user_service.controller;

import com.flavorfleet.user_service.dto.RegisterRequest;
import com.flavorfleet.user_service.dto.LoginRequest;
import com.flavorfleet.user_service.dto.LoginResponse;
import com.flavorfleet.user_service.model.User;
import com.flavorfleet.user_service.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        logger.info("Received register request for username: {}", request.getUsername());
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        logger.info("Received login request for username: {}", request.getUsername());
        return authService.login(request);
    }

    @GetMapping("/test")
    public String test() {
        logger.info("Received test request");
        return "User Service is up!";
    }
}