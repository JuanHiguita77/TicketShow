package com.riwi.ticketShowWeb.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.ticketShowWeb.api.dto.request.LoginRequest;
import com.riwi.ticketShowWeb.api.dto.request.RegisterRequest;
import com.riwi.ticketShowWeb.api.dto.response.AuthResp;
import com.riwi.ticketShowWeb.infraestructure.abstract_services.IAuthService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping
@AllArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user authentication and registration")
public class AuthController {

    // Inyeccion de dependencia
    @Autowired
    private final IAuthService authService;
    
    // Login
    @ApiResponse(
        responseCode = "400",
        description = "When the request sent to the server is not formatted correctly, e.g. missing fields",
        content = {
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)
            )
        }
    )
    @PostMapping(path = "/auth/login")
    public ResponseEntity<AuthResp> login(
        @Validated @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(this.authService.login(request));
    }

    // Register
    @ApiResponse(
        responseCode = "400",
        description = "It is returned when the request sent to the server is not formatted correctly, for example, if mandatory fields are missing or the data provided does not meet the validation criteria set.",
        content = {
            @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)
            )
        }
    )
    @PostMapping(path = "/auth/register")
    public ResponseEntity<AuthResp> register(
        @Validated @RequestBody RegisterRequest request
    ) {

        return ResponseEntity.ok(this.authService.register(request));
    }
    
}
