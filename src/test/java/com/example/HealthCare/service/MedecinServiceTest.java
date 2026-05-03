package com.example.HealthCare.service;

import com.example.HealthCare.dto.MedecinRequestDTO;
import com.example.HealthCare.dto.MedecinResponseDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class MedecinServiceTest {
    @Autowired
    private  MedecinService medecinService;
    MedecinRequestDTO medecinRequestDTO;
    MedecinResponseDTO saveMedecin;
    @BeforeEach
    void setUp(){
        medecinRequestDTO = new MedecinRequestDTO();
        medecinRequestDTO.setNom("Ayoub");
        medecinRequestDTO.setEmail("AyoubHadi@gmail.com");
        medecinRequestDTO.setTelephone("0603430001");
        medecinRequestDTO.setSpecialite("doctor");

    }

@Test
void shouldAjouterMedecin(){
        MedecinResponseDTO save = medecinService.ajouterMedecin(medecinRequestDTO);
        assertEquals("Ayoub",save.getNom());
}

    @Test
    void modifieMedeceine() {
        MedecinResponseDTO save = medecinService.ajouterMedecin(medecinRequestDTO);
        MedecinRequestDTO newupdate= new MedecinRequestDTO();
        newupdate.setNom("Souhayb");
        newupdate.setEmail("Souhayb@gmail.com");
        newupdate.setSpecialite("doctor");
        newupdate.setTelephone("0604535435");
        MedecinResponseDTO medecinResponseDTO = medecinService.modifieMedeceine(save.getId(),newupdate);
        assertEquals("Souhayb",medecinResponseDTO.getNom());
        assertEquals("Souhayb@gmail.com",medecinResponseDTO.getEmail());

    }

}