package com.example.HealthCare.dto;

import com.example.HealthCare.model.RendezVous;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class MedecinResponseDTO {
    private int id;
    private String nom;
    private String telephone;
    private String email;
    private String specialite;
    private List<RendezVous> rendezVousList;

}
