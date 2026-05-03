package com.example.HealthCare.dto;

import com.example.HealthCare.enums.Statut;
import com.example.HealthCare.model.Medecine;
import com.example.HealthCare.model.Patient;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class RendezVousResponseDTO {
    private long id;
    private LocalDate dateRendezVous;
    private Statut statut;
    private Medecine medecine;
    private Patient patient;

}