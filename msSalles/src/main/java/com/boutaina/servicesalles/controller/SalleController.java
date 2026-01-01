package com.boutaina.servicesalles.controller;

import com.boutaina.servicesalles.dto.SalleRequestDTO;
import com.boutaina.servicesalles.dto.SalleResponseDTO;
import com.boutaina.servicesalles.service.SalleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salles")
@RequiredArgsConstructor
public class SalleController {

    private final SalleService service;

    @PostMapping
    public SalleResponseDTO create(@RequestBody SalleRequestDTO dto) {
        return service.create(dto);
    }

    @GetMapping("/{id}")
    public SalleResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<SalleResponseDTO> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
