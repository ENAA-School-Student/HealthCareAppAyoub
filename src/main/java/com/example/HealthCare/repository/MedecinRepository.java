package com.example.HealthCare.repository;

import com.example.HealthCare.model.Medecine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedecinRepository extends JpaRepository<Medecine,Long> {
}
