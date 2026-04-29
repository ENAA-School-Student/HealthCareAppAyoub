package com.example.HealthCare.repository;

import com.example.HealthCare.model.entity.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DossierMedicalRepository extends JpaRepository<DossierMedical,Long> {
    DossierMedical findByPatient_id(int id);
}
