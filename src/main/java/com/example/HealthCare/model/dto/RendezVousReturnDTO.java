package com.example.HealthCare.model.dto;

import com.example.HealthCare.model.entity.Medecine;
import com.example.HealthCare.model.entity.Patient;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class RendezVousReturnDTO {
    private int id;
    private LocalDate dateRendezVous;
    private String statut;
    private Medecine medecine;
    private Patient patient;

}