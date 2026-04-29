package com.example.HealthCare.mapper;

import com.example.HealthCare.model.dto.DossierMedicalAjouterDTO;
import com.example.HealthCare.model.dto.DossierMedicalReturnDTO;
import com.example.HealthCare.model.entity.DossierMedical;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DossierMedicalMapper {
    @Mapping(source = "patientId", target = "patient.id")
    DossierMedical toEntity(DossierMedicalAjouterDTO dossierMedicalAjouterDTO);
    DossierMedicalReturnDTO toDTO(DossierMedical dossierMedical);
}
