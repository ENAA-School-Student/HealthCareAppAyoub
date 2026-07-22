package com.example.HealthCare.repository;

import com.example.HealthCare.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    Page<Patient> findByNom(String nom, Pageable pageable);
    Optional<Patient> findById(Long id);
    Optional<Patient> findByUserUsername(String username);
}
