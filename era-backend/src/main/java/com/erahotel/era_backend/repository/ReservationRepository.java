package com.erahotel.era_backend.repository;

import com.erahotel.era_backend.dto.AdminReservationSummaryDTO;
import com.erahotel.era_backend.entity.Reservation;
import com.erahotel.era_backend.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, String> {

    @Query("""
        SELECT new com.erahotel.era_backend.dto.AdminReservationSummaryDTO(
            r.reservationId,
            COALESCE(g.name, ''),
            r.guestEmail,
            COALESCE(g.phone, ''),
            r.roomNumber,
            r.startDate,
            r.endDate,
            r.totalCost,
            r.status
        )
        FROM Reservation r
        LEFT JOIN Guest g ON g.email = r.guestEmail
        ORDER BY r.startDate DESC
    """)
    List<AdminReservationSummaryDTO> findAdminReservationSummaries();
}
