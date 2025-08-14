package com.erahotel.era_backend.mapper;

import com.erahotel.era_backend.dto.GuestDto;
import com.erahotel.era_backend.entity.Guest;

/**
 * Utility class for mapping between Guest entity and GuestDto.
 * <p>
 * Provides static methods to convert Guest entities to GuestDto objects and vice versa.
 * </p>
 *
 * @author Alwin Roble
 * @see com.erahotel.era_backend.entity.Guest
 * @see com.erahotel.era_backend.dto.GuestDto
 */
public class GuestMapper {

    /**
     * Maps a Guest entity to a GuestDto.
     *
     * @param guest the Guest entity to map
     * @return a GuestDto containing the data from the Guest entity
     */
    public static GuestDto mapToGuestDto(Guest guest) {
        return new GuestDto(
                guest.getGuestId(),
                guest.getName(),
                guest.getEmail(),
                guest.getPhone(),
                guest.getGuestReservations()
        );
    }

    /**
     * Maps a GuestDto to a Guest entity.
     *
     * @param guestDto the GuestDto to map
     * @return a Guest entity containing the data from the GuestDto
     */
    public static Guest mapToGuest(GuestDto guestDto) {
        return new Guest(
                guestDto.getGuestId(),
                guestDto.getName(),
                guestDto.getEmail(),
                guestDto.getPhone(),
                guestDto.getGuestReservations()
        );
    }
}
