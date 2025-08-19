package com.erahotel.era_backend.repository;

import com.erahotel.era_backend.entity.Reservation;
import com.erahotel.era_backend.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

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


}
