package com.example.orienteeringapp.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {
    private String username;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;
    private boolean isPrivate;
}