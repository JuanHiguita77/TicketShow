package com.riwi.ticketShowWeb.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.ticketShowWeb.api.dto.request.LoginRequest;
import com.riwi.ticketShowWeb.api.dto.request.RegisterRequest;
import com.riwi.ticketShowWeb.api.dto.response.AuthResponse;
import com.riwi.ticketShowWeb.infraestructure.abstract_services.IAuthService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping
@AllArgsConstructor
public class AuthController {

    @Autowired
    private final IAuthService authService;
    
    // Login
    @PostMapping(path = "/auth/login")
    public ResponseEntity<AuthResponse> login(
        @Validated @RequestBody LoginRequest request
    ) {

        return ResponseEntity.ok(this.authService.login(request));
    }

    // Register
    @PostMapping(path = "/auth/register")
    public ResponseEntity<AuthResponse> register(
        @Validated @RequestBody RegisterRequest request
    ) {

        return ResponseEntity.ok(this.authService.register(request));
    }
    
}
