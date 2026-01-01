package com.boutaina.msreservation.service;

import com.boutaina.msreservation.client.SalleRestClient;
import com.boutaina.msreservation.client.UserRestClient;
import com.boutaina.msreservation.dto.ReservationRequestDTO;
import com.boutaina.msreservation.dto.ReservationResponseDTO;
import com.boutaina.msreservation.dto.SalleDTO;
import com.boutaina.msreservation.dto.UserDTO;
import com.boutaina.msreservation.entity.Reservation;
import com.boutaina.msreservation.exception.ResourceNotFoundException;
import com.boutaina.msreservation.mapper.ReservationMapper;
import com.boutaina.msreservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository repository;
    private final ReservationMapper mapper;

    // Injection des clients depuis le nouveau package 'client'
    private final SalleRestClient salleRestClient;
    private final UserRestClient userRestClient;

    @Override
    public ReservationResponseDTO create(ReservationRequestDTO dto) {

        // 1. Vérification Salle via OpenFeign (msSalles)
        SalleDTO salle = Optional.ofNullable(salleRestClient.getSalleById(dto.getSalleId()))
                .orElseThrow(() -> new ResourceNotFoundException("Impossible de réserver : La salle avec l'ID " + dto.getSalleId() + " n'existe pas."));

        // 2. Vérification User via OpenFeign (msUsers)
        UserDTO user = Optional.ofNullable(userRestClient.getUserById(dto.getUtilisateurId()))
                .orElseThrow(() -> new ResourceNotFoundException("Impossible de réserver : L'utilisateur avec l'ID " + dto.getUtilisateurId() + " n'existe pas."));

        // (Optionnel) Logique métier supplémentaire possible ici
        // Ex: if(salle.getCapacite() < 10) throw new RuntimeException("Salle trop petite");

        // 3. Création
        Reservation reservation = mapper.toEntity(dto);
        return mapper.toDto(repository.save(reservation));
    }

    @Override
    public List<ReservationResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public ReservationResponseDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Réservation introuvable"));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Réservation introuvable");
        }
        repository.deleteById(id);
    }
}