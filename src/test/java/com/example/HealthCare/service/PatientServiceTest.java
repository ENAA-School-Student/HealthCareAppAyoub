package com.example.HealthCare.service;

import com.example.HealthCare.model.dto.PatientAjouterDTO;
import com.example.HealthCare.model.dto.PatientReturnDTO;
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

    @Test
    void ajouterPatient() {
        PatientAjouterDTO dto = new PatientAjouterDTO();
        dto.setNom("Dupont");
        dto.setPrenom("Jean");
        dto.setEmail("jean@email.com");
        dto.setTelephone("0600000001");
        dto.setDateNaissance(LocalDate.of(1990, 1, 15));
        PatientReturnDTO result = patientService.ajouterPatient(dto);

        assertNotNull(result.getId());
        assertEquals("Dupont", result.getNom());
    }

}