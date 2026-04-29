package com.example.HealthCare.repository;

import com.example.HealthCare.model.entity.Medecine;
import com.example.HealthCare.model.entity.Patient;
import com.example.HealthCare.model.entity.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {
    List<RendezVous> findByPatient_Id(int id);
    List<RendezVous> findByMedecine_Id(int id);

}
