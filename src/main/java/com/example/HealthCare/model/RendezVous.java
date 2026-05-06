package com.example.HealthCare.model;

import com.example.HealthCare.enums.Statut;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "date_rendez_vous")
    private LocalDate dateRendezVous;
    @Column(name="statut")
    @Enumerated(EnumType.STRING)
    private Statut statut;

    @ManyToOne
    @JoinColumn(name = "medecine_id")
    @JsonIgnore
    private Medecine medecine;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
