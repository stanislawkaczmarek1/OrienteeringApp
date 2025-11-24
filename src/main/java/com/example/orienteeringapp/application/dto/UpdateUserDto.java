package com.example.orienteeringapp.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    private String fullName;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(
        regexp = "^[+]?[(]?[0-9]{1,4}[)]?[-\\s.]?[(]?[0-9]{1,4}[)]?[-\\s.]?[0-9]{1,9}$",
        message = "Invalid phone number format"
    )
    private String phoneNumber;

    private Boolean isPrivate;

    private String currentPassword;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private String newPassword;
}
