package com.erahotel.era_backend.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Data Transfer Object for the Reservation entity.
 * <p>
 * Used to transfer reservation data between layers without exposing the entity directly.
 * Contains reservation details such as ID, guest email, room number, dates, total cost, and status.
 * </p>
 *
 * @author alwin roble
 * @see com.erahotel.era_backend.entity.Reservation
 */
@Data
public class ReservationDto {
    /**
     * The unique identifier of the reservation (custom format: 3 letters + 3 numbers, e.g., ABC123).
     */
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
     * The status of the reservation (e.g., Confirmed, Cancelled, Finished).
     */
    private String status;
}
