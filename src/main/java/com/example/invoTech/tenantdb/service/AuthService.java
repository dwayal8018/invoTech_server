package com.example.invoTech.tenantdb.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.invoTech.config.JwtUtil;
import com.example.invoTech.tenant.TenantContext;
import com.example.invoTech.tenantdb.entity.User;
import com.example.invoTech.tenantdb.repository.UserRepository;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Map<String, String> authenticate(String username, String password) {
        Optional<User> user = findByUsername(username);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            String tenantId = (String) TenantContext.getCurrentTenant();
            String token = jwtUtil.generateToken(username, tenantId);
            return Map.of("token", token, "message", "Login successful");
        }
        return null;
    }
}
