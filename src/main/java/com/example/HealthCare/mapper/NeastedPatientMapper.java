package com.example.HealthCare.mapper;

import com.example.HealthCare.dto.PatientRequestDTO;
import com.example.HealthCare.dto.PatientResponseDTO;
import com.example.HealthCare.model.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface NeastedPatientMapper {
    Patient toEntity(PatientRequestDTO dto);
    PatientResponseDTO toDTO(Patient patient);

}
