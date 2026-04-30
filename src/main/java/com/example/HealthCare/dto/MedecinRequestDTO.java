package com.example.HealthCare.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedecinRequestDTO
{
    @NotBlank(message = "Nom is required")
    private String nom;
    @NotBlank(message = "telephone is required")
    private String telephone;
    @NotBlank(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;
    @NotBlank(message = "specialite is required")
    private String specialite;
}
