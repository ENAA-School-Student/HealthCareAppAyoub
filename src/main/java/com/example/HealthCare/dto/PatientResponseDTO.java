package com.example.HealthCare.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PatientResponseDTO {
    private long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private LocalDate dateNaissance;
    private List<RendezVousResponseDTO> rendezVousList;
    private DossierMedicalRequestDTO dossierMedical;
}
