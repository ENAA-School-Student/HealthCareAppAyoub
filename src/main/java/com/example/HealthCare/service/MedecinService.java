package com.example.HealthCare.service;

import com.example.HealthCare.Exceptions.ResourceNotFoundException;
import com.example.HealthCare.dto.MedecinRequestDTO;
import com.example.HealthCare.dto.MedecinResponseDTO;
import com.example.HealthCare.dto.PatientResponseDTO;
import com.example.HealthCare.mapper.MedecinMapper;
import com.example.HealthCare.model.Medecine;
import com.example.HealthCare.model.User;
import com.example.HealthCare.repository.MedecinRepository;
import com.example.HealthCare.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
@Transactional
public class MedecinService {

    private final MedecinRepository medecinRepository;
    private final MedecinMapper medecinMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @CacheEvict(value = "medecine", allEntries = true)
    public MedecinResponseDTO ajouterMedecin(MedecinRequestDTO medecinRequestDTO){
        if(userRepository.findByEmail(medecinRequestDTO.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists!");
        }
        User user = new User();
        user.setUsername(medecinRequestDTO.getEmail());
        user.setEmail(medecinRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(medecinRequestDTO.getTelephone()));
        user.setRole(com.example.HealthCare.enums.Role.MEDECIN);
        userRepository.save(user);

        Medecine medecine = medecinMapper.ToEntity(medecinRequestDTO);
        medecine.setUser(user);
        return medecinMapper.ToDTO(medecinRepository.save(medecine));

}

    @CacheEvict(value = "medecine", allEntries = true)
    public MedecinResponseDTO modifieMedeceine(Long id , MedecinRequestDTO medecinRequestDTO){
        Medecine medecine = medecinRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Medecin Not found with id  :"+id));

        medecine.setNom(medecinRequestDTO.getNom());
        medecine.setEmail(medecinRequestDTO.getEmail());
        medecine.setTelephone(medecinRequestDTO.getTelephone());

        Medecine saveUpdatedMedecine = medecinRepository.save(medecine);
        return medecinMapper.ToDTO(saveUpdatedMedecine);
    }
    @CacheEvict(value = "medecine", allEntries = true)
    public Boolean supprimerMedecine(long id){
        Medecine medecine = medecinRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Medecin Not found with id  :"+id));
        medecinRepository.delete(medecine);
        return true;
    }

    @Cacheable("medecine")
    public Page<MedecinResponseDTO> obtenirTousLesMedecinPagination(Pageable pageable) {
        return medecinRepository.findAll(pageable)
                .map(medecin -> medecinMapper.ToDTO(medecin));
    }
    @Cacheable("medecine")
    public Page<MedecinResponseDTO> rechercherParSpecialite(String specialite, Pageable pageable){
        return medecinRepository.findMedcineByspecialite(specialite,pageable)
                .map(medecine -> medecinMapper.ToDTO(medecine));
    }

@Cacheable("medecine")
    public  Long getTotalMedecine(){
        return  medecinRepository.count();
}
}

