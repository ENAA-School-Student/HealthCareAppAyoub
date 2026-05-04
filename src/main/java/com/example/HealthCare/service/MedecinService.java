package com.example.HealthCare.service;

import com.example.HealthCare.dto.MedecinRequestDTO;
import com.example.HealthCare.dto.MedecinResponseDTO;
import com.example.HealthCare.mapper.MedecinMapper;
import com.example.HealthCare.model.Medecine;
import com.example.HealthCare.repository.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedecinService {
    @Autowired
    private MedecinRepository medecinRepository;
    @Autowired
    private MedecinMapper medecinMapper;

    public MedecinResponseDTO ajouterMedecin(MedecinRequestDTO medecinRequestDTO){
        Medecine medecine = medecinMapper.ToEntity(medecinRequestDTO);
        Medecine saveMedecine=medecinRepository.save(medecine);
        return medecinMapper.ToDTO(saveMedecine);
}
    public List<MedecinResponseDTO> obtenirTousLesMedecin() {
        List<Medecine> medecines = medecinRepository.findAll();
        List<MedecinResponseDTO> medecinResponseDTOS = new ArrayList<>();
        for(Medecine medecine : medecines){
            medecinResponseDTOS.add(medecinMapper.ToDTO(medecine));
        }
        return medecinResponseDTOS;
    }


    public MedecinResponseDTO modifieMedeceine(Long id , MedecinRequestDTO medecinRequestDTO){
        Medecine medecine = medecinRepository.findById(id).orElseThrow(() -> new RuntimeException("Medecine Introvable"));

        medecine.setNom(medecinRequestDTO.getNom());
        medecine.setEmail(medecinRequestDTO.getEmail());
        medecine.setTelephone(medecinRequestDTO.getTelephone());

        Medecine saveUpdatedMedecine = medecinRepository.save(medecine);
        return medecinMapper.ToDTO(saveUpdatedMedecine);
    }

    public Boolean supprimerMedecine(long id){
        Medecine medecine = medecinRepository.findById(id).orElseThrow(()-> new RuntimeException("Medecine Introvable"));
        medecinRepository.delete(medecine);
        return true;
    }
}

