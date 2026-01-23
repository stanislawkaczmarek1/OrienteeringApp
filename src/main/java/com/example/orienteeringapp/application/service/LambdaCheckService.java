package com.example.orienteeringapp.application.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;

@Service
@RequiredArgsConstructor
public class LambdaCheckService {

    private final LambdaClient lambdaClient;

    public String checkFrontend(String url) {

        String payload = """
            {
              "url": "%s"
            }
            """.formatted(url);

        InvokeRequest request = InvokeRequest.builder()
                .functionName("orienteeringFunction")
                .payload(SdkBytes.fromUtf8String(payload))
                .build();

        InvokeResponse response = lambdaClient.invoke(request);

        return response.payload().asUtf8String();
    }
}
