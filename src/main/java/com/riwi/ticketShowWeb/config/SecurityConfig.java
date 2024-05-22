package com.riwi.ticketShowWeb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.riwi.ticketShowWeb.infraestructure.helpers.JwtAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    @Autowired
    private final AuthenticationProvider authenticationProvider;

    @Autowired
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final String[] PUBLIC_RESOURCES = {
        "/auth/**",
        "/events/auth/search",
        "/events/auth/{id}",
        "/events/sendEmail/{idEvent}",
        "/seat/selectSeat/{idEvent}",
        "/seat/**",
        "/error/**",
        "/error"
    };

    private final String[] ADMIN_RESOURCES = {
        "/admin/payload",
        "/swagger-ui/index.html",
        "/v2/api-docs",
        "/v3/api-docs/**",
        "/swagger-resources/**",
        "/swagger-ui.html",
        "/swagger-ui/**",
        "/webjars/**",
        "http://localhost:5173/**",
        "/api/v1/**",
        "/events/add",
        "/events/delete/{id}",
        "/events/update/{id}",
        "/add",
        "/delete",
        "/admin/**",
        "/admin/payload"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authRequest -> authRequest
                        .requestMatchers(PUBLIC_RESOURCES).permitAll()
                        .requestMatchers(ADMIN_RESOURCES).hasAuthority("admin").anyRequest().authenticated()
                )
                .sessionManagement(sessionManager ->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
