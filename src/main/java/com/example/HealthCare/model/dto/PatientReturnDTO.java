package com.example.HealthCare.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PatientReturnDTO {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private LocalDate dateNaissance;
    private List<RendezVousReturnDTO> rendezVousList;
    private DossierMedicalDTO dossierMedical;
}
