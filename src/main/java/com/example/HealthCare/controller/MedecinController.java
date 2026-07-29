package com.example.HealthCare.controller;

import com.example.HealthCare.dto.MedecinRequestDTO;
import com.example.HealthCare.dto.MedecinResponseDTO;
import com.example.HealthCare.dto.PatientResponseDTO;
import com.example.HealthCare.service.MedecinService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medecine")
@RequiredArgsConstructor
public class MedecinController {

    private final MedecinService medecinService;
    @PostMapping("/ajouterMedecin")
    public ResponseEntity<MedecinResponseDTO> ajouterMedecin(@RequestBody @Valid MedecinRequestDTO medecinRequestDTO) {
        MedecinResponseDTO saveMedecin = medecinService.ajouterMedecin(medecinRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveMedecin);
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


    @GetMapping("/getMedecinePagination")
    public ResponseEntity<Page<MedecinResponseDTO>> obtenirTousLesMedecinPagination(
            @RequestParam( defaultValue = "1") int pageNumber ,
            @RequestParam( defaultValue = "5") int pageSize,
            @RequestParam (defaultValue = "nom") String sortBy,
            @RequestParam (defaultValue = "asc")String sortDir )
    {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        {
            Page<MedecinResponseDTO> rs= medecinService.obtenirTousLesMedecinPagination(
                    PageRequest.of(pageNumber-1,pageSize,sort));
            return ResponseEntity.ok(rs);
        }
    }

    @GetMapping("/searchMedecinParSpecialite")
    public ResponseEntity<Page<MedecinResponseDTO>> rechercherParSpecialite(
            @RequestParam (required = false) String specialite,
            @RequestParam (defaultValue = "1") int pageNumber,
            @RequestParam (defaultValue = "5") int pageSize,
            @RequestParam (defaultValue = "specialite") String sortBy,
            @RequestParam (defaultValue = "asc") String  sortDir
    ){
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber-1,pageSize,sort);
        Page<MedecinResponseDTO> rs = medecinService.rechercherParSpecialite(specialite,pageable);
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/count")
       public ResponseEntity<Long> totalMedecine(){
           return ResponseEntity.ok(medecinService.getTotalMedecine());
        }

}