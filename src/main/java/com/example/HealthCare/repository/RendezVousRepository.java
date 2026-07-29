package com.example.HealthCare.repository;

import com.example.HealthCare.dto.MedecinResponseDTO;
import com.example.HealthCare.dto.RendezVousResponseDTO;
import com.example.HealthCare.enums.Statut;
import com.example.HealthCare.model.DossierMedical;
import com.example.HealthCare.model.Medecine;
import com.example.HealthCare.model.Patient;
import com.example.HealthCare.model.RendezVous;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    long count();

    Page<RendezVous> findByStatut(Statut statut, Pageable pageable);

    @Query("select r from RendezVous r JOIN r.medecine m where m.id = :id")
    List<RendezVous> findRendezVousDeUnMedecine(long id);


  @Query(value = "SELECT r.* as totalRendzeVous FROM rendez_vous r JOIN medecine m ON r.medecine_id = m.id ",nativeQuery = true)
  List<Medecine> allMedecinRendezVous();
//
//    @Query("select r from RendezVous r where r.dateRendezVous = :date")
//    List<RendezVous> rendezVousPourUnmedecinParUnDate(@Param("date") LocalDate date);

    @Query(value ="select r.* FROM rendez_vous r LEFT JOIN patient p ON p.id = r.patient_id ",nativeQuery = true)
    List<RendezVous> patietnRendezVous();

    @Query(value = "SELECT r.* FROM rendez_vous r WHERE r.patient_id = :patientId ", nativeQuery = true)
    List<RendezVous> findRendezVousByPatientId(@Param("patientId") Long patientId);

//    @Query("select p  from Patient p where (select COUNT(r) from RendezVous r  where r.patient = p) > :greaterThannumber")
//    List<Patient> getAllPatietRendezVousGreaterThan(int greaterThannumber);

//    @Query("select r from RendezVous r where r.dateRendezVous > CURRENT DATE")
//    List<RendezVous>  rendez_vousapreraujourdhui();

}
