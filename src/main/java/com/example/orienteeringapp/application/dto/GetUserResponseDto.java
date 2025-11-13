package com.example.orienteeringapp.application.dto;

public record GetUserResponseDto(String username,
                                 String fullName,
                                 String email,
                                 String phoneNumber,
                                 boolean isPrivate) {
}
