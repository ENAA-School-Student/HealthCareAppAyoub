package com.example.HealthCare.service;

import com.example.HealthCare.mapper.PatientMapper;
import com.example.HealthCare.model.dto.PatientAjouterDTO;
import com.example.HealthCare.model.dto.PatientReturnDTO;
import com.example.HealthCare.model.entity.Patient;
import com.example.HealthCare.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientMapper patientMapper;

    public PatientReturnDTO ajouterPatient(PatientAjouterDTO patientAjouterDTO) {
        Patient patient = patientMapper.toEntity(patientAjouterDTO);
        Patient savePatient = patientRepository.save(patient);
        return patientMapper.toDTO(savePatient);
    }

    public List<PatientReturnDTO> obtenirTousLesPatients(){
        List<Patient>patients = patientRepository.findAll();
        List<PatientReturnDTO> patientReturnDTOS = new ArrayList<>();
        for(Patient patient : patients){
        patientReturnDTOS.add(patientMapper.toDTO(patient));
        }
return patientReturnDTOS;
    }
    public PatientReturnDTO modifierpatient(Long id, PatientAjouterDTO patientAjouterDTO){
        Patient patient = patientRepository.findById(id).orElseThrow(()->new RuntimeException("Patient introvable"));

        patient.setNom(patientAjouterDTO.getNom());
        patient.setPrenom(patientAjouterDTO.getPrenom());
        patient.setEmail(patientAjouterDTO.getEmail());
        patient.setTelephone(patientAjouterDTO.getTelephone());
        patient.setDateNaissance(patientAjouterDTO.getDateNaissance());

        Patient saveUpdate = patientRepository.save(patient);
        return patientMapper.toDTO(saveUpdate);
    }

    public void supprimerPatinet(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new RuntimeException("Patient Intovable"));
        patientRepository.delete(patient);
    }

}
