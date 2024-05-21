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

    // Inyecciones de dependencia
    @Autowired
    private final AuthenticationProvider authenticationProvider;

    @Autowired
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    // Rutas públicas específicas
    private final String[] PUBLIC_RESOURCES = {
        // Rutas de eventos
        "/auth/**",
        "/events/auth/search",
        "/events/auth/{id}", 
    
        // Rutas de correo electrónico
        "/events/sendEmail/{idEvent}",

        // Rutas para seleccionar asientos
        "/seat/selectSeat/{idEvent}",
        "/seat/**",

        "/error",
        "/error/**",

        "/auth/payload",
    };
    
    
    private final String[] ADMIN_RESOURCES  = { 

        // Rutas para la documentación de Swagger
        "/swagger-ui/index.html",
        "/v2/api-docs",
        "/v3/api-docs/**",
        "/swagger-resources/**",
        "/swagger-ui.html",
        "/swagger-ui/**",
        "/webjars/**",
    
        // Otras rutas públicas generales
        "http://localhost:5173/**",
        "/api/v1/**",
    
        // Rutas para acciones específicas (añadir, eliminar, admin)
        "/events/add",
        "/events/delete/{id}",
        "/events/update/{id}",
        "/add",
        "/delete",
     };
    
    /*
     * La anotación @Bean en Spring Boot indica que el objeto retornado por el
     * método debe ser registrado como un bean en el contexto de Spring.
    */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        return http
                .csrf(csrf -> csrf.disable()) //Desabilitar protección csrf -> Statelest
                .authorizeHttpRequests(authRequest -> authRequest
                .requestMatchers(PUBLIC_RESOURCES).permitAll() 
                .requestMatchers(ADMIN_RESOURCES).hasAuthority("admin").anyRequest().authenticated()
                )//Configurar rutas publicas
                .sessionManagement(sessionManager -> 
                    sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider) //Agregarmos el proveedor de autenticación 
                .addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class) //Agregar el filtro personalizado antes del filtro de spring security
                .build();
    }

}
