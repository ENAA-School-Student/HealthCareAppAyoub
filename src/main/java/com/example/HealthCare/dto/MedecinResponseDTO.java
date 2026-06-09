package com.example.HealthCare.dto;

import com.example.HealthCare.model.RendezVous;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
public class MedecinResponseDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String nom;
    private String telephone;
    private String email;
    private String specialite;
}
