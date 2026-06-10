package com.example.HealthCare.controller;

import com.example.HealthCare.model.DossierMedical;
import com.example.HealthCare.model.Medecine;
import com.example.HealthCare.model.Patient;
import com.example.HealthCare.model.RendezVous;
import com.example.HealthCare.repository.DossierMedicalRepository;
import com.example.HealthCare.repository.MedecinRepository;
import com.example.HealthCare.repository.PatientRepository;
import com.example.HealthCare.repository.RendezVousRepository;
import com.example.HealthCare.service.PdfService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/download")
@RequiredArgsConstructor
public class PdfController {

    private final PatientRepository patientRepository;
    private final DossierMedicalRepository dossierRepository;
    private final RendezVousRepository rendezVousRepository;
    private final PdfService pdfService;
    private final MedecinRepository medecinRepository;

    @GetMapping("/patients/{id}/dossier")
    public ResponseEntity<byte[]> downloadDossier(@PathVariable Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient non trouvé"));

        DossierMedical dossier = dossierRepository.findByPatient_id(id)
                .orElseThrow(() -> new EntityNotFoundException("Dossier non trouvé"));

        byte[] pdfBytes = pdfService.generateDossierMedical(patient, dossier);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"dossier-" + id + ".pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(pdfBytes.length)
                .body(pdfBytes);
    }

    @GetMapping("/patients/{id}/rendez-vous")
    public ResponseEntity<byte[]> downloadRendezVous(@PathVariable Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient non trouvé"));

        List<RendezVous> liste = rendezVousRepository.findByMedecine_Id(id);
        byte[] pdfBytes = pdfService.generateListeRendezVous(liste, patient);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"rdv-patient-" + id + ".pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(pdfBytes.length)
                .body(pdfBytes);
    }

    @GetMapping("/rapport")
    public ResponseEntity<byte[]> downloadRapport() {
        List<Patient> patients = patientRepository.findAll();
        List<Medecine> medecins = medecinRepository.findAll();
        List<RendezVous> rendezVous = rendezVousRepository.findAll();
        byte[] pdf = pdfService.generateRapportSimple(patients, medecins, rendezVous);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"rapport.pdf\"")
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(pdf.length)
                .body(pdf);
    }
}