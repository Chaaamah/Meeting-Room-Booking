package com.boutaina.msreservation.dto;

import lombok.Data;

@Data
public class SalleDTO {
    private Long id;
    private String nom;
    private int capacite;
    private String equipement;
}
