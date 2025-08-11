package com.erahotel.era_backend.repository;

import com.erahotel.era_backend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, String>{
}
