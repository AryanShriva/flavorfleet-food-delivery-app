package com.flavorfleet.user_service.controller;

import com.flavorfleet.user_service.dto.LoginRequest;
import com.flavorfleet.user_service.dto.LoginResponse;
import com.flavorfleet.user_service.dto.RegisterRequest;
import com.flavorfleet.user_service.model.User;
import com.flavorfleet.user_service.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AuthService authService;

    @GetMapping("/test")
    public String test() {
        logger.info("Received test request");
        return "User Service is up!";
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        logger.info("Received register request for username: {}", request.getUsername());
        User user = authService.register(request);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        logger.info("Received login request for username: {}", request.getUsername());
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<User> getUserProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("Received profile request for username: {}", username);
        User user = authService.getUserByUsername(username);
        if (user == null) {
            logger.error("User not found: {}", username);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}