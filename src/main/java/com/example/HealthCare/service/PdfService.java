package com.example.HealthCare.service;

import com.example.HealthCare.model.DossierMedical;
import com.example.HealthCare.model.Medecine;
import com.example.HealthCare.model.Patient;
import com.example.HealthCare.model.RendezVous;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PdfService {

    public byte[] generateDossierMedical(Patient patient, DossierMedical dossier) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, out);
            doc.open();

            doc.add(new Paragraph("Dossier Medical"));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Patient: " + patient.getNom() + " " + patient.getPrenom()));
            doc.add(new Paragraph("Date de naissance: " + patient.getDateNaissance()));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Diagnostics: " + dossier.getDiagnostic()));
            doc.add(new Paragraph("Observations: " + dossier.getObservations()));

            doc.close();
        } catch (Exception e) {
            throw new RuntimeException("PDF generation failed", e);
        }

        return out.toByteArray();
    }

    public byte[] generateListeRendezVous(List<RendezVous> liste, Patient patient) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, out);
            doc.open();

            doc.add(new Paragraph("Liste des Rendez-vous"));
            doc.add(new Paragraph("Patient: " + patient.getNom() + " " + patient.getPrenom()));
            doc.add(new Paragraph(" "));

            for (RendezVous rdv : liste) {
                doc.add(new Paragraph("- " + rdv.getDateRendezVous() + " avec Dr. " + rdv.getMedecine().getNom()));
            }

            doc.close();
        } catch (Exception e) {
            throw new RuntimeException("PDF generation failed", e);
        }

        return out.toByteArray();
    }

    public byte[] generateRapportSimple(
            List<Patient> patients,
            List<Medecine> medecins,
            List<RendezVous> rendezVous) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, out);
            doc.open();

            // Title
            doc.add(new Paragraph("Rapport General - HealthCare+"));
            doc.add(new Paragraph("Date: " + java.time.LocalDate.now()));
            doc.add(new Paragraph(" "));


            doc.add(new Paragraph("=== Statistiques ==="));
            doc.add(new Paragraph("Total Patients: " + patients.size()));
            doc.add(new Paragraph("Total Medecins: " + medecins.size()));
            doc.add(new Paragraph("Total Rendez-vous: " + rendezVous.size()));
            doc.add(new Paragraph(" "));

            doc.add(new Paragraph("=== Liste des Patients ==="));
            for (Patient p : patients) {
                doc.add(new Paragraph("- " + p.getNom() + " " + p.getPrenom()));
            }
            doc.add(new Paragraph(" "));

            doc.add(new Paragraph("=== Liste des Medecins ==="));
            for (Medecine m : medecins) {
                doc.add(new Paragraph("- Dr. " + m.getNom() + " specialite " + m.getSpecialite()));
            }

            doc.close();
        } catch (Exception e) {
            throw new RuntimeException("Rapport generation failed", e);
        }

        return out.toByteArray();
    }
}