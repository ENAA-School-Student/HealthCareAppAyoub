package com.example.HealthCare.model.dto;

import com.example.HealthCare.model.entity.Medecine;
import com.example.HealthCare.model.entity.Patient;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class RendezVousAjouterDTO {
    @NonNull
    private LocalDate dateRendezVous;
    @NonNull
    private String statut;
    @NonNull
    private Long medecinId;
    @NonNull
    private Long patientId;
}
