package com.riwi.ticketShowWeb.infraestructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riwi.ticketShowWeb.api.dto.request.LoginRequest;
import com.riwi.ticketShowWeb.api.dto.request.RegisterRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
    
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthRep login(LoginRequest request){
        try{
            
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
            );            
        } catch (Exception e) {
            throw new BadRequestException("Invalid information");
        }

        User user = this.findUser(request.getUserName());

        return AuthResp.builder()
                .message("Correct Authentication")
                .token(this.jwtService.getToken(user))
                .build();
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
       
        User exist = this.findUser(request.getUserName());

        if (exist != null) {
            throw new BadRequestException("User already exists");
        }

      
        User user = User.builder()
                .userName(request.getUserName())
                .password(this.passwordEncoder.encode(request.getPassword()))
                .role(Role.CLIENT)
                .build();

        
        user = this.userRepository.save(user);

        return AuthResp.builder()
                .message("was registered correctly")
                .token(this.jwtService.getToken(user))
                .build();

    }
    
    private User findByUserName(String username){
        return this.userRepository.findByUserName(username)
                .orElse(null);
    }

}

