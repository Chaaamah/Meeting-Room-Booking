package com.boutaina.msreservation.service;

import com.boutaina.msreservation.dto.ReservationRequestDTO;
import com.boutaina.msreservation.dto.ReservationResponseDTO;

import java.util.List;

public interface ReservationService {
    ReservationResponseDTO create(ReservationRequestDTO dto);
    List<ReservationResponseDTO> getAll();
    ReservationResponseDTO getById(Long id);
    void delete(Long id);
}
