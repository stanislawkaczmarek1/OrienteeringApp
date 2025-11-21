package com.example.orienteeringapp.web.controller;

import com.example.orienteeringapp.application.service.UserFollowsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-follows")
@Tag(name = "UserFollows")
public class UserFollowsController {
    private final UserFollowsService userFollowsService;

    public UserFollowsController(UserFollowsService userFollowsService) {
        this.userFollowsService = userFollowsService;
    }
}