package com.example.HealthCare.controller;

import com.example.HealthCare.dto.PatientRequestDTO;
import com.example.HealthCare.dto.PatientResponseDTO;
import com.example.HealthCare.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/ajouterPatient")
    public ResponseEntity<PatientResponseDTO> ajouterPatient(@RequestBody @Valid PatientRequestDTO dto){
        PatientResponseDTO savePatient = patientService.ajouterPatient(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savePatient);
    }
    @GetMapping("/obtenirTousLesPatients")
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
    public ResponseEntity<Page<PatientResponseDTO>> getPatientsParPagination(Pageable pageable){
        {
            Page<PatientResponseDTO> rs = patientService.obtenirTousLesPatientsParPagenation(pageable);
            return ResponseEntity.ok(rs);
        }
    }
    @GetMapping("/searchPatientParNom")
    public ResponseEntity<Page<PatientResponseDTO>> rechercherParNom(
            @RequestParam String nom,
            @RequestParam (defaultValue = "1") int pageNumber,
            @RequestParam (defaultValue = "5") int pageSize,
            @RequestParam (defaultValue = "nom") String sortBy,
            @RequestParam (defaultValue = "asc") String  sortDir
            ){
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber-1,pageSize,sort);
        Page<PatientResponseDTO> rs = patientService.rechercherParNom(nom,pageable);
        return ResponseEntity.ok(rs);
    }

}
