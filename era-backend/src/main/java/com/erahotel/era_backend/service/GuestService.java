package com.erahotel.era_backend.service;

import com.erahotel.era_backend.dto.GuestDto;

import java.util.List;

/**
 * Service interface for managing guests.
 * <p>
 * Defines business operations for creating, retrieving, updating, and deleting guests,
 * as well as managing guest reservations.
 * </p>
 *
 * @author Alwin Roble
 * @see com.erahotel.era_backend.dto.GuestDto
 */
public interface GuestService {

    /**
     * Creates a new guest.
     *
     * @param guestDto the data transfer object containing guest information
     * @return the created guest as a {@link GuestDto}
     */
    GuestDto createGuest(GuestDto guestDto);

    /**
     * Retrieves a guest by their unique ID.
     *
     * @param guestId the ID of the guest to retrieve
     * @return the guest as a {@link GuestDto}
     */
    GuestDto getGuestById(Long guestId);

    /**
     * Retrieves all guests.
     *
     * @return a list of all guests as {@link GuestDto} objects
     */
    List<GuestDto> getAllGuests();

    /**
     * Updates the information of an existing guest.
     *
     * @param guestId      the ID of the guest to update
     * @param updatedGuest the updated guest data
     * @return the updated guest as a {@link GuestDto}
     */
    GuestDto updateGuest(Long guestId, GuestDto updatedGuest);

    /**
     * Deletes a guest by their unique ID.
     *
     * @param guestId the ID of the guest to delete
     */
    void deleteGuest(Long guestId);

    /**
     * Retrieves a guest by their email address.
     *
     * @param email the email address of the guest
     * @return the guest as a {@link GuestDto}
     */
    GuestDto getGuestByEmail(String email);

    /**
     * Adds a reservation ID to the guest's list of reservations.
     *
     * @param guestId       the ID of the guest
     * @param reservationId the reservation ID to add
     * @return the updated guest as a {@link GuestDto}
     */
    GuestDto addReservation(Long guestId, String reservationId);

}
