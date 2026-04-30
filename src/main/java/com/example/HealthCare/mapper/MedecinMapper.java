package com.example.HealthCare.mapper;

import com.example.HealthCare.dto.MedecinRequestDTO;
import com.example.HealthCare.dto.MedecinResponseDTO;
import com.example.HealthCare.model.Medecine;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface MedecinMapper {
    Medecine ToEntity(MedecinRequestDTO medecinRequestDTO);
    MedecinResponseDTO ToDTO(Medecine medecine);
}
