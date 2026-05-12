package com.example.HealthCare.mapper;

import com.example.HealthCare.dto.UserRequestDTO;
import com.example.HealthCare.dto.UserResponceDTO;
import com.example.HealthCare.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")

public interface UserMapper {
    User ToEntity(UserRequestDTO userRequestDTO);
    UserResponceDTO  ToDTO(User user);
}
