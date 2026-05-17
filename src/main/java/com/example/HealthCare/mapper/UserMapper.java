package com.example.HealthCare.mapper;

import com.example.HealthCare.dto.AuthenticationRequestDTO;
import com.example.HealthCare.dto.AuthenticationResponceDTO;
import com.example.HealthCare.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")

public interface UserMapper {
    User ToEntity(AuthenticationRequestDTO authenticationRequestDTO);
    AuthenticationResponceDTO ToDTO(User user);
}
