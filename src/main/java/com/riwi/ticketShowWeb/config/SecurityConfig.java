package com.riwi.ticketShowWeb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Declarar Rutas publicas
    private final String[] PUBLIC_RESOURCES = { "/events/search/", "/auth/**" };

    /*
     * La anotación @Bean en Spring Boot indica que el objeto retornado por el
     * método debe ser registrado como un bean en el contexto de Spring.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf.disable()) // Desabilitar proteccion csrf
                .authorizeHttpRequests(authRequest -> authRequest
                        .requestMatchers(PUBLIC_RESOURCES).permitAll() // Configurar rutas publicas
                        .anyRequest().authenticated())
                .build();
    }
}
