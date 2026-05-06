package com.example.HealthCare.controller;

import com.example.HealthCare.dto.DossierMedicalRequestDTO;
import com.example.HealthCare.dto.DossierMedicalResponseDTO;
import com.example.HealthCare.service.DossierMedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/DossierMedical")
public class DossierMedicalController {
    @Autowired
    public DossierMedicalService dossierMedicalService;

    @PostMapping
    public ResponseEntity<DossierMedicalResponseDTO> ajouterDossierMedical(DossierMedicalRequestDTO dossierMedicalRequestDTO){
        DossierMedicalResponseDTO save = dossierMedicalService.ajouterUnDossierMedical(dossierMedicalRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }
    @PutMapping("/{patientId}/diagnostic")
    public ResponseEntity<DossierMedicalResponseDTO> ajouterDiagnostic(@PathVariable long id, @RequestBody String diagnostic){
        DossierMedicalResponseDTO rs = dossierMedicalService.AjouterDiagnostic(id, diagnostic);
        return ResponseEntity.ok(rs);
    }
    @PutMapping("/{patientId}/observation")
    public ResponseEntity<DossierMedicalResponseDTO>  ajouterObservation(@PathVariable long id , @RequestBody String observation){
        DossierMedicalResponseDTO rs = dossierMedicalService.AjouterObservation(id, observation);
        return ResponseEntity.ok(rs);
    }
    @GetMapping("/DossierMedical/{id}")
    public ResponseEntity<DossierMedicalResponseDTO> Consulterdossielmedical(@PathVariable long id){
        DossierMedicalResponseDTO rs = dossierMedicalService.Consulterdossielmedical(id);
        return ResponseEntity.ok(rs);
    }
//    @GetMapping("/dossierWithPatietns")
//    public ResponseEntity<List<DossierMedicalResponseDTO>> getAllPatientifoesFromDossier(){
//        return ResponseEntity.ok(dossierMedicalService.getDossierMedecalWithPatietnInfoes());
//    }



}
