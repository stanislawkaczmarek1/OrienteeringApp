package com.example.orienteeringapp.web.controller.web;

import com.example.orienteeringapp.application.service.LambdaCheckService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/web/lambda")
public class WebLambdaController {

    private final LambdaCheckService service;

    @PostMapping("/check-frontend")
    public String check(@RequestBody Map<String, String> body) {
        return service.checkFrontend(body.get("url"));
    }
}
