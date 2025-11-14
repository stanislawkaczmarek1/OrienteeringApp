package com.example.orienteeringapp.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private String error;
    private String message;
    private LocalDateTime timestamp;
    private String path;

    public ErrorResponse(String error, String message) {
        this.error = error;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public static ErrorResponse of(String error, String message) {
        return new ErrorResponse(error, message);
    }

    public static ErrorResponse of(String error, String message, String path) {
        ErrorResponse response = new ErrorResponse(error, message);
        response.setPath(path);
        return response;
    }
}
