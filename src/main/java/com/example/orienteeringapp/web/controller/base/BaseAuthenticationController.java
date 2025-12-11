package com.example.orienteeringapp.web.controller.base;

import com.example.orienteeringapp.application.dto.*;
import com.example.orienteeringapp.application.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class BaseAuthenticationController {

    protected final AuthenticationService authenticationService;

    protected BaseAuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> register(@Valid @RequestBody CreateUserDto request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(@Valid @RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/login/google")
    public ResponseEntity<AuthenticationResponseDto> loginWithGoogle(@Valid @RequestBody GoogleLoginRequestDto request) {
        return ResponseEntity.ok(authenticationService.loginWithGoogle(request.getIdToken()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponseDto> refreshToken(@Valid @RequestBody RefreshTokenRequestDto request) {
        return ResponseEntity.ok(authenticationService.refreshAccessToken(request.getRefreshToken()));
    }
}



