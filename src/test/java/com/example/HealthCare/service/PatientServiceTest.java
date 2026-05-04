package com.example.HealthCare.service;

import com.example.HealthCare.dto.PatientRequestDTO;
import com.example.HealthCare.dto.PatientResponseDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class PatientServiceTest {
    @Autowired
    private PatientService patientService;
     PatientRequestDTO patientRequestDTO;
     PatientRequestDTO secondPatientRequestDTO;

        @BeforeEach
        void setUp(){
            patientRequestDTO = new PatientRequestDTO();
            patientRequestDTO.setNom("Ali");
            patientRequestDTO.setPrenom("sung");
            patientRequestDTO.setEmail("alisung@gmail.com");
            patientRequestDTO.setTelephone("039394848");
            patientRequestDTO.setDateNaissance(LocalDate.of(2003,02,20));

            secondPatientRequestDTO = new PatientRequestDTO();
            secondPatientRequestDTO.setNom("Oussama");
            secondPatientRequestDTO.setPrenom("sung");
            secondPatientRequestDTO.setEmail("oussamasung@gmail.com");
            secondPatientRequestDTO.setTelephone("0697394848");
            secondPatientRequestDTO.setDateNaissance(LocalDate.of(2001,03,30));



    }

        @Test
        void shouldAjouterPatient() {
            PatientResponseDTO result = patientService.ajouterPatient(patientRequestDTO);
            assertNotNull(result.getId());
            assertEquals("Ali", result.getNom());
        }

    @Test
    void shouldModefierPatient() {
        PatientResponseDTO save = patientService.ajouterPatient(patientRequestDTO);
        PatientRequestDTO patientRequestDTO = new PatientRequestDTO();
        patientRequestDTO.setNom("Ayoub");
        patientRequestDTO.setPrenom("Hadi");
        patientRequestDTO.setEmail("AyoubHadi@gmail.com");
        patientRequestDTO.setTelephone("037477332");
        patientRequestDTO.setDateNaissance(LocalDate.of(2000, 02, 22));

        PatientResponseDTO modefier = patientService.modifierpatient(save.getId(), patientRequestDTO);

        assertNotNull(modefier);
        assertEquals("Ayoub", modefier.getNom());
        assertEquals("Hadi", modefier.getPrenom());
        assertEquals("AyoubHadi@gmail.com", modefier.getEmail());

        assertEquals(save.getId(), modefier.getId());
    }

        @Test
        void shouldObtenirTousLesPatients() {
                patientService.ajouterPatient(patientRequestDTO);
                patientService.ajouterPatient(secondPatientRequestDTO);

                List<PatientResponseDTO> rs = patientService.obtenirTousLesPatients();
                assertNotNull(rs);
                assertFalse(rs.isEmpty());
        }

    @Test
    void shouldsupprimerPatinet() {
        PatientResponseDTO save = patientService.ajouterPatient(patientRequestDTO);
        boolean isDeleted =  patientService.supprimerPatinet(save.getId());
        assertTrue(isDeleted);
    }

    @Test
    void shouldConsulterPatient(){
        PatientResponseDTO save = patientService.ajouterPatient(patientRequestDTO);
        PatientResponseDTO rs = patientService.consulterPatient(save.getId());
        assertNotNull(rs);
        assertEquals(save.getId(),rs.getId());
    }




}