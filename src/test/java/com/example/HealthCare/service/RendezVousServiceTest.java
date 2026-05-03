    package com.example.HealthCare.service;

    import com.example.HealthCare.dto.*;
    import com.example.HealthCare.enums.Statut;
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
    class RendezVousServiceTest {
        @Autowired
        private MedecinService medecinService;
        @Autowired
        private PatientService patientService;
        @Autowired
        private RendezVousService rendezVousService;

        RendezVousRequestDTO rendezVousRequestDTO ;
        PatientRequestDTO patientRequestDTO ;
        MedecinRequestDTO medecinRequestDTO ;

        PatientResponseDTO savedPatient;
        MedecinResponseDTO savedMedecin;

        @BeforeEach
        void setUp() {
            patientRequestDTO = new PatientRequestDTO();
            patientRequestDTO.setNom("Ayoub");
            patientRequestDTO.setPrenom("Hadi");
            patientRequestDTO.setEmail("AyoubHadi@email.com");
            patientRequestDTO.setTelephone("0603430001");

            medecinRequestDTO = new MedecinRequestDTO();
            medecinRequestDTO.setNom("Sami");
            medecinRequestDTO.setEmail("sami@email.com");
            medecinRequestDTO.setTelephone("063430002");
            medecinRequestDTO.setSpecialite("Cardiologue");

            rendezVousRequestDTO = new RendezVousRequestDTO();
            rendezVousRequestDTO.setStatut(Statut.EN_ATTENTE);
            rendezVousRequestDTO.setDateRendezVous(LocalDate.of(2025, 6, 10));


            savedPatient = patientService.ajouterPatient(patientRequestDTO);
            savedMedecin = medecinService.ajouterMedecin(medecinRequestDTO);

            rendezVousRequestDTO.setPatientId(savedPatient.getId());
            rendezVousRequestDTO.setMedecinId(savedMedecin.getId());
        }


        @Test
        void shouldAjouterRenndezvous(){
            rendezVousRequestDTO.setStatut(Statut.EN_ATTENTE);
            RendezVousResponseDTO rs = rendezVousService.ajouterRendezVous(rendezVousRequestDTO);
            assertEquals(savedPatient.getId(), rs.getPatient().getId());
        }
        @Test
        void shouldRechercherParPatient(){
            RendezVousResponseDTO saved = rendezVousService.ajouterRendezVous(rendezVousRequestDTO);
            List<RendezVousResponseDTO> rs = rendezVousService.rechercherParPatient(saved.getPatient().getId());
            assertNotNull(rs);
            assertFalse(rs.isEmpty());
        }
        @Test
        void shouldRechercherParMedecin(){
            RendezVousResponseDTO saved =rendezVousService.ajouterRendezVous(rendezVousRequestDTO);
            List<RendezVousResponseDTO> rs = rendezVousService.rechercherParMedecine(saved.getMedecine().getId());
            assertNotNull(rs);
            assertFalse(rs.isEmpty());
        }

        @Test
        void shouldAnnulerRendezVous() {
            RendezVousResponseDTO save = rendezVousService.ajouterRendezVous(rendezVousRequestDTO);
            RendezVousResponseDTO rs = rendezVousService.AnnulerRendezVous(save.getId());
            assertEquals(Statut.ANNULE,rs.getStatut());
        }
    }