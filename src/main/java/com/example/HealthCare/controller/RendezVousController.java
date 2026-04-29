package com.example.HealthCare.controller;

import com.example.HealthCare.model.dto.RendezVousAjouterDTO;
import com.example.HealthCare.model.dto.RendezVousReturnDTO;
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
    public ResponseEntity<RendezVousReturnDTO> ajouterRendezVous(RendezVousAjouterDTO rendezVousAjouterDTO){
        RendezVousReturnDTO saveRendezVous = rendezVousService.ajouterRendezVous(rendezVousAjouterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveRendezVous);
    }
    @PutMapping("/modifier/{id}")
    public ResponseEntity<RendezVousReturnDTO> modifierRendezVous(@PathVariable long id, @RequestBody RendezVousAjouterDTO rendezVousAjouterDTO){
        RendezVousReturnDTO rs = rendezVousService.modifieRendezVous(id,rendezVousAjouterDTO);
        return ResponseEntity.ok(rs);
    }
    @PutMapping("/annulerRendezVous/{id}")
    public ResponseEntity<RendezVousReturnDTO> annulerRendezVous(@PathVariable long id){
        RendezVousReturnDTO rs = rendezVousService.AnnulerRendezVous(id);
        return ResponseEntity.ok(rs);
    }
    @GetMapping
    public ResponseEntity<List<RendezVousReturnDTO>> obtenirTousLesRendezVous(){
      List<RendezVousReturnDTO>   rs = rendezVousService.obtenirTousLesRendezVous();
      return  ResponseEntity.ok(rs);
    }

    @GetMapping("/medecine/{id}")
    public ResponseEntity<List<RendezVousReturnDTO>> rechercherRendezVousParMedecine(@PathVariable int id){
        List<RendezVousReturnDTO> rs = rendezVousService.rechercherParMedecine(id);
        return ResponseEntity.ok(rs);
    }
    @GetMapping("/patient/{id}")
    public ResponseEntity<List<RendezVousReturnDTO>> rechercherRendezVousParPatient(@PathVariable int id){
        List<RendezVousReturnDTO> rs = rendezVousService.rechercherParPatient(id);
        return ResponseEntity.ok(rs);
    }

}
