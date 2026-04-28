package com.example.HealthCare.controller;

import com.example.HealthCare.model.dto.MedecinAjouterDTO;
import com.example.HealthCare.model.dto.MedecinReturnDTO;
import com.example.HealthCare.service.MedecinService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medecine")
public class MedecinController {
    @Autowired
    MedecinService medecinService;

    @PostMapping
    public ResponseEntity<MedecinReturnDTO> ajouterMedecin(@RequestBody @Valid MedecinAjouterDTO medecinAjouterDTO) {
        MedecinReturnDTO saveMedecin = medecinService.ajouterMedecin(medecinAjouterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveMedecin);
    }

    @GetMapping
    public ResponseEntity<List<MedecinReturnDTO>> obtenirTousLesMedecin() {
      List<MedecinReturnDTO> rs= medecinService.obtenirTousLesMedecin();
       return ResponseEntity.ok(rs);
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<MedecinReturnDTO> modifierMedecine(@PathVariable long id , @RequestBody MedecinAjouterDTO medecinAjouterDTO){
        MedecinReturnDTO rs = medecinService.modifieMedeceine(id, medecinAjouterDTO);
        return ResponseEntity.ok(rs);
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Void> supprimerMedecine( @PathVariable long id){
        medecinService.supprimerMedecine(id);
        return ResponseEntity.noContent().build();
    }
}