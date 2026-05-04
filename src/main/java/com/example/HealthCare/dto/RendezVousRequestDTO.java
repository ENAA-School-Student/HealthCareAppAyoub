package com.example.HealthCare.dto;

import com.example.HealthCare.enums.Statut;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class RendezVousRequestDTO {
    @NonNull
    private LocalDate dateRendezVous;

    private Statut statut = Statut.EN_ATTENTE ;
    @NonNull
    private Long medecinId;
    @NonNull
    private Long patientId;
}
