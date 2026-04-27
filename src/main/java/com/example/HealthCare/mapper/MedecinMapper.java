package com.example.HealthCare.mapper;

import com.example.HealthCare.model.dto.MedecinAjouterDTO;
import com.example.HealthCare.model.dto.MedecinReturnDTO;
import com.example.HealthCare.model.entity.Medecine;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface MedecinMapper {
    Medecine ToEntity(MedecinAjouterDTO medecinAjouterDTO);
    MedecinReturnDTO ToDTO(Medecine medecine);
}
