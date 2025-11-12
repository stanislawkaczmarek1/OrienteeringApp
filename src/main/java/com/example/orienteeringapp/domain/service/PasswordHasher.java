package com.example.orienteeringapp.domain.service;

public interface PasswordHasher {
    String hash(String rawPassword);
    boolean verify(String rawPassword, String hashedPassword);
}
