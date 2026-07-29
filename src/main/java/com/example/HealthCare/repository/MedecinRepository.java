package com.example.HealthCare.repository;

import com.example.HealthCare.model.Medecine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedecinRepository extends JpaRepository<Medecine,Long> {

    @Query("select m from Medecine m where m.specialite = :specialite")
    Page<Medecine> findMedcineByspecialite(String specialite, Pageable pageable);

long count();
}
