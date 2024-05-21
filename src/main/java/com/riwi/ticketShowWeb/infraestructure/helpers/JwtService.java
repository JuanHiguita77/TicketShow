package com.riwi.ticketShowWeb.infraestructure.helpers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.riwi.ticketShowWeb.domain.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService 
{
    private final String SECRET_KEY = "Y29udHJhc2XDsWEgdWx0cmEgc2VjcmV0YSwgY29udHJhc2XDsWEgdWx0cmEgc2VjcmV0YSwgY29udHJhc2XDsWEgdWx0cmEgc2VjcmV0YQ==";

    public SecretKey getKey()
    {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //Metodo para obtener los claims
    public Claims getAllClaims(String token)
    {
        //parser es para desarmar, contrario a builder
        //verificar la firma del servidor: verifyWith
        return Jwts
                .parser()
                .verifyWith(this.getKey())//validar
                .build()//construirlo
                .parseSignedClaims(token)//extraer los tokens de base 64 a json
                .getPayload();//obtener el payload o cuerpo
    } 

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = this.getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String getEmailFromToken(String token) {
        return this.getClaim(token, claims -> claims.get("email", String.class));
    }

    // Método para obtener el rol del token
    public String getRoleFromToken(String token) {
        return this.getClaim(token, claims -> claims.get("rol", String.class));
    }

    public Long getIssuedAtFromToken(String token) {
        return this.getClaim(token, Claims::getIssuedAt).getTime();
    }

    public Long getExpirationFromToken(String token) {
        return this.getClaim(token, Claims::getExpiration).getTime();
    }

    public Date getExpiration(String token) {
        return this.getClaim(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token) {
        return this.getExpiration(token).before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) 
    {
        String email = this.getEmailFromToken(token);
        return (email.equals(userDetails.getUsername()) && !this.isTokenExpired(token));
    }
}
