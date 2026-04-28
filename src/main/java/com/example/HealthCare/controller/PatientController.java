package com.example.HealthCare.controller;

import com.example.HealthCare.model.dto.PatientAjouterDTO;
import com.example.HealthCare.model.dto.PatientReturnDTO;
import com.example.HealthCare.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientReturnDTO> ajouterPatient(@RequestBody @Valid PatientAjouterDTO dto){
        PatientReturnDTO savePatient = patientService.ajouterPatient(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savePatient);
    }
    @GetMapping
    public ResponseEntity<List<PatientReturnDTO>> obtenirTousLesPatients(){
    List<PatientReturnDTO> rs = patientService.obtenirTousLesPatients();
    return ResponseEntity.ok(rs);
    }
    @PutMapping("/modifier/{id}")
    public ResponseEntity<PatientReturnDTO> modifierpatient(@PathVariable long id , @RequestBody PatientAjouterDTO patientAjouterDTO){
        PatientReturnDTO rs = patientService.modifierpatient(id,patientAjouterDTO);
        return ResponseEntity.ok(rs);
    }
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Void> supprimerPatient(@PathVariable long id){
        patientService.supprimerPatinet(id);
      return  ResponseEntity.noContent().build();
    }
}
