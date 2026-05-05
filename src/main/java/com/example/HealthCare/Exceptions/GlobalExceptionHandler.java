package com.example.HealthCare.Exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice

public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleAll(Exception e, HttpServletRequest request) {
        Map<String, Object> error = new HashMap<>();
        error.put("status", 404);
        error.put("error", e.getClass().getSimpleName());
        error.put("message", e.getMessage());
        error.put("path", request.getRequestURI());
        return ResponseEntity.status(404).body(error);
    }
}
