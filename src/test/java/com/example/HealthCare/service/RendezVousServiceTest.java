package com.example.HealthCare.service;

import com.example.HealthCare.dto.RendezVousRequestDTO;
import com.example.HealthCare.dto.RendezVousResponseDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RendezVousServiceTest {
    @Autowired
    private RendezVousService rendezVousService;
    RendezVousRequestDTO rendezVousRequestDTO = new RendezVousRequestDTO();
    @Test
    void shouldAjouterRenndezvous(){
        rendezVousRequestDTO.setDateRendezVous(LocalDate.of(2020,02,22));
        rendezVousRequestDTO.setStatut("occupaied");
        rendezVousRequestDTO.setMedecinId(2L);
        rendezVousRequestDTO.setPatientId(3L);

        RendezVousResponseDTO rs = rendezVousService.ajouterRendezVous(rendezVousRequestDTO);
        assertNotNull(rs.getId());
        assertEquals("occupaied", rs.getStatut());

    }

    @Test
    void annulerRendezVous() {
        RendezVousResponseDTO save = rendezVousService.ajouterRendezVous(rendezVousRequestDTO);
        rendezVousService.AnnulerRendezVous(save.getId());

        assertThrows(RuntimeException.class,
                () -> rendezVousService.AnnulerRendezVous(save.getId())
        );
    }
}