package com.boutaina.servicesalles.service;

import com.boutaina.servicesalles.dto.SalleRequestDTO;
import com.boutaina.servicesalles.dto.SalleResponseDTO;
import java.util.List;

public interface SalleService {
    SalleResponseDTO create(SalleRequestDTO dto);
    SalleResponseDTO getById(Long id);
    List<SalleResponseDTO> getAll();
    void delete(Long id);
}
