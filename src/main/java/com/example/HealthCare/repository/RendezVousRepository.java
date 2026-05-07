package com.example.HealthCare.repository;

import com.example.HealthCare.dto.MedecinResponseDTO;
import com.example.HealthCare.dto.RendezVousResponseDTO;
import com.example.HealthCare.enums.Statut;
import com.example.HealthCare.model.DossierMedical;
import com.example.HealthCare.model.Medecine;
import com.example.HealthCare.model.Patient;
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

    List<RendezVousResponseDTO> findByStatut(Statut statut);


    @Query("select r from RendezVous r JOIN r.medecine m where m.id = :id")
    List<RendezVous> findRendezVousDeUnMedecine(long id);




//    @Query("select r from RendezVous r where r.dateRendezVous = :date")
//    List<RendezVous> rendezVousPourUnmedecinParUnDate(@Param("date") LocalDate date);
//    @Query(value ="select r.* FROM rendez_vous r LEFT JOIN patient p ON p.id = r.patient_id ",nativeQuery = true)
//    List<RendezVous> patietnRendezVous();
//
//    @Query(value = "SELECT m.*, count(r.id) as totalRendzeVous FROM medecine m LEFT JOIN rendez_vous r ON r.medecine_id = m.id GROUP BY m.id ",nativeQuery = true)
//    List<Medecine> allRendezvVousDeUnmedecein();
//
//    @Query("select p  from Patient p where (select COUNT(r) from RendezVous r  where r.patient = p) > :greaterThannumber")
//    List<Patient> getAllPatietRendezVousGreaterThan(int greaterThannumber);
//
//    @Query("select r from RendezVous r where r.dateRendezVous > CURRENT DATE")
//    List<RendezVous>  rendez_vousapreraujourdhui();



}
