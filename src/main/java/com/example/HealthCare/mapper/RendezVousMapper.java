package com.example.HealthCare.mapper;

import com.example.HealthCare.dto.NeastedMedecineResponceDTO;
import com.example.HealthCare.dto.NeastedPatietnResponceDTO;
import com.example.HealthCare.dto.RendezVousRequestDTO;
import com.example.HealthCare.dto.RendezVousResponseDTO;
import com.example.HealthCare.model.Medecine;
import com.example.HealthCare.model.Patient;
import com.example.HealthCare.model.RendezVous;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")

public interface RendezVousMapper {
    RendezVous ToEntity(RendezVousRequestDTO rendezVousRequestDTO);
    RendezVousResponseDTO ToDTO(RendezVous rendezVous);

    NeastedMedecineResponceDTO toNeastedMedecineResponceDTO(Medecine medecine);
    NeastedPatietnResponceDTO toNeastedPatientResponceDTO(Patient patient);


}
