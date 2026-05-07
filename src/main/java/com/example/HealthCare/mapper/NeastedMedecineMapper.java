package com.example.HealthCare.mapper;

import com.example.HealthCare.dto.NeastedMedecineResponceDTO;
import com.example.HealthCare.model.Medecine;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface NeastedMedecineMapper {
    Medecine ToEntity(NeastedMedecineResponceDTO neastedMedecineResponceDTO);
    NeastedMedecineResponceDTO ToDTO(Medecine medecine);
}
