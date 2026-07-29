package com.example.HealthCare.controller;

import com.example.HealthCare.dto.MedecinResponseDTO;
import com.example.HealthCare.dto.RendezVousRequestDTO;
import com.example.HealthCare.dto.RendezVousResponseDTO;
import com.example.HealthCare.enums.Statut;
import com.example.HealthCare.service.RendezVousService;
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
@RequiredArgsConstructor
@RequestMapping("/api/RendezVous")
public class RendezVousController {

    private final RendezVousService rendezVousService;

    @PostMapping("/ajouterRendezVous")
    public ResponseEntity<RendezVousResponseDTO> ajouterRendezVous(@RequestBody RendezVousRequestDTO rendezVousRequestDTO) {
        RendezVousResponseDTO saveRendezVous = rendezVousService.ajouterRendezVous(rendezVousRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveRendezVous);
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<RendezVousResponseDTO> modifierRendezVous(@PathVariable long id, @RequestBody RendezVousRequestDTO rendezVousRequestDTO) {
        RendezVousResponseDTO rs = rendezVousService.modifieRendezVous(id, rendezVousRequestDTO);
        return ResponseEntity.ok(rs);
    }

    @PutMapping("/annulerRendezVous/{id}")
    public ResponseEntity<RendezVousResponseDTO> annulerRendezVous(@PathVariable long id) {
        RendezVousResponseDTO rs = rendezVousService.AnnulerRendezVous(id);
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/obtenirTousLesRendezVous")
    public ResponseEntity<List<RendezVousResponseDTO>> obtenirTousLesRendezVous() {
        List<RendezVousResponseDTO> rs = rendezVousService.obtenirTousLesRendezVous();
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/medecine/{id}")
    public ResponseEntity<List<RendezVousResponseDTO>> rechercherRendezVousParMedecine(@PathVariable long id) {
        List<RendezVousResponseDTO> rs = rendezVousService.rechercherParMedecine(id);
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<List<RendezVousResponseDTO>> rechercherRendezVousParPatient(@PathVariable long id) {
        List<RendezVousResponseDTO> rs = rendezVousService.rechercherParPatient(id);
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/RendezVousParMedecinId/{id}")
    public ResponseEntity<List<RendezVousResponseDTO>> getMedecineRendezVous(@PathVariable @Valid long id) {
        return ResponseEntity.ok(rendezVousService.findRendzeVousByMedecinID(id));
    }

    @GetMapping("/findByStatut")
    public ResponseEntity<List<RendezVousResponseDTO>> findByStatut(@RequestParam @Valid Statut statut) {
        return ResponseEntity.ok(rendezVousService.rendezVousParStatut(statut));
    }

    @PutMapping("/EditRendezVousStatut")
    public ResponseEntity<RendezVousResponseDTO> modifieRendezVousStatut(@RequestParam Long id, @RequestParam Statut statut) {
        return ResponseEntity.ok(rendezVousService.modifieRendezVousStatut(id, statut));
    }

    @GetMapping("/getRendezVous")
    public ResponseEntity<Page<RendezVousResponseDTO>> obtenirTousLesRendezVousPagination
            (@RequestParam(defaultValue = "1") int pageNumber,
             @RequestParam(defaultValue = "5") int pageSize,
             @RequestParam(defaultValue = "dateRendezVous") String sortBy,
             @RequestParam(defaultValue = "desc") String sortDer
            ) {
        Sort sort = sortDer.equalsIgnoreCase("desc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Page<RendezVousResponseDTO>
                rs = rendezVousService.obtenirTousLesRendezVousPagination(PageRequest.of(pageNumber - 1, pageSize, sort));
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/rechercherParDate")
    public ResponseEntity<Page<RendezVousResponseDTO>> rechercherParDate
            (@RequestParam(defaultValue = "1") int pageNumber,
             @RequestParam(defaultValue = "5") int pageSize,
             @RequestParam(defaultValue = "EN_ATTENTE") Statut statut,
             @RequestParam(defaultValue = "asc") String sortDer)
    {
        Sort sort = sortDer.equalsIgnoreCase("asc") ? Sort.by("statut").ascending() : Sort.by("statut").descending();
        Pageable pageable = PageRequest.of(pageNumber-1,pageSize,sort);
        Page<RendezVousResponseDTO>
                rs = rendezVousService.rechercherParStatut(statut,pageable);
        return ResponseEntity.ok(rs);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countRendzeVous(){
        return  ResponseEntity.ok(rendezVousService.getRendezVousTotal()) ;
    }

    @GetMapping("/getAllPatietnRendzeVous")
    public ResponseEntity<List<RendezVousResponseDTO>> getAllPatietnRendzeVous() {
        return ResponseEntity.ok(rendezVousService.getallPatientRendzeVous());
  }

    @GetMapping("/getAllMedecinRendesVous")
    public ResponseEntity<List<MedecinResponseDTO>> getAllmedeccenRendezVous(){
        return ResponseEntity.ok(rendezVousService.getALLMedecineRendezVouus());
    }

//    @GetMapping("/getAllrendevouGreaterTHAN")
//  public ResponseEntity<List<PatientResponseDTO>> getAllpatientGreaterThan(@RequestParam int number){
//        return ResponseEntity.ok(rendezVousService.getALlRendezVousGretaerTHAN(number));
//    }
//    @GetMapping("/rendezVouAfterToday")
//    public ResponseEntity<List<RendezVousResponseDTO>> getALLRendezvouAftertoday(){
//        return ResponseEntity.ok(rendezVousService.getAllRendezVousAfterToday());
//    }

    }