package com.erahotel.era_backend.service;

import com.erahotel.era_backend.dto.ReservationDto;

import java.util.List;

/**
 * Service interface for managing reservations.
 * <p>
 * Defines business operations for creating, retrieving, updating, and deleting reservations.
 * </p>
 *
 * @author alwin roble
 * @see com.erahotel.era_backend.dto.ReservationDto
 */
public interface ReservationService {

    /**
     * Creates a new reservation.
     *
     * @param resDto the data transfer object containing reservation information
     * @return the created reservation as a {@link ReservationDto}
     */
    ReservationDto createReservation(ReservationDto resDto);

    /**
     * Retrieves a reservation by its unique ID.
     *
     * @param reservationId the ID of the reservation to retrieve
     * @return the reservation as a {@link ReservationDto}
     */
    ReservationDto getReservationById(String reservationId);

    /**
     * Retrieves all reservations.
     *
     * @return a list of all reservations as {@link ReservationDto} objects
     */
    List<ReservationDto> getAllReservations();

    /**
     * Deletes a reservation by its unique ID.
     *
     * @param reservationId the ID of the reservation to delete
     */
    void deleteReservation(String reservationId);

    /**
     * Updates the information of an existing reservation.
     *
     * @param resId           the ID of the reservation to update
     * @param reservationDto  the updated reservation data
     * @return the updated reservation as a {@link ReservationDto}
     */
    ReservationDto updateReservation(String resId, ReservationDto reservationDto);
}
