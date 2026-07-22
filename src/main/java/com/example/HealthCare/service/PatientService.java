package com.example.HealthCare.service;

import com.example.HealthCare.Exceptions.ResourceNotFoundException;

import com.example.HealthCare.dto.PatientRequestDTO;
import com.example.HealthCare.dto.PatientResponseDTO;
import com.example.HealthCare.enums.Role;
import com.example.HealthCare.mapper.PatientMapper;
import com.example.HealthCare.mapper.UserMapper;
import com.example.HealthCare.model.Patient;
import com.example.HealthCare.model.User;
import com.example.HealthCare.repository.PatientRepository;
import com.example.HealthCare.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class PatientService {
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final PasswordEncoder passwordEncoder;

    @CacheEvict(value = "patients", allEntries = true)
    public PatientResponseDTO ajouterPatient(PatientRequestDTO patientRequestDTO){
        if(userRepository.findByEmail(patientRequestDTO.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists!");
        }
        User user = new User();
        user.setUsername(patientRequestDTO.getEmail());
        user.setEmail(patientRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(patientRequestDTO.getTelephone()));
        user.setRole(Role.PATIENT);
        userRepository.save(user);

        Patient patient = patientMapper.toEntity(patientRequestDTO);
        patient.setUser(user);
        return patientMapper.toDTO(patientRepository.save(patient));
    }
    @Cacheable("patients")
    public List<PatientResponseDTO> obtenirTousLesPatients(){
            List<Patient>patients = patientRepository.findAll();
            List<PatientResponseDTO> patientResponseDTOS = new ArrayList<>();
            for(Patient patient : patients){
            patientResponseDTOS.add(patientMapper.toDTO(patient));
            }
        return patientResponseDTOS;
    }
    @CacheEvict(value = "patients", allEntries = true)
    public PatientResponseDTO modifierpatient(Long id, PatientRequestDTO patientRequestDTO){
        Patient patient = patientRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Patient Not found with id  :"+id));

        patient.setNom(patientRequestDTO.getNom());
        patient.setPrenom(patientRequestDTO.getPrenom());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setTelephone(patientRequestDTO.getTelephone());
        patient.setDateNaissance(patientRequestDTO.getDateNaissance());

        Patient saveUpdate = patientRepository.save(patient);
        return patientMapper.toDTO(saveUpdate);
    }
    @CacheEvict(value = "patients",allEntries = true)
        public Boolean supprimerPatinet(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Patient Not found with id  :"+id));
        patientRepository.delete(patient);
        return true;
    }
    @Cacheable(value = "patients", key = "#id")
    public PatientResponseDTO consulterPatient(Long id){
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Patient Not found with id  :"+id));
        return patientMapper.toDTO(patient);
    }
    @Cacheable("patients")
    public Page<PatientResponseDTO> obtenirTousLesPatientsParPagenation(Pageable pageable){
      return patientRepository.findAll(pageable)
              .map(patient -> patientMapper.toDTO(patient));
    }
    @Cacheable("patients")
    public Page<PatientResponseDTO> rechercherParNom(String nom, Pageable pageable){
        return patientRepository.findByNom(nom,pageable)
                .map(patient -> patientMapper.toDTO(patient));
  }
  @Cacheable("patients")
    public PatientResponseDTO obtenirParUsername(String username){
      Patient patient = patientRepository.findByUserUsername(username)
              .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
      return patientMapper.toDTO(patient);
  }
    @CacheEvict(value = "patients", allEntries = true)
    public PatientResponseDTO modifierParUsername(String username, PatientRequestDTO dto) {
        Patient patient = patientRepository.findByUserUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with username: " + username));

        patient.setNom(dto.getNom());
        patient.setPrenom(dto.getPrenom());
        patient.setTelephone(dto.getTelephone());
        patient.setEmail(dto.getEmail());
        patient.setDateNaissance(dto.getDateNaissance());

        Patient updatedPatient = patientRepository.save(patient);

        return patientMapper.toDTO(updatedPatient);
    }

//public Page<PatientResponseDTO> findBytelephone(String tele, Pageable p){
//        return patientRepository.findByTelePhone(tele,p)
//                .map(patientMapper::toDTO);
//}

}
