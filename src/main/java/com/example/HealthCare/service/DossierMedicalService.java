package com.example.HealthCare.service;

import com.example.HealthCare.mapper.DossierMedicalMapper;
import com.example.HealthCare.mapper.RendezVousMapper;
import com.example.HealthCare.model.dto.DossierMedicalAjouterDTO;
import com.example.HealthCare.model.dto.DoussierMedicalReturnDTO;
import com.example.HealthCare.model.entity.DossierMedical;
import com.example.HealthCare.repository.DossierMedicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DossierMedicalService {
    @Autowired
   private DossierMedicalRepository dossierMedicalRepository;
    @Autowired
    private DossierMedicalMapper dossierMedicalMapper;


    public DoussierMedicalReturnDTO ajouterUnDossierMedical(DossierMedicalAjouterDTO dossierMedicalAjouterDTO){
        DossierMedical dossierMedical = dossierMedicalMapper.toEntity(dossierMedicalAjouterDTO);
        DossierMedical saveDossierMedical = dossierMedicalRepository.save(dossierMedical);
        return dossierMedicalMapper.toDTO(saveDossierMedical);
    }

        public DoussierMedicalReturnDTO Consulterdossielmedical(int id){
        DossierMedical dossierMedical = dossierMedicalRepository.findByPatient_id(id);
        return dossierMedicalMapper.toDTO(dossierMedical);
        }

        public DoussierMedicalReturnDTO AjouterDiagnostic(int patientId , String diagnostic){
        DossierMedical dossierMedical = dossierMedicalRepository.findByPatient_id(patientId);
        dossierMedical.setDiagnostic(diagnostic);
        DossierMedical save = dossierMedicalRepository.save(dossierMedical);
        return dossierMedicalMapper.toDTO(save);
        }
        public DoussierMedicalReturnDTO AjouterObservation(int patientId , String observation){
        DossierMedical dossierMedical = dossierMedicalRepository.findByPatient_id(patientId);
        dossierMedical.setObservations(observation);
        DossierMedical save = dossierMedicalRepository.save(dossierMedical);
        return dossierMedicalMapper.toDTO(save);
        }






}
