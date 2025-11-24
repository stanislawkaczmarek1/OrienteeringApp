package com.example.orienteeringapp.application.service;

import com.example.orienteeringapp.application.dto.*;
import com.example.orienteeringapp.application.exception.InvalidTokenException;
import com.example.orienteeringapp.application.exception.TokenExpiredException;
import com.example.orienteeringapp.domain.model.RefreshToken;
import com.example.orienteeringapp.domain.model.User;
import com.example.orienteeringapp.domain.repository.UserRepository;
import com.example.orienteeringapp.domain.repository.RefreshTokenRepository;
import com.example.orienteeringapp.domain.service.PasswordHasher;
import com.example.orienteeringapp.infrastructure.security.GoogleTokenVerifier;
import com.example.orienteeringapp.infrastructure.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;
import java.util.UUID;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordHasher passwordHasher;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final GoogleTokenVerifier googleTokenVerifier;

    private final long refreshTokenDurationMs;

    public AuthenticationService(
            UserRepository userRepository,
            RefreshTokenRepository refreshTokenRepository,
            PasswordHasher passwordHasher,
            JwtService jwtService,
            AuthenticationManager authenticationManager,
            GoogleTokenVerifier googleTokenVerifier,
            @Value("${jwt.refresh.expiration}") long refreshTokenDurationMs
    ) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.passwordHasher = passwordHasher;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.googleTokenVerifier = googleTokenVerifier;
        this.refreshTokenDurationMs = refreshTokenDurationMs;
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

        User savedUser = userRepository.save(user);
        return generateTokens(savedUser);
    }

    public AuthenticationResponseDto login(LoginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return generateTokens(user);
    }

    public AuthenticationResponseDto loginWithGoogle(String idTokenString) {
        var payload = googleTokenVerifier.verifyToken(idTokenString);

        if (payload == null || !Boolean.TRUE.equals(payload.getEmailVerified())) {
            throw new InvalidTokenException("Invalid Google ID token or email not verified");
        }

        String email = payload.getEmail();
        String fullName = (String) payload.get("name");

        var user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User newUser = new User(
                            null,
                            email,
                            fullName,
                            email,
                            null,
                            "",
                            false,
                            null
                    );
                    return userRepository.save(newUser);
                });

        return generateTokens(user);
    }

    @Transactional
    public RefreshTokenResponseDto refreshAccessToken(String refreshTokenValue) {
        RefreshToken refreshToken = refreshTokenRepository.findByTokenWithLock(refreshTokenValue)
                .map(this::verifyExpiration)
                .orElseThrow(() -> new InvalidTokenException("Invalid refresh token"));

        User user = userRepository.findById(refreshToken.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String newAccessToken = jwtService.generateToken(user.getUsername());

        refreshTokenRepository.deleteById(refreshToken.getId());

        RefreshToken newRefreshToken = new RefreshToken(
                null,
                refreshToken.getUserId(),
                UUID.randomUUID().toString(),
                Instant.now().plusMillis(refreshTokenDurationMs)
        );
        refreshTokenRepository.save(newRefreshToken);

        return new RefreshTokenResponseDto(newAccessToken, newRefreshToken.getToken());
    }

    @Transactional
    protected AuthenticationResponseDto generateTokens(User user) {
        refreshTokenRepository.deleteByUserId(user.getId());

        String jwtToken = jwtService.generateToken(user.getUsername());
        RefreshToken refreshToken = new RefreshToken(
                null,
                user.getId(),
                UUID.randomUUID().toString(),
                Instant.now().plusMillis(refreshTokenDurationMs)
        );
        refreshTokenRepository.save(refreshToken);

        return new AuthenticationResponseDto(jwtToken, user.getUsername(), refreshToken.getToken());
    }

    private RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.deleteByUserId(token.getUserId());
            throw new TokenExpiredException("Refresh token has expired");
        }
        return token;
    }
}
