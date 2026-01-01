package com.boutaina.servicesalles.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalleResponseDTO {
    private Long id;
    private String nom;
    private int capacite;
    private String equipement;
}
