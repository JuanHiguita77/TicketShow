package com.riwi.ticketShowWeb.infraestructure.services;

import javax.management.relation.RoleNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.riwi.ticketShowWeb.api.dto.request.LoginRequest;
import com.riwi.ticketShowWeb.api.dto.request.RegisterRequest;
import com.riwi.ticketShowWeb.api.dto.response.AuthResp;
import com.riwi.ticketShowWeb.domain.entities.Role;
import com.riwi.ticketShowWeb.domain.entities.User;
import com.riwi.ticketShowWeb.domain.repositories.RoleRepository;
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
    private final RoleRepository roleRepository;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResp login(LoginRequest request){
        try{
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
            );            
        } catch (Exception e) {
            throw new BadRequestException("Invalid information");
        }

        User user = this.findByUserName(request.getUserName());

        return AuthResp.builder()
                .message("Correct Authentication")
                .token(this.jwtService.getToken(user))
                .build();
    }

    @Override
    public AuthResp register(RegisterRequest request) {
        User exist = this.findByUserName(request.getUserName());
        
        if (exist != null) {
            throw new BadRequestException("User already exists");
        }

        Role role = this.findRoleById(request.getRole_id()); // Aquí se obtiene el rol de manera más clara

        User user = User.builder()
                .userName(request.getUserName())
                .password(this.passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(role)
                .build();

        user = this.userRepository.save(user);

        return AuthResp.builder()
                .message("was registered correctly")
                .token(this.jwtService.getToken(user))
                .build();
    }

    private User findByUserName(String username) {
        return this.userRepository.findByUserName(username).orElse(null);
    }

    private Role findRoleById(Long roleId) 
    {
        return this.roleRepository.findById(roleId)
                .orElseThrow(() -> new BadRequestException("Role not found"));
    }
}
