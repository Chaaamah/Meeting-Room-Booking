package com.boutaina.msreservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseDTO {
    private Long id;
    private Long salleId;
    private Long utilisateurId;
    private LocalDate date;
    private LocalTime heure;
}
