package com.example.HealthCare.service;

import com.example.HealthCare.mapper.RendezVousMapper;
import com.example.HealthCare.model.dto.MedecinAjouterDTO;
import com.example.HealthCare.model.dto.RendezVousAjouterDTO;
import com.example.HealthCare.model.dto.RendezVousReturnDTO;
import com.example.HealthCare.model.entity.Medecine;
import com.example.HealthCare.model.entity.Patient;
import com.example.HealthCare.model.entity.RendezVous;
import com.example.HealthCare.repository.MedecinRepository;
import com.example.HealthCare.repository.PatientRepository;
import com.example.HealthCare.repository.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RendezVousService {

    @Autowired
    RendezVousRepository rendezVousRepository;
    @Autowired
    RendezVousMapper rendezVousMapper;
    @Autowired
    MedecinRepository medecinRepository;
    @Autowired
    PatientRepository patientRepository;

    public  RendezVousReturnDTO ajouterRendezVous(RendezVousAjouterDTO rendezVousAjouterDTO)
    {
        Medecine medecin = medecinRepository.findById(rendezVousAjouterDTO.getMedecinId()).orElseThrow(() -> new RuntimeException("Medecin not found"));
        Patient patient = patientRepository.findById(rendezVousAjouterDTO.getPatientId()).orElseThrow(() -> new RuntimeException("Patient not found"));


        RendezVous rendezVous = new RendezVous();

        rendezVous.setDateRendezVous(rendezVousAjouterDTO.getDateRendezVous());
        rendezVous.setStatut(rendezVousAjouterDTO.getStatut());
        rendezVous.setMedecine(medecin);
        rendezVous.setPatient(patient);

        RendezVous save = rendezVousRepository.save(rendezVous);

        return rendezVousMapper.ToDTO(save);
    }

    public RendezVousReturnDTO modifieRendezVous(Long id, RendezVousAjouterDTO rendezVousAjouterDTO){
        RendezVous rendezVous = rendezVousRepository.findById(id).orElseThrow(()-> new RuntimeException("RendezVous Introvable"));
        Medecine medecine = medecinRepository.findById(id).orElseThrow(()->new RuntimeException("Not found"));
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new RuntimeException("Not found"));
        rendezVous.setMedecine(medecine);
        rendezVous.setPatient(patient);
        rendezVous.setStatut(rendezVousAjouterDTO.getStatut());
        rendezVous.setDateRendezVous(rendezVousAjouterDTO.getDateRendezVous());

        RendezVous saveUpdatedRendezVous = rendezVousRepository.save(rendezVous);
        return rendezVousMapper.ToDTO(saveUpdatedRendezVous);
    }

    public RendezVousReturnDTO AnnulerRendezVous(long id){
        RendezVous rendezVous = rendezVousRepository.findById(id).orElseThrow(()-> new RuntimeException("RendezVou Introvable"));

        rendezVous.setStatut("Annuler");

        RendezVous saveAnuller = rendezVousRepository.save(rendezVous);
        return rendezVousMapper.ToDTO(saveAnuller);
    }

    public List<RendezVousReturnDTO> obtenirTousLesRendezVous(){
        List<RendezVous> rendezVous = rendezVousRepository.findAll();
        List<RendezVousReturnDTO> rendezVousReturnDTOS = new ArrayList<>();
        for(RendezVous RV: rendezVous){
            rendezVousReturnDTOS.add(rendezVousMapper.ToDTO(RV));
        }
        return rendezVousReturnDTOS;

    }








}
