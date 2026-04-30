package com.example.HealthCare.mapper;

import com.example.HealthCare.dto.DossierMedicalRequestDTO;
import com.example.HealthCare.dto.DossierMedicalResponseDTO;
import com.example.HealthCare.model.DossierMedical;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DossierMedicalMapper {
    @Mapping(source = "patientId", target = "patient.id")
    DossierMedical toEntity(DossierMedicalRequestDTO dossierMedicalRequestDTO);
    DossierMedicalResponseDTO toDTO(DossierMedical dossierMedical);
}
