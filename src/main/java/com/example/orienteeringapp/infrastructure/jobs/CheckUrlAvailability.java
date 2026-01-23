package com.example.orienteeringapp.infrastructure.jobs;

import com.example.orienteeringapp.application.service.LambdaCheckService;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CheckUrlAvailability {
    private final LambdaCheckService lambdaCheckService;

    public CheckUrlAvailability(LambdaCheckService lambdaCheckService) {
        this.lambdaCheckService = lambdaCheckService;
    }

    @Scheduled(cron = "* * * * * *")
    public void checkUrlAvailability() {
        String result = lambdaCheckService.checkFrontend("https://mobileorienteering.com");
        System.out.println(result);
    }
}
