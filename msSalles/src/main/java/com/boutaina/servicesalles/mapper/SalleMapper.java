package com.boutaina.servicesalles.mapper;

import com.boutaina.servicesalles.dto.SalleRequestDTO;
import com.boutaina.servicesalles.dto.SalleResponseDTO;
import com.boutaina.servicesalles.entity.Salle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SalleMapper {
    Salle toEntity(SalleRequestDTO dto);
    SalleResponseDTO toDto(Salle entity);
}
