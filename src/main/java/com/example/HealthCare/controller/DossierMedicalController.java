package com.example.HealthCare.controller;

import com.example.HealthCare.model.dto.DossierMedicalAjouterDTO;
import com.example.HealthCare.model.dto.DossierMedicalReturnDTO;
import com.example.HealthCare.service.DossierMedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/DossierMedical")
public class DossierMedicalController {
    @Autowired
    public DossierMedicalService dossierMedicalService;

    @PostMapping
    public ResponseEntity<DossierMedicalReturnDTO> ajouterDossierMedical(DossierMedicalAjouterDTO dossierMedicalAjouterDTO){
        DossierMedicalReturnDTO save = dossierMedicalService.ajouterUnDossierMedical(dossierMedicalAjouterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }
    @PutMapping("/{patientId}/diagnostic")
    public ResponseEntity<DossierMedicalReturnDTO> ajouterDiagnostic(@PathVariable int id, @RequestBody String diagnostic){
        DossierMedicalReturnDTO rs = dossierMedicalService.AjouterDiagnostic(id, diagnostic);
        return ResponseEntity.ok(rs);
    }
    @PutMapping("/{patientId}/observation")
    public ResponseEntity<DossierMedicalReturnDTO>  ajouterObservation(@PathVariable int id , @RequestBody String observation){
        DossierMedicalReturnDTO rs = dossierMedicalService.AjouterObservation(id, observation);
        return ResponseEntity.ok(rs);
    }
    @GetMapping("/DossierMedical/{id}")
    public ResponseEntity<DossierMedicalReturnDTO> Consulterdossielmedical(@PathVariable int id){
        DossierMedicalReturnDTO rs = dossierMedicalService.Consulterdossielmedical(id);
        return ResponseEntity.ok(rs);
    }



}
