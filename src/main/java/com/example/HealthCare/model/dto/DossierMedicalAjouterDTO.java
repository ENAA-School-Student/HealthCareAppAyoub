package com.example.HealthCare.model.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter

public class DossierMedicalAjouterDTO {
    @NonNull
    private String diagnostic;
    @NonNull
    private String observations;
    @NonNull
    private LocalDate dateCreation;
    @NonNull
    private int patientId;
}
