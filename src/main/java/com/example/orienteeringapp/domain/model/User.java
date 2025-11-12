package com.example.orienteeringapp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String passwordHash;
    private boolean isPrivate;
    private LocalDateTime createdAt;
}

