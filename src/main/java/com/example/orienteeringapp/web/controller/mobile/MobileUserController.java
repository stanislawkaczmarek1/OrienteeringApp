package com.example.orienteeringapp.web.controller.mobile;

import com.example.orienteeringapp.application.service.UserService;
import com.example.orienteeringapp.web.controller.base.BaseUserController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mobile/users")
@Tag(name = "Mobile Users API")
public class MobileUserController extends BaseUserController {
    public MobileUserController(UserService userService) {
        super(userService);
    }
}


