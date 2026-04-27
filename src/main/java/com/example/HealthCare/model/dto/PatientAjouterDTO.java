package com.example.HealthCare.model.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
public class PatientAjouterDTO {
    @NotBlank
    private String nom;
    @NotBlank
    private String prenom;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String telephone;
    @NotNull
    private LocalDate dateNaissance;
}
