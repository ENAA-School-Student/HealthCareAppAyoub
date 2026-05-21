package com.example.HealthCare.controller;

import com.example.HealthCare.dto.MedecinRequestDTO;
import com.example.HealthCare.dto.MedecinResponseDTO;
import com.example.HealthCare.service.MedecinService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medecine")
public class MedecinController {
    @Autowired
    MedecinService medecinService;

    @PostMapping
    public ResponseEntity<MedecinResponseDTO> ajouterMedecin(@RequestBody @Valid MedecinRequestDTO medecinRequestDTO) {
        MedecinResponseDTO saveMedecin = medecinService.ajouterMedecin(medecinRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveMedecin);
    }

    @GetMapping("/getMedecineAtOnce")
    public ResponseEntity<List<MedecinResponseDTO>> obtenirTousLesMedecin() {
      List<MedecinResponseDTO> rs= medecinService.obtenirTousLesMedecin();
       return ResponseEntity.ok(rs);
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<MedecinResponseDTO> modifierMedecine(@PathVariable @Valid long id , @RequestBody MedecinRequestDTO medecinRequestDTO){
        MedecinResponseDTO rs = medecinService.modifieMedeceine(id, medecinRequestDTO);
        return ResponseEntity.ok(rs);
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Void> supprimerMedecine( @PathVariable long id){
        medecinService.supprimerMedecine(id);
        return ResponseEntity.noContent().build();
    }


}