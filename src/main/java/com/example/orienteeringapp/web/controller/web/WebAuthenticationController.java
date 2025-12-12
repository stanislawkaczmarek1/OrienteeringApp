package com.example.orienteeringapp.web.controller.web;

import com.example.orienteeringapp.application.service.AuthenticationService;
import com.example.orienteeringapp.web.controller.base.BaseAuthenticationController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/web/auth")
@Tag(name = "Web Authentication API")
public class WebAuthenticationController extends BaseAuthenticationController {
    public WebAuthenticationController(AuthenticationService authenticationService) {
        super(authenticationService);
    }
}


