package com.example.orienteeringapp.application.dto;


public record CreateUserDto(String username,
                            String fullName,
                            String email,
                            String phoneNumber,
                            String password,
                            boolean isPrivate) {
}