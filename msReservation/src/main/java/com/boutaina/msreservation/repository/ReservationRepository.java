package com.boutaina.msreservation.repository;

import com.boutaina.msreservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findBySalleId(Long salleId);
    List<Reservation> findByUtilisateurId(Long utilisateurId);
}
