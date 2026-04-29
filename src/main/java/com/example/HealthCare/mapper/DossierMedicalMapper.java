package com.example.HealthCare.mapper;

import com.example.HealthCare.model.dto.DossierMedicalAjouterDTO;
import com.example.HealthCare.model.dto.DoussierMedicalReturnDTO;
import com.example.HealthCare.model.entity.DossierMedical;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DossierMedicalMapper {
    DossierMedical toEntity(DossierMedicalAjouterDTO dossierMedicalAjouterDTO);
    DoussierMedicalReturnDTO toDTO(DossierMedical dossierMedical);
}
