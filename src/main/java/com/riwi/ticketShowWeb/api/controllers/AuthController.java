package com.riwi.ticketShowWeb.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.ticketShowWeb.api.dto.request.LoginRequest;
import com.riwi.ticketShowWeb.api.dto.request.RegisterRequest;
import com.riwi.ticketShowWeb.api.dto.response.AuthResp;
import com.riwi.ticketShowWeb.api.dto.response.PayloadResponse;
import com.riwi.ticketShowWeb.infraestructure.abstract_services.IAuthService;
import com.riwi.ticketShowWeb.infraestructure.helpers.JwtAuthenticationFilter;
import com.riwi.ticketShowWeb.infraestructure.helpers.JwtService;
import com.riwi.ticketShowWeb.utils.exceptions.BadRequestException;

import io.jsonwebtoken.Claims;
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
public class AuthController {

    // Inyeccion de dependencia
    @Autowired
    private final IAuthService authService;
    
    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final JwtAuthenticationFilter jwtFilter;
    
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
    ) 
    {
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
    ) 
    {
        return ResponseEntity.ok(this.authService.register(request));
    }
    
    @ApiResponse(responseCode = "400", description = "Token Invalid", content = 
    {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @Operation(summary = "Send Token to verify", description = "Send a token to verify")
    @PostMapping(path = "/admin/payload")
    public ResponseEntity<PayloadResponse> obtenerPayloadToken(@RequestBody String token) 
    {
        // Verificar si el token es válido
        if (StringUtils.hasLength(token) && token.startsWith("Bearer ")) {
            // Extraer el token del encabezado
            String tokenNew = token.substring(7); // Eliminar el prefijo "Bearer "
            
            // Extraer el payload del token
            Claims claims = jwtService.getAllClaims(tokenNew);

            // Construir la respuesta del payload
            PayloadResponse payloadResponse = PayloadResponse.builder()
                    .sub(claims.getSubject())
                    .role(claims.get("role", String.class))
                    .id(claims.get("id", Long.class))
                    .build();
    
            // Devolver el payload del token como parte de la respuesta
            return ResponseEntity.ok(payloadResponse);
        }
        else
        {
            throw new BadRequestException("Invalid Token");
        }
    }
}
