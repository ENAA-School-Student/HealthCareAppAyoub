package com.example.HealthCare.service;

import com.example.HealthCare.dto.RendezVousRequestDTO;
import com.example.HealthCare.dto.RendezVousResponseDTO;
import com.example.HealthCare.enums.Statut;
import com.example.HealthCare.mapper.RendezVousMapper;
import com.example.HealthCare.model.Medecine;
import com.example.HealthCare.model.Patient;
import com.example.HealthCare.model.RendezVous;
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

    public RendezVousResponseDTO ajouterRendezVous(RendezVousRequestDTO rendezVousRequestDTO)
    {
        Medecine medecin = medecinRepository.findById(rendezVousRequestDTO.getMedecinId()).orElseThrow(() -> new RuntimeException("Medecin not found"));
        Patient patient = patientRepository.findById(rendezVousRequestDTO.getPatientId()).orElseThrow(() -> new RuntimeException("Patient not found"));

        RendezVous rendezVous = rendezVousMapper.ToEntity(rendezVousRequestDTO);
        rendezVous.setMedecine(medecin);
        rendezVous.setPatient(patient);

        RendezVous save = rendezVousRepository.save(rendezVous);

        return rendezVousMapper.ToDTO(save);
    }

    public RendezVousResponseDTO modifieRendezVous(Long id, RendezVousRequestDTO rendezVousRequestDTO){
        RendezVous rendezVous = rendezVousRepository.findById(id).orElseThrow(()-> new RuntimeException("RendezVous Introvable"));
        Medecine medecine = medecinRepository.findById(id).orElseThrow(()->new RuntimeException("Not found"));
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new RuntimeException("Not found"));
        rendezVous.setMedecine(medecine);
        rendezVous.setPatient(patient);
        rendezVous.setStatut(rendezVousRequestDTO.getStatut());
        rendezVous.setDateRendezVous(rendezVousRequestDTO.getDateRendezVous());

        RendezVous saveUpdatedRendezVous = rendezVousRepository.save(rendezVous);
        return rendezVousMapper.ToDTO(saveUpdatedRendezVous);
    }

    public RendezVousResponseDTO AnnulerRendezVous(long id){
        RendezVous rendezVous = rendezVousRepository.findById(id).orElseThrow(()-> new RuntimeException("RendezVou Introvable"));

        rendezVous.setStatut(Statut.ANNULE);

        RendezVous saveAnuller = rendezVousRepository.save(rendezVous);
        return rendezVousMapper.ToDTO(saveAnuller);
    }

    public List<RendezVousResponseDTO> obtenirTousLesRendezVous(){
        List<RendezVous> rendezVous = rendezVousRepository.findAll();
        List<RendezVousResponseDTO> rendezVousResponseDTOS = new ArrayList<>();
        for(RendezVous RV: rendezVous){
            rendezVousResponseDTOS.add(rendezVousMapper.ToDTO(RV));
        }
        return rendezVousResponseDTOS;

    }
public  List<RendezVousResponseDTO> rechercherParPatient(long id){
        List<RendezVousResponseDTO> GetResault = new ArrayList<>();
        for(RendezVous rendezVous : rendezVousRepository.findByPatient_Id(id) ){
            GetResault.add(rendezVousMapper.ToDTO(rendezVous));
        }
        return GetResault;
}

public List<RendezVousResponseDTO> rechercherParMedecine(long id){
        List<RendezVousResponseDTO> GetResaul = new ArrayList<>();
        for(RendezVous rendezVous : rendezVousRepository.findByMedecine_Id(id)){
            GetResaul.add(rendezVousMapper.ToDTO(rendezVous));
        }
        return GetResaul;
}

}
