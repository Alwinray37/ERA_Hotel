package com.erahotel.era_backend.repository;

import com.erahotel.era_backend.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Reservation entities.
 * <p>
 * Extends {@link JpaRepository} to provide CRUD operations for the Reservation entity.
 * </p>
 *
 * @author alwin roble
 * @see com.erahotel.era_backend.entity.Reservation
 */
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    // Additional custom query methods can be defined here if needed
}
