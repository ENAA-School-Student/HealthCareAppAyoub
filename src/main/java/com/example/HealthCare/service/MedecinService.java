package com.example.HealthCare.service;

import com.example.HealthCare.mapper.MedecinMapper;
import com.example.HealthCare.model.dto.MedecinAjouterDTO;
import com.example.HealthCare.model.dto.MedecinReturnDTO;
import com.example.HealthCare.model.entity.Medecine;
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

    public MedecinReturnDTO ajouterMedecin(MedecinAjouterDTO medecinAjouterDTO){
        Medecine medecine = medecinMapper.ToEntity(medecinAjouterDTO);
        Medecine saveMedecine=medecinRepository.save(medecine);
        return medecinMapper.ToDTO(saveMedecine);
}
    public List<MedecinReturnDTO> obtenirTousLesMedecin() {
        List<Medecine> medecines = medecinRepository.findAll();
        List<MedecinReturnDTO> medecinReturnDTOS = new ArrayList<>();
        for(Medecine medecine : medecines){
            medecinReturnDTOS.add(medecinMapper.ToDTO(medecine));
        }
        return medecinReturnDTOS;
    }


    public MedecinReturnDTO modifieMedeceine(Long id , MedecinAjouterDTO medecinAjouterDTO){
        Medecine medecine = medecinRepository.findById(id).orElseThrow(() -> new RuntimeException("Medecine Introvable"));

        medecine.setNom(medecinAjouterDTO.getNom());
        medecine.setEmail(medecinAjouterDTO.getEmail());
        medecine.setTelephone(medecinAjouterDTO.getTelephone());

        Medecine saveUpdatedMedecine = medecinRepository.save(medecine);
        return medecinMapper.ToDTO(saveUpdatedMedecine);
    }

    public void supprimerMedecine(long id){
        Medecine medecine = medecinRepository.findById(id).orElseThrow(()-> new RuntimeException("Medecine Introvable"));
        medecinRepository.delete(medecine);
    }
}

