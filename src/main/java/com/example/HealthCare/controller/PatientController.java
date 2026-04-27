package com.example.HealthCare.controller;

import com.example.HealthCare.mapper.PatientMapper;
import com.example.HealthCare.model.dto.PatientAjouterDTO;
import com.example.HealthCare.model.dto.PatientReturnDTO;
import com.example.HealthCare.model.entity.Patient;
import com.example.HealthCare.repository.PatientRepository;
import com.example.HealthCare.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private  PatientMapper patientMapper;
    @PostMapping
    public ResponseEntity<PatientReturnDTO> ajouterPatient(@RequestBody @Valid PatientAjouterDTO dto){
        PatientReturnDTO savePatient = patientService.ajouterPatient(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savePatient);
    }

    @GetMapping
    public List<PatientReturnDTO> getAllPatients(){
        List<Patient> patients =patientRepository.findAll();
        List<PatientReturnDTO> patientReturnDTOS = new ArrayList<>();
        for(Patient patient : patients){
            patientReturnDTOS.add(patientMapper.toDTO(patient));
        }
        return patientReturnDTOS;
    }
}
