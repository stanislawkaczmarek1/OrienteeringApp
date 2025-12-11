package com.example.orienteeringapp.web.controller.mobile;

import com.example.orienteeringapp.application.service.UserFollowsService;
import com.example.orienteeringapp.web.controller.base.BaseUserFollowsController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mobile/user-follows")
@Tag(name = "Mobile User Follows API")
public class MobileUserFollowsController extends BaseUserFollowsController {
    public MobileUserFollowsController(UserFollowsService userFollowsService) {
        super(userFollowsService);
    }
}


