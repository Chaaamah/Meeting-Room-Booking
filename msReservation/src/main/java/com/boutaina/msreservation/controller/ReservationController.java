package com.boutaina.msreservation.controller;

import com.boutaina.msreservation.dto.ReservationRequestDTO;
import com.boutaina.msreservation.dto.ReservationResponseDTO;
import com.boutaina.msreservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService service;

    @PostMapping
    public ReservationResponseDTO create(@RequestBody ReservationRequestDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<ReservationResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ReservationResponseDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
