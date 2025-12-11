package com.example.orienteeringapp.web.controller.web;

import com.example.orienteeringapp.application.service.UserFollowsService;
import com.example.orienteeringapp.web.controller.base.BaseUserFollowsController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/web/user-follows")
@Tag(name = "Web User Follows API")
public class WebUserFollowsController extends BaseUserFollowsController {
    public WebUserFollowsController(UserFollowsService userFollowsService) {
        super(userFollowsService);
    }
}


