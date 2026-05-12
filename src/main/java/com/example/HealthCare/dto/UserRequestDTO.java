package com.example.HealthCare.dto;

import lombok.Data;
import lombok.NonNull;
@Data
public class UserRequestDTO {
    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String password;
}
