package com.boutaina.servicesalles.service.impl;

import com.boutaina.servicesalles.dto.SalleRequestDTO;
import com.boutaina.servicesalles.dto.SalleResponseDTO;
import com.boutaina.servicesalles.entity.Salle;
import com.boutaina.servicesalles.exception.ResourceNotFoundException;
import com.boutaina.servicesalles.mapper.SalleMapper;
import com.boutaina.servicesalles.repository.SalleRepository;
import com.boutaina.servicesalles.service.SalleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalleServiceImpl implements SalleService {

    private final SalleRepository repository;
    private final SalleMapper mapper;

    @Override
    public SalleResponseDTO create(SalleRequestDTO dto) {
        Salle salle = mapper.toEntity(dto);
        return mapper.toDto(repository.save(salle));
    }

    @Override
    public SalleResponseDTO getById(Long id) {
        Salle salle = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Salle non trouvée"));
        return mapper.toDto(salle);
    }

    @Override
    public List<SalleResponseDTO> getAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
