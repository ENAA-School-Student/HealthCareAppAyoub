package com.example.HealthCare.service;

import com.example.HealthCare.mapper.DossierMedicalMapper;
import com.example.HealthCare.model.dto.DossierMedicalDTO;
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


    public List<DossierMedicalDTO> getAllDossierMedical(){
        List<DossierMedical> dossierMedicals =dossierMedicalRepository.findAll();
        List<DossierMedicalDTO> dossierMedicalDTOS = new ArrayList<>();
        for(DossierMedical dossierMedical : dossierMedicals){
            dossierMedicalDTOS.add(dossierMedicalMapper.toDTO(dossierMedical));
        }
        return dossierMedicalDTOS;
    }
}
