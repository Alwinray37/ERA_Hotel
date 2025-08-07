package com.erahotel.era_backend.mapper;

import com.erahotel.era_backend.dto.GuestDto;
import com.erahotel.era_backend.entity.Guest;

public class GuestMapper {

    public static GuestDto mapToGuestDto(Guest guest){
        return new GuestDto(
                guest.getGuestId(),
                guest.getFirst_name(),
                guest.getFirst_name(),
                guest.getEmail(),
                guest.getPhone(),
                guest.getGuest_reservations()
        );
    }
    public static Guest mapToGuest(GuestDto guestDto){
        return new Guest(
                guestDto.getGuestId(),
                guestDto.getFirstName(),
                guestDto.getLastName(),
                guestDto.getEmail(),
                guestDto.getPhone(),
                guestDto.getReservations()
        );
    }
}
