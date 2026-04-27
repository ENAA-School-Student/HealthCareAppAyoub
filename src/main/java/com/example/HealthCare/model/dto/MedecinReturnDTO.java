package com.example.HealthCare.model.dto;

import com.example.HealthCare.model.entity.RendezVous;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class MedecinReturnDTO {
    private int id;
    private String nom;
    private String telephone;
    private String email;
    private String specialite;
    private List<RendezVous> rendezVousList;

}
