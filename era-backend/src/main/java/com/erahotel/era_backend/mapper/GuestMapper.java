package com.erahotel.era_backend.mapper;

import com.erahotel.era_backend.dto.GuestDto;
import com.erahotel.era_backend.entity.Guest;

public class GuestMapper {

    public static GuestDto mapToGuestDto(Guest guest){
        return new GuestDto(
                guest.getGuestId(),
                guest.getName(),
                guest.getEmail(),
                guest.getPhone(),
                guest.getGuestReservations()
        );
    }
    public static Guest mapToGuest(GuestDto guestDto){
        return new Guest(
                guestDto.getGuestId(),
                guestDto.getName(),
                guestDto.getEmail(),
                guestDto.getPhone(),
                guestDto.getGuestReservations()
        );
    }
}
