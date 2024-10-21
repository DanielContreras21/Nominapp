package com.nominapp.security.service;

import com.nominapp.security.model.request.LoginRequest;
import com.nominapp.security.model.request.RegisterRequest;
import com.nominapp.security.model.response.RegisterResponse;

public interface AuthService {
    String login(LoginRequest login);
    RegisterResponse register(RegisterRequest register);
}
