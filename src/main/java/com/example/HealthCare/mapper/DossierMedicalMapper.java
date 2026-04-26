package com.example.HealthCare.mapper;

import com.example.HealthCare.model.dto.DossierMedicalDTO;
import com.example.HealthCare.model.entity.DossierMedical;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DossierMedicalMapper {
    DossierMedicalDTO toDTO(DossierMedical dossierMedical);
    DossierMedical toEntity(DossierMedicalDTO dto);
}
