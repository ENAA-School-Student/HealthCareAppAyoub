package com.example.HealthCare.service;

import com.example.HealthCare.dto.MedecinRequestDTO;
import com.example.HealthCare.dto.MedecinResponseDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;


import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@ExtendWith(MockitoExtension.class)
class MedecinServiceTest {

    @Mock
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
    lenient().when(medecinService.ajouterMedecin(any()))
            .thenReturn(saveMedecin);
    MedecinResponseDTO save = medecinService.ajouterMedecin(medecinRequestDTO);
    assertNotNull(save);
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
    void shouldSupprimerMedecine(){
       MedecinResponseDTO save= medecinService.ajouterMedecin(medecinRequestDTO);
       boolean isDeleted = medecinService.supprimerMedecine(save.getId());
       assertTrue(isDeleted);
    }



}