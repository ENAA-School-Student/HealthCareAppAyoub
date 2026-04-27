package com.example.HealthCare.controller;

import com.example.HealthCare.model.dto.DossierMedicalDTO;
import com.example.HealthCare.model.entity.DossierMedical;
import com.example.HealthCare.service.DossierMedicalService;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/DossierMedical")
public class DossierMedicalController {
    @Autowired
    public DossierMedicalService dossierMedicalService;

    @GetMapping
    public List<DossierMedicalDTO> getDossier(){
        return  dossierMedicalService.getAllDossierMedical();
    }


}
