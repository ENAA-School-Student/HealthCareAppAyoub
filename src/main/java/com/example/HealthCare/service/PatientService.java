package com.example.HealthCare.service;

import com.example.HealthCare.dto.PatientRequestDTO;
import com.example.HealthCare.dto.PatientResponseDTO;
import com.example.HealthCare.mapper.PatientMapper;
import com.example.HealthCare.model.Patient;
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

    public PatientResponseDTO ajouterPatient(PatientRequestDTO patientRequestDTO) {
        Patient patient = patientMapper.toEntity(patientRequestDTO);
        Patient savePatient = patientRepository.save(patient);
        return patientMapper.toDTO(savePatient);
    }

    public List<PatientResponseDTO> obtenirTousLesPatients(){
        List<Patient>patients = patientRepository.findAll();
        List<PatientResponseDTO> patientResponseDTOS = new ArrayList<>();
        for(Patient patient : patients){
        patientResponseDTOS.add(patientMapper.toDTO(patient));
        }
return patientResponseDTOS;
    }
    public PatientResponseDTO modifierpatient(Long id, PatientRequestDTO patientRequestDTO){
        Patient patient = patientRepository.findById(id).orElseThrow(()->new RuntimeException("Patient introvable"));

        patient.setNom(patientRequestDTO.getNom());
        patient.setPrenom(patientRequestDTO.getPrenom());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setTelephone(patientRequestDTO.getTelephone());
        patient.setDateNaissance(patientRequestDTO.getDateNaissance());

        Patient saveUpdate = patientRepository.save(patient);
        return patientMapper.toDTO(saveUpdate);
    }

        public void supprimerPatinet(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new RuntimeException("Patient Intovable"));
        patientRepository.delete(patient);
    }

    public PatientResponseDTO consulterPatient(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new RuntimeException("Not found"));
        return patientMapper.toDTO(patient);
    }

}
