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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
    private final String[] PUBLIC_RESOURCES  = { "/events/auth/search",
    "/events/sendEmail", 
    "/events/auth/**",
    "/error/**",
    "/error",
    "/events/error/**",
    "/events/error",
    "/auth/payload",
    "/swagger-ui/index.html", 
    "/v2/api-docs",
    "/v3/api-docs/**",
    "/swagger-resources/**",
    "/swagger-ui.html",
    "/swagger-ui/**",
    "/webjars/**",


    "http://localhost:5173/",
    "/events/**",
    "/api/v1/**",
    "/add",
    "/delete",
    "/admin/**"
    }; //Rutas publicas

    private final String[] ADMIN_RESOURCES  = { 

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
                .requestMatchers(ADMIN_RESOURCES).hasAuthority("ADMIN").anyRequest().authenticated()
                )//Configurar rutas publicas
                .sessionManagement(sessionManager -> 
                    sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider) //Agregarmos el proveedor de autenticación 
                .addFilterBefore(jwtAuthenticationFilter,UsernamePasswordAuthenticationFilter.class) //Agregar el filtro personalizado antes del filtro de spring security
                .build();
    }

    
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:5173/");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
