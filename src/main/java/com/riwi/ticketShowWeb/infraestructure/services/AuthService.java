package com.riwi.ticketShowWeb.infraestructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.riwi.ticketShowWeb.api.dto.request.LoginRequest;
import com.riwi.ticketShowWeb.api.dto.request.RegisterRequest;
import com.riwi.ticketShowWeb.api.dto.response.AuthResponse;
import com.riwi.ticketShowWeb.domain.entities.User;
import com.riwi.ticketShowWeb.domain.repositories.UserRepository;
import com.riwi.ticketShowWeb.infraestructure.abstract_services.IAuthService;
import com.riwi.ticketShowWeb.infraestructure.helpers.JwtService;
import com.riwi.ticketShowWeb.utils.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService implements IAuthService{
    
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse login(LoginRequest request){
        try{
            
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
            );            
        } catch (Exception e) {
            throw new BadRequestException("Invalid information");
        }

        User user = this.findByUserName(request.getUserName());

        return AuthResponse.builder()
                .message("Correct Authentication")
                .token(this.jwtService.getToken(user))
                .build();
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
       
        User exist = this.findByUserName(request.getUserName());

        if (exist != null) {
            throw new BadRequestException("User already exists");
        }

      
        User user = User.builder()
                .userName(request.getUserName())
                .password(this.passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        
        user = this.userRepository.save(user);

        return AuthResponse.builder()
                .message("was registered correctly")
                .token(this.jwtService.getToken(user))
                .build();

    }

    private User findByUserName(String username)
    {
        return this.userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));         
    }

}

