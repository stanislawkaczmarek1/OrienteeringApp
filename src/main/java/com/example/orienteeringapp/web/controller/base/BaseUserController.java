package com.example.orienteeringapp.web.controller.base;

import com.example.orienteeringapp.application.dto.GetUserResponseDto;
import com.example.orienteeringapp.application.dto.UpdateUserDto;
import com.example.orienteeringapp.application.service.UserService;
import com.example.orienteeringapp.infrastructure.security.annotation.IsCurrentUser;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

public abstract class BaseUserController {

    protected final UserService userService;

    protected BaseUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<GetUserResponseDto> getCurrentUser(Authentication authentication) {
        GetUserResponseDto responseDto = userService.getCurrentUser(authentication.getName());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<GetUserResponseDto> getUser(@PathVariable Long id) {
        GetUserResponseDto responseDto = userService.getUser(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @IsCurrentUser
    public ResponseEntity<GetUserResponseDto> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserDto dto
    ) {
        GetUserResponseDto responseDto = userService.updateUser(id, dto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    @IsCurrentUser
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}



