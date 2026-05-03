package com.example.HealthCare.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter

public class DossierMedicalRequestDTO {
    @NonNull
    private String diagnostic;
    @NonNull
    private String observations;
    @NonNull
    private LocalDate dateCreation;
    @NonNull
    private long patientId;
}
