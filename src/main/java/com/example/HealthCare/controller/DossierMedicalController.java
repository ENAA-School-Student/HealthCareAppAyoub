package com.example.HealthCare.controller;

import com.example.HealthCare.dto.DossierMedicalRequestDTO;
import com.example.HealthCare.dto.DossierMedicalResponseDTO;
import com.example.HealthCare.service.DossierMedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@RestController
@RequestMapping("/DossierMedical")
public class DossierMedicalController {
    @Autowired
    public DossierMedicalService dossierMedicalService;

    @PostMapping("/ajouterDossierMedical")
    public ResponseEntity<DossierMedicalResponseDTO> ajouterDossierMedical(@RequestBody  DossierMedicalRequestDTO dossierMedicalRequestDTO){
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

    @GetMapping("/getAllDossierMedical")
    public ResponseEntity<Page<DossierMedicalResponseDTO>> getDossieMedicalPagination(
            @RequestParam (defaultValue = "1") int pageNUmber,
            @RequestParam (defaultValue = "5") int pageSize,
            @RequestParam (defaultValue = "dateCreation") String sortBy,
            @RequestParam (defaultValue = "desc") String sortDer

    ){
        Sort sort = sortDer.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Page<DossierMedicalResponseDTO> rs = dossierMedicalService.getDossierMedical(PageRequest.of(pageNUmber-1,pageSize,sort));
        return ResponseEntity.ok(rs);
    }


    @GetMapping("getDossierMedicalParDiagnostic")
    public ResponseEntity<Page<DossierMedicalResponseDTO>> getDossierMedicalParDiagnostic(
            @RequestParam String diagnostic,
            @RequestParam (defaultValue = "1") int pageNUmber,
            @RequestParam (defaultValue = "5") int pageSize,
            @RequestParam (defaultValue = "id") String sortBy,
            @RequestParam (defaultValue = "asc") String sortDer    ){

        Sort sort = sortDer.equalsIgnoreCase("asc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Page<DossierMedicalResponseDTO> rs = dossierMedicalService.getDossierMedicalParDiagnostic(diagnostic,PageRequest.of(pageNUmber-1,pageSize,sort));
        return  ResponseEntity.ok(rs);
    }




//    @GetMapping("/dossierWithPatietns")
//    public ResponseEntity<List<DossierMedicalResponseDTO>> getAllPatientifoesFromDossier(){
//        return ResponseEntity.ok(dossierMedicalService.getDossierMedecalWithPatietnInfoes());
//    }



}
