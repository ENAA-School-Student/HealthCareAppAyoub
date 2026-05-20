package com.example.HealthCare.controller;

import com.example.HealthCare.dto.PatientRequestDTO;
import com.example.HealthCare.dto.PatientResponseDTO;
import com.example.HealthCare.service.PatientService;
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
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponseDTO> ajouterPatient(@RequestBody @Valid PatientRequestDTO dto){
        PatientResponseDTO savePatient = patientService.ajouterPatient(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savePatient);
    }
    @GetMapping("/getAllPatietnAtOnce")
    public ResponseEntity<List<PatientResponseDTO>> obtenirTousLesPatients(){
    List<PatientResponseDTO> rs = patientService.obtenirTousLesPatients();
    return ResponseEntity.ok(rs);
    }
    @PutMapping("/modifier/{id}")
    public ResponseEntity<PatientResponseDTO> modifierpatient(@PathVariable long id , @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDTO rs = patientService.modifierpatient(id, patientRequestDTO);
        return ResponseEntity.ok(rs);
    }
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Void> supprimerPatient(@PathVariable long id){
        patientService.supprimerPatinet(id);
      return  ResponseEntity.noContent().build();
    }
    @GetMapping("/consulterPatient/{id}")
    public ResponseEntity<PatientResponseDTO> consulterPatient(@PathVariable long id){
     PatientResponseDTO rs= patientService.consulterPatient(id);
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/getPatientsPagination")
    public ResponseEntity<Page<PatientResponseDTO>> getPatientsParPagination(
            @RequestParam (defaultValue = "1") int pageNumber,
            @RequestParam (defaultValue = "5") int pageSize,
            @RequestParam (defaultValue = "nom") String sortBy,
            @RequestParam (defaultValue = "asc")String sortDir
    ){
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
    {
        Page<PatientResponseDTO> rs = patientService.obtenirTousLesPatientsParPagenation
                (PageRequest.of(pageNumber-1,pageSize,sort));
        return ResponseEntity.ok(rs);
    }
}}
