package com.example.orienteeringapp.web.controller;

import com.example.orienteeringapp.application.service.FollowRequestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/follow-requests")
@Tag(name = "FollowRequests")
public class FollowRequestController {
    private final FollowRequestService followRequestService;

    public FollowRequestController(FollowRequestService followRequestService) {
        this.followRequestService = followRequestService;
    }
}