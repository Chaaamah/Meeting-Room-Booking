package com.boutaina.msreservation.mapper;

import com.boutaina.msreservation.dto.ReservationRequestDTO;
import com.boutaina.msreservation.dto.ReservationResponseDTO;
import com.boutaina.msreservation.entity.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    Reservation toEntity(ReservationRequestDTO dto);
    ReservationResponseDTO toDto(Reservation reservation);
}
