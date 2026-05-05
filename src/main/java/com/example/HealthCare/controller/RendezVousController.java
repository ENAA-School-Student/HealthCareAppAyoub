package com.example.HealthCare.controller;

import com.example.HealthCare.dto.RendezVousRequestDTO;
import com.example.HealthCare.dto.RendezVousResponseDTO;
import com.example.HealthCare.repository.RendezVousRepository;
import com.example.HealthCare.service.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/RendezVous")
public class RendezVousController
{
    @Autowired
    private RendezVousService rendezVousService;

    @PostMapping
    public ResponseEntity<RendezVousResponseDTO> ajouterRendezVous(@RequestBody RendezVousRequestDTO rendezVousRequestDTO){
        RendezVousResponseDTO saveRendezVous = rendezVousService.ajouterRendezVous(rendezVousRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveRendezVous);
    }
    @PutMapping("/modifier/{id}")
    public ResponseEntity<RendezVousResponseDTO> modifierRendezVous(@PathVariable long id, @RequestBody RendezVousRequestDTO rendezVousRequestDTO){
        RendezVousResponseDTO rs = rendezVousService.modifieRendezVous(id, rendezVousRequestDTO);
        return ResponseEntity.ok(rs);
    }
    @PutMapping("/annulerRendezVous/{id}")
    public ResponseEntity<RendezVousResponseDTO> annulerRendezVous(@PathVariable long id){
        RendezVousResponseDTO rs = rendezVousService.AnnulerRendezVous(id);
        return ResponseEntity.ok(rs);
    }
    @GetMapping
    public ResponseEntity<List<RendezVousResponseDTO>> obtenirTousLesRendezVous(){
      List<RendezVousResponseDTO>   rs = rendezVousService.obtenirTousLesRendezVous();
      return  ResponseEntity.ok(rs);
    }

    @GetMapping("/medecine/{id}")
    public ResponseEntity<List<RendezVousResponseDTO>> rechercherRendezVousParMedecine(@PathVariable long id){
        List<RendezVousResponseDTO> rs = rendezVousService.rechercherParMedecine(id);
        return ResponseEntity.ok(rs);
    }
    @GetMapping("/patient/{id}")
    public ResponseEntity<List<RendezVousResponseDTO>> rechercherRendezVousParPatient(@PathVariable long id){
        List<RendezVousResponseDTO> rs = rendezVousService.rechercherParPatient(id);
        return ResponseEntity.ok(rs);
    }
    @GetMapping("/getALlrendezVous")
    public ResponseEntity<List<RendezVousResponseDTO>> getAllPatietnRendzeVous(){
        return ResponseEntity.ok(rendezVousService.getallPatientRendzeVous());
    }


}
