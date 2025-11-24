package com.example.orienteeringapp.web.controller;

import com.example.orienteeringapp.application.dto.GetUserResponseDto;
import com.example.orienteeringapp.application.service.UserService;
import com.example.orienteeringapp.application.dto.CreateUserDto;
import com.example.orienteeringapp.application.dto.CreateUserResponseDto;
import com.example.orienteeringapp.application.dto.UpdateUserDto;
import com.example.orienteeringapp.infrastructure.security.annotation.IsCurrentUser;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@Tag(name = "Users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Deprecated
    public ResponseEntity<CreateUserResponseDto> createUser(@RequestBody CreateUserDto dto) {
        CreateUserResponseDto responseDto = userService.createUser(dto);
        return  new ResponseEntity<>(responseDto, HttpStatus.CREATED);

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
