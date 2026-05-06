package com.example.HealthCare.repository;

import com.example.HealthCare.model.Medecine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedecinRepository extends JpaRepository<Medecine,Long> {

    @Query("select m from Medecine m where m.specialite = :specialite")
    List<Medecine> findMedcineByspecialite(String specialite);

}
