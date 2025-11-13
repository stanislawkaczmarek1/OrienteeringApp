package com.example.orienteeringapp.application.service;

import com.example.orienteeringapp.application.dto.AuthenticationResponseDto;
import com.example.orienteeringapp.application.dto.CreateUserDto;
import com.example.orienteeringapp.application.dto.LoginRequestDto;
import com.example.orienteeringapp.domain.model.User;
import com.example.orienteeringapp.domain.repository.UserRepository;
import com.example.orienteeringapp.domain.service.PasswordHasher;
import com.example.orienteeringapp.infrastructure.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            PasswordHasher passwordHasher,
            JwtService jwtService,
            AuthenticationManager authenticationManager
    ) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponseDto register(CreateUserDto request) {
        String hashedPassword = passwordHasher.hash(request.getPassword());

        User user = new User(
                null,
                request.getUsername(),
                request.getFullName(),
                request.getEmail(),
                request.getPhoneNumber(),
                hashedPassword,
                request.isPrivate(),
                null
        );

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(request.getUsername());
        return new AuthenticationResponseDto(jwtToken, request.getUsername());
    }

    public AuthenticationResponseDto login(LoginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String jwtToken = jwtService.generateToken(request.getUsername());
        return new AuthenticationResponseDto(jwtToken, request.getUsername());
    }
}
