package com.riwi.ticketShowWeb.infraestructure.abstract_services;

import com.riwi.ticketShowWeb.api.dto.request.LoginRequest;
import com.riwi.ticketShowWeb.api.dto.request.RegisterRequest;
import com.riwi.ticketShowWeb.api.dto.response.AuthResponse;

public interface IAuthService {
    
    public AuthResponse login(LoginRequest request);

    public AuthResponse register(RegisterRequest request);
}
