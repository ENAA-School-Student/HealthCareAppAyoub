package com.example.HealthCare.service;

import com.example.HealthCare.dto.DossierMedicalRequestDTO;
import com.example.HealthCare.dto.DossierMedicalResponseDTO;
import com.example.HealthCare.mapper.DossierMedicalMapper;
import com.example.HealthCare.model.DossierMedical;
import com.example.HealthCare.repository.DossierMedicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DossierMedicalService {
    @Autowired
   private DossierMedicalRepository dossierMedicalRepository;
    @Autowired
    private DossierMedicalMapper dossierMedicalMapper;


    public DossierMedicalResponseDTO ajouterUnDossierMedical(DossierMedicalRequestDTO dossierMedicalRequestDTO){
        DossierMedical dossierMedical = dossierMedicalMapper.toEntity(dossierMedicalRequestDTO);
        DossierMedical saveDossierMedical = dossierMedicalRepository.save(dossierMedical);
        return dossierMedicalMapper.toDTO(saveDossierMedical);
    }

        public DossierMedicalResponseDTO Consulterdossielmedical(long id){
        DossierMedical dossierMedical = dossierMedicalRepository.findByPatient_id(id);
        return dossierMedicalMapper.toDTO(dossierMedical);
        }

        public DossierMedicalResponseDTO AjouterDiagnostic(long patientId , String diagnostic){
        DossierMedical dossierMedical = dossierMedicalRepository.findByPatient_id(patientId);
        dossierMedical.setDiagnostic(diagnostic);
        DossierMedical save = dossierMedicalRepository.save(dossierMedical);
        return dossierMedicalMapper.toDTO(save);
        }
        public DossierMedicalResponseDTO AjouterObservation(long patientId , String observation){
        DossierMedical dossierMedical = dossierMedicalRepository.findByPatient_id(patientId);
        dossierMedical.setObservations(observation);
        DossierMedical save = dossierMedicalRepository.save(dossierMedical);
        return dossierMedicalMapper.toDTO(save);
        }






}
