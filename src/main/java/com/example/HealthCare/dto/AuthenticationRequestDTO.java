package com.example.HealthCare.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
@Builder
@Data
public class AuthenticationRequestDTO {
    @NonNull
    private String username;
    @NonNull
    @Email
    private String email;
    @NonNull
    @Size(min=6, max=50)
    private String password;
}
