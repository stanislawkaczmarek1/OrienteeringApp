package com.example.orienteeringapp.web.controller;

import com.example.orienteeringapp.application.service.UserService;
import com.example.orienteeringapp.application.dto.CreateUserDto;
import com.example.orienteeringapp.application.dto.CreateUserResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public CreateUserResponseDto createUser(@RequestBody CreateUserDto dto) {
        return userService.createUser(dto);
    }

}

