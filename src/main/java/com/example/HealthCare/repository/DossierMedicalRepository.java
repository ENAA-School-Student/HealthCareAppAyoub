package com.example.HealthCare.repository;

import com.example.HealthCare.model.DossierMedical;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DossierMedicalRepository extends JpaRepository<DossierMedical,Long> {
    Optional<DossierMedical>findByPatient_id(long id);
    Page<DossierMedical>findByDiagnostic(String diagnostic,Pageable pageable);

    long count();

//    @Query("select d from DossierMedical d join Patient p ON p = d.patient ")
//    List<DossierMedical> getDossierMedecalWithPatietnInfoes();
}
