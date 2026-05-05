package com.example.HealthCare.repository;

import com.example.HealthCare.dto.RendezVousResponseDTO;
import com.example.HealthCare.model.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {
    List<RendezVous> findByPatient_Id(long id);
    List<RendezVous> findByMedecine_Id(long id);
    @Query("select r from RendezVous r where r.dateRendezVous = :date")
    List<RendezVous> rendezVousPourUnmedecinParUnDate(@Param("date") LocalDate date);
    @Query(value ="select r.* FROM rendez_vous r LEFT JOIN patient p ON p.id = r.patient_id ",nativeQuery = true)
    List<RendezVous> patietnRendezVous();





}
