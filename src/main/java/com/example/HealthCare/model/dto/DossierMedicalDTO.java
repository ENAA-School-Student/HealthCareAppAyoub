package com.example.HealthCare.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter

public class DossierMedicalDTO {
    private String diagnostic;
    private String observations;
    private LocalDate dateCreation;

}
