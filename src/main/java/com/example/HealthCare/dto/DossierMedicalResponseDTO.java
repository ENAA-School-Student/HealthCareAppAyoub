package com.example.HealthCare.dto;

import com.example.HealthCare.model.Patient;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
@Getter
@Setter
public class DossierMedicalResponseDTO implements Serializable {
    private static final long serialVersionUID=1L;
    private long id ;
    private String diagnostic;
    private String observations;
    private LocalDate dateCreation;
    private PatientResponseDTO patient;
}
