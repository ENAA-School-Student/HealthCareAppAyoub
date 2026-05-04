package com.example.HealthCare.service;

import com.example.HealthCare.dto.DossierMedicalRequestDTO;
import com.example.HealthCare.dto.DossierMedicalResponseDTO;
import com.example.HealthCare.dto.PatientRequestDTO;
import com.example.HealthCare.dto.PatientResponseDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class DossierMedicalServiceTest {
    @Autowired
  private  DossierMedicalService dossierMedicalService;
  DossierMedicalRequestDTO dossierMedicalRequestDTO;
    @Autowired
    private PatientService patientService;
    PatientRequestDTO patientRequestDTO;
    PatientResponseDTO savedPatient;
  @BeforeEach
  void setUp(){
      dossierMedicalRequestDTO = new DossierMedicalRequestDTO();
      dossierMedicalRequestDTO.setDateCreation(LocalDate.of(2020,02,22));
      dossierMedicalRequestDTO.setDiagnostic("diabetes");
      dossierMedicalRequestDTO.setObservations("stable");
      dossierMedicalRequestDTO.setPatientId(1);

      patientRequestDTO = new PatientRequestDTO();
      patientRequestDTO.setNom("Ayoub");
      patientRequestDTO.setPrenom("Hadi");
      patientRequestDTO.setEmail("AyoubHadi@email.com");
      patientRequestDTO.setTelephone("0603430001");

      savedPatient = patientService.ajouterPatient(patientRequestDTO);

      dossierMedicalRequestDTO.setPatientId(savedPatient.getId());

  }

  @Test
  void shouldAjouterDossierMedical(){
      DossierMedicalResponseDTO saveDossier = dossierMedicalService.ajouterUnDossierMedical(dossierMedicalRequestDTO);
      assertEquals(savedPatient.getId(),saveDossier.getPatient().getId());

  }

  @Test
  void shouldAjouterDiagnostic(){
      DossierMedicalResponseDTO saveDossier = dossierMedicalService.ajouterUnDossierMedical(dossierMedicalRequestDTO);
      DossierMedicalResponseDTO saveDiagnostic = dossierMedicalService.AjouterDiagnostic(saveDossier.getPatient().getId(),"cancer");
      assertNotNull(saveDiagnostic);
      assertEquals("cancer",saveDiagnostic.getDiagnostic());
  }

  @Test
  void shouldAjouterObservation(){
      DossierMedicalResponseDTO saveDossier = dossierMedicalService.ajouterUnDossierMedical(dossierMedicalRequestDTO);
      DossierMedicalResponseDTO saveObservation = dossierMedicalService.AjouterObservation(saveDossier.getPatient().getId(),"stable");
      assertNotNull(saveObservation);
      assertEquals("stable",saveObservation.getObservations());
  }

}