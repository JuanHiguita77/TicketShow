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

    // Declarar Rutas publicas
    private final String[] PUBLIC_RESOURCES  = { "/services/public/get","/auth/**" };
    
    /*
     * La anotación @Bean en Spring Boot indica que el objeto retornado por el
     * método debe ser registrado como un bean en el contexto de Spring.
    */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        return http
                .csrf(csrf -> csrf.disable()) //Desabilitar protección csrf -> Statelest
                .authorizeHttpRequests(authRequest -> authRequest
                    .requestMatchers(PUBLIC_RESOURCES).permitAll() //Configurar rutas publicas
                    .anyRequest().authenticated() 
                )
                .sessionManagement(sessionManager -> 
                    sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider) //Agregarmos el proveedor de autenticación 
                .addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class) //Agregar el filtro personalizado antes del filtro de spring security
                .build();
    }
}