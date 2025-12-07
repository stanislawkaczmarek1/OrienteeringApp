package com.example.orienteeringapp.web.controller.web;

import com.example.orienteeringapp.application.service.UserService;
import com.example.orienteeringapp.web.controller.base.BaseUserController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/web/users")
@Tag(name = "Web Users API")
public class WebUserController extends BaseUserController {
    public WebUserController(UserService userService) {
        super(userService);
    }
}

