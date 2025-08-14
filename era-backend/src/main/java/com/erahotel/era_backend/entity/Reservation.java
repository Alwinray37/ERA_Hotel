package com.erahotel.era_backend.entity;

import com.erahotel.era_backend.utils.ReservationIdGenerator;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Entity class representing a reservation in the system.
 * <p>
 * Maps to the "reservations" table in the database and contains reservation details such as
 * guest email, room number, reservation dates, total cost, and status.
 * The reservation ID is a custom string (e.g., "ABC123") generated before persisting.
 * </p>
 *
 * @author alwin roble
 */
@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    /**
     * The unique identifier for the reservation (custom format: 3 letters + 3 numbers, e.g., ABC123).
     */
    @Id
    @Column(unique = true, nullable = false)
    private String reservationId;

    /**
     * The email address of the guest associated with the reservation.
     */
    private String guestEmail;

    /**
     * The room number associated with the reservation.
     */
    private String roomNumber;

    /**
     * The start date of the reservation.
     */
    private Date startDate;

    /**
     * The end date of the reservation.
     */
    private Date endDate;

    /**
     * The total cost of the reservation.
     */
    private BigDecimal totalCost;

    /**
     * The status of the reservation (e.g., confirmed, cancelled, finished).
     */
    private String status;

    /**
     * Assigns a custom reservation ID before persisting if not already set.
     */
    @PrePersist
    public void assignReservationId() {
        if (this.reservationId == null) {
            this.reservationId = ReservationIdGenerator.generateId();
        }
    }
}
