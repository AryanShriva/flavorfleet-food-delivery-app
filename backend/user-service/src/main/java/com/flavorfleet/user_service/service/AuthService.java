package com.flavorfleet.userservice.service;

import com.flavorfleet.userservice.dto.RegisterRequest;
import com.flavorfleet.userservice.dto.LoginRequest;
import com.flavorfleet.userservice.dto.LoginResponse;
import com.flavorfleet.userservice.model.User;
import com.flavorfleet.userservice.repository.UserRepository;
import com.flavorfleet.userservice.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public User register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()) != null ||
                userRepository.findByEmail(request.getEmail()) != null) {
            throw new RuntimeException("Username or email already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        return userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername());
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(user.getUsername());
        return new LoginResponse(token);
    }
}