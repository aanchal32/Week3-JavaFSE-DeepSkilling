package com.example.auth.service;

import com.example.auth.exception.InvalidCredentialsException;
import com.example.auth.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class AuthenticationService {

    private static final String VALID_USERNAME = "user";
    private static final String VALID_PASSWORD = "pwd";

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final String encodedValidPassword;

    public AuthenticationService(JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.encodedValidPassword = passwordEncoder.encode(VALID_PASSWORD);
    }

    public String authenticate(String authorizationHeader) {
        String[] credentials = decodeAuthorizationHeader(authorizationHeader);
        String username = credentials[0];
        String password = credentials[1];

        if (!VALID_USERNAME.equals(username) || !passwordEncoder.matches(password, encodedValidPassword)) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        return generateJwt(username);
    }

    public String[] decodeAuthorizationHeader(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Basic ")) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        String base64Credentials = authorizationHeader.substring("Basic ".length()).trim();
        String decoded;
        try {
            decoded = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
        } catch (IllegalArgumentException ex) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        String[] parts = decoded.split(":", 2);
        if (parts.length != 2) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        return parts;
    }

    public String generateJwt(String username) {
        return jwtUtil.generateToken(username);
    }
}
