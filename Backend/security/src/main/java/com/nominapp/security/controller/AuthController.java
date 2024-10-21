package com.nominapp.security.controller;

import com.nominapp.security.model.request.LoginRequest;
import com.nominapp.security.model.request.RegisterRequest;
import com.nominapp.security.model.response.RegisterResponse;
import com.nominapp.security.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest register){
        RegisterResponse response = service.register(register);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest login){
        String response = service.login(login);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}


