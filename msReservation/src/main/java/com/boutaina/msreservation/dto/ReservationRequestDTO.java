package com.boutaina.msreservation.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data

public class ReservationRequestDTO {
    private Long salleId;
    private Long utilisateurId;
    private LocalDate date;
    private LocalTime heure;
}
