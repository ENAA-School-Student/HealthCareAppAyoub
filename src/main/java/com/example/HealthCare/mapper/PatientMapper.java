    package com.example.HealthCare.mapper;

    import com.example.HealthCare.model.dto.PatientAjouterDTO;
    import com.example.HealthCare.model.dto.PatientReturnDTO;
    import com.example.HealthCare.model.entity.Patient;
    import org.mapstruct.Mapper;

    @Mapper(componentModel="spring")
    public interface PatientMapper {
        Patient toEntity(PatientAjouterDTO  dto);
        PatientReturnDTO toDTO(Patient patient);
    }
