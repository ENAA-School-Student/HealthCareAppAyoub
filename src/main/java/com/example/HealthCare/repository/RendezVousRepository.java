package com.example.HealthCare.repository;

import com.example.HealthCare.model.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous,Long> {
    List<RendezVous> findByPatient_Id(long id);
    List<RendezVous> findByMedecine_Id(long id);

}
