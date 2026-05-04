package com.example.HealthCare.service;

import com.example.HealthCare.dto.MedecinRequestDTO;
import com.example.HealthCare.dto.MedecinResponseDTO;
import jakarta.transaction.Transactional;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class MedecinServiceTest {
    @Autowired
    private  MedecinService medecinService;
    MedecinRequestDTO medecinRequestDTO;
    MedecinRequestDTO secondMedecinRequest;
    MedecinResponseDTO saveMedecin;
    @BeforeEach
    void setUp(){
        medecinRequestDTO = new MedecinRequestDTO();
        medecinRequestDTO.setNom("Ayoub");
        medecinRequestDTO.setEmail("AyoubHadi@gmail.com");
        medecinRequestDTO.setTelephone("0603430001");
        medecinRequestDTO.setSpecialite("doctor");

        saveMedecin = medecinService.ajouterMedecin(medecinRequestDTO);

        secondMedecinRequest = new MedecinRequestDTO();
        secondMedecinRequest.setNom("Ayoub");
        secondMedecinRequest.setEmail("AyoubHadi@gmail.com");
        secondMedecinRequest.setTelephone("0603430001");
        secondMedecinRequest.setSpecialite("doctor");

    }

@Test
void shouldAjouterMedecin(){
        MedecinResponseDTO save = medecinService.ajouterMedecin(medecinRequestDTO);
        assertEquals("Ayoub",save.getNom());
}

    @Test
    void modifieMedeceine() {
        MedecinResponseDTO save = medecinService.ajouterMedecin(medecinRequestDTO);
        MedecinRequestDTO newUpdate= new MedecinRequestDTO();
        newUpdate.setNom("Souhayb");
        newUpdate.setEmail("Souhayb@gmail.com");
        newUpdate.setSpecialite("doctor");
        newUpdate.setTelephone("0604535435");
        MedecinResponseDTO medecinResponseDTO = medecinService.modifieMedeceine(save.getId(),newUpdate);
        assertEquals("Souhayb",medecinResponseDTO.getNom());
        assertEquals("Souhayb@gmail.com",medecinResponseDTO.getEmail());
    }
    @Test
    void shouldObtenirTousLesMedecin(){
            medecinService.ajouterMedecin(medecinRequestDTO);
            medecinService.ajouterMedecin(secondMedecinRequest);
        List<MedecinResponseDTO> rs = medecinService.obtenirTousLesMedecin();

        assertNotNull(rs);
        assertTrue(rs.size()>1);

    }
    @Test
    void shouldSupprimerMedecine(){
       MedecinResponseDTO save= medecinService.ajouterMedecin(medecinRequestDTO);
       boolean isDeleted = medecinService.supprimerMedecine(save.getId());
       assertTrue(isDeleted);
    }



}