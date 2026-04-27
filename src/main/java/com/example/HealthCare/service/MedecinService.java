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
}}
