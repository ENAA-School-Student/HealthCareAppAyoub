package com.example.HealthCare.service;

import com.example.HealthCare.dto.PatientRequestDTO;
import com.example.HealthCare.dto.PatientResponseDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class PatientServiceTest {
    @Autowired
    private PatientService patientService;
    PatientRequestDTO dto = new PatientRequestDTO();

    @Test
    void ajouterPatient() {
        PatientRequestDTO dto = new PatientRequestDTO();
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
    void modefierPatient(){
        PatientResponseDTO save = patientService.ajouterPatient(dto);
        PatientRequestDTO patientRequestDTO = new PatientRequestDTO();
        patientRequestDTO.setNom("Ayoub");
        patientRequestDTO.setPrenom("Hadi");
        patientRequestDTO.setEmail("AyoubHadi@gmail.com");
        patientRequestDTO.setTelephone("037477332");
        patientRequestDTO.setDateNaissance(LocalDate.of(2000,02,22));

        PatientResponseDTO modefier = patientService.modifierpatient(save.getId(),patientRequestDTO);

        assertNotNull(modefier);
        assertEquals("Ayoub", modefier.getNom());
        assertEquals("Hadi", modefier.getPrenom());
        assertEquals("AyoubHadi@gmail.com", modefier.getEmail());
        assertEquals(save.getId(), modefier.getId());


    }

}