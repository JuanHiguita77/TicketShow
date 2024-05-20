package com.riwi.ticketShowWeb.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.ticketShowWeb.api.dto.response.PayloadResponse;
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
public class PayloadController {

    @Autowired
    private final JwtService jwtService;
    
    @ApiResponse(responseCode = "400", description = "Token Invalid", content = 
    {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
    })
    @Operation(summary = "Send Token to verify", description = "Send a token to verify")
    @PostMapping(path = "/admin/payload")
    public ResponseEntity<PayloadResponse> payloadObtainer(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) 
    {
        // Verificar si el encabezado de autorización no está vacío y comienza con "Bearer "
        if (StringUtils.hasLength(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) 
        {
            // Extraer el token del encabezado
            String token = authorizationHeader.substring(7);

            // Extraer el payload del token
            Claims claims = jwtService.getAllClaims(token);

            // Construir la respuesta del payload
            PayloadResponse payloadResponse = PayloadResponse.builder()
                    .sub(claims.getSubject())
                    .role(claims.get("role", String.class))
                    .id(claims.get("id", Long.class))
                    .exp(claims.getExpiration().getTime())
                    .build();

            // Devolver el payload del token como parte de la respuesta
            return ResponseEntity.ok(payloadResponse);
        } else {
            throw new BadRequestException("Invalid Token");
        }
    }



    /*@PostMapping(path = "/auth/payload")
    public ResponseEntity<PayloadResponse> payloadObtainer(@RequestBody String token) 
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
    }*/
}
