package com.example.HealthCare.dto;

import com.example.HealthCare.model.Patient;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class DossierMedicalResponseDTO {
    private int id ;
    private String diagnostic;
    private String observations;
    private LocalDate dateCreation;
    private Patient patient;
}
