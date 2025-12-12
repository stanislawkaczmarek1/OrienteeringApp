package com.example.orienteeringapp.web.controller.mobile;

import com.example.orienteeringapp.application.service.AuthenticationService;
import com.example.orienteeringapp.web.controller.base.BaseAuthenticationController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mobile/auth")
@Tag(name = "Mobile Authentication API")
public class MobileAuthenticationController extends BaseAuthenticationController {
    public MobileAuthenticationController(AuthenticationService authenticationService) {
        super(authenticationService);
    }
}


