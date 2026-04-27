package com.example.HealthCare.model.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class RendezVousReturnDTO {
    private int id;
    private LocalDate dateRendezVous;
    private String statut;
}