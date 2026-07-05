package com.example.auth.controller;

import com.example.auth.model.TokenResponse;
import com.example.auth.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/authenticate")
    public ResponseEntity<TokenResponse> authenticate(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authenticationService.authenticate(authorizationHeader);
        return ResponseEntity.ok(new TokenResponse(token));
    }
}
