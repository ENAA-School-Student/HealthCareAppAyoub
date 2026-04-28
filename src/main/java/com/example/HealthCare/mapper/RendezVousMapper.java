package com.example.HealthCare.mapper;

import com.example.HealthCare.model.dto.RendezVousAjouterDTO;
import com.example.HealthCare.model.dto.RendezVousReturnDTO;
import com.example.HealthCare.model.entity.RendezVous;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")

public interface RendezVousMapper {
    RendezVous ToEntity(RendezVousAjouterDTO rendezVousAjouterDTO);
    RendezVousReturnDTO ToDTO(RendezVous rendezVous);
}
