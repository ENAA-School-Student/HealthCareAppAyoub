package com.example.HealthCare.service;

import com.example.HealthCare.dto.PatientRequestDTO;
import com.example.HealthCare.dto.PatientResponseDTO;
import jakarta.transaction.Transactional;
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
        PatientRequestDTO dto = new PatientRequestDTO();

        @Test
        void shouldAjouterPatient() {
            dto.setNom("Dupont");
            dto.setPrenom("Jean");
            dto.setEmail("jean@email.com");
            dto.setTelephone("0600000001");
            dto.setDateNaissance(LocalDate.of(1990, 1, 15));
            PatientResponseDTO result = patientService.ajouterPatient(dto);

            assertNotNull(result.getId());
            assertEquals("Dupont", result.getNom());
        }

    @Test
    void shouldModefierPatient() {
        PatientResponseDTO save = patientService.ajouterPatient(dto);
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
        List<PatientResponseDTO> rs = patientService.obtenirTousLesPatients();
        assertNotNull(rs);
        assertFalse(rs.isEmpty());
    }

    @Test
    void shouldsupprimerPatinet() {
        PatientResponseDTO save = patientService.ajouterPatient(dto);
        patientService.supprimerPatinet(save.getId());
        assertThrows(RuntimeException.class, () -> patientService.consulterPatient(save.getId()));

    }
    @Test
    void shouldConsulterPatient(){
        PatientResponseDTO save = patientService.ajouterPatient(dto);
        PatientResponseDTO rs = patientService.consulterPatient(save.getId());
        assertNotNull(rs);
        assertEquals(save.getId(),rs.getId());
    }




}