package com.boutaina.msreservation.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="reservations")
@Data
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long salleId;
    private Long utilisateurId;
    private LocalDate date;
    private LocalTime heure;
}
