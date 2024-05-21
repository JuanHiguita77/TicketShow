package com.riwi.ticketShowWeb.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.ticketShowWeb.api.dto.response.PayloadResponse;
import com.riwi.ticketShowWeb.infraestructure.helpers.JwtService;
import com.riwi.ticketShowWeb.utils.exceptions.BadRequestException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping
@AllArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user authentication and registration")
public class PayloadController {

    @Autowired
    private final JwtService jwtService;
    
    @ApiResponse(responseCode = "400", description = "Token Invalid", content = 
    {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @Operation(summary = "Send Token to verify", description = "Send a token to verify")
    @PostMapping(path = "/auth/payload")
    public ResponseEntity<PayloadResponse> getPayload(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            String token = authorizationHeader.substring(7);

            PayloadResponse payloadResponse = PayloadResponse.builder()
            .email(jwtService.getEmailFromToken(token))
            .role(jwtService.getRoleFromToken(token))
            .iat(jwtService.getIssuedAtFromToken(token))
            .exp(jwtService.getExpirationFromToken(token))
            .build();
                                        
            return ResponseEntity.ok(payloadResponse);
        } else {
            throw new BadRequestException("token");
        }
    }
}
