package com.example.HealthCare.dto;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class AuthenticationResponceDTO {
    private String token;
    private String username;
}
