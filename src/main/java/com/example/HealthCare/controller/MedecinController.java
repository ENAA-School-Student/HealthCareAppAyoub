package com.example.HealthCare.controller;

import com.example.HealthCare.mapper.MedecinMapper;
import com.example.HealthCare.model.dto.MedecinAjouterDTO;
import com.example.HealthCare.model.dto.MedecinReturnDTO;
import com.example.HealthCare.model.dto.PatientAjouterDTO;
import com.example.HealthCare.model.dto.PatientReturnDTO;
import com.example.HealthCare.repository.MedecinRepository;
import com.example.HealthCare.service.MedecinService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medecine")
public class MedecinController {
    @Autowired
    MedecinService medecinService;
    @Autowired
    MedecinRepository medecinRepository;
    @Autowired
    MedecinMapper medecinMapper;

    @PostMapping
    public ResponseEntity<MedecinReturnDTO> ajouterMedecin(@RequestBody @Valid MedecinAjouterDTO medecinAjouterDTO){
        MedecinReturnDTO saveMedecin = medecinService.ajouterMedecin(medecinAjouterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveMedecin);
    }


}
