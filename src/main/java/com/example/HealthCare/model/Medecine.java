package com.example.HealthCare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "medecine")
public class Medecine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nom;
    private String telephone;
    private String email;
    private String specialite;

    @OneToMany(mappedBy = "medecine")
    @JsonIgnore
    private List<RendezVous> rendezVousList;

}
