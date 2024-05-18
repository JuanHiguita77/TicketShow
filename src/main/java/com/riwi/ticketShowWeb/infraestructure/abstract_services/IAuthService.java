package com.riwi.ticketShowWeb.infraestructure.abstract_services;

import com.riwi.ticketShowWeb.api.dto.request.LoginRequest;
import com.riwi.ticketShowWeb.api.dto.request.RegisterRequest;
import com.riwi.ticketShowWeb.api.dto.response.AuthResp;

public interface IAuthService {
    
    public AuthResp login(LoginRequest request);

    public AuthResp register(RegisterRequest request);
}
