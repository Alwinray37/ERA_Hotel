package com.erahotel.era_backend.mapper;

import com.erahotel.era_backend.dto.GuestDto;
import com.erahotel.era_backend.entity.Guest;

public class GuestMapper {

    public static GuestDto mapToGuestDto(Guest g) {
        if (g == null) return null;
        GuestDto dto = new GuestDto();
        dto.setGuestId(g.getGuestId());
        dto.setFirstName(g.getFirstName());
        dto.setLastName(g.getLastName());
        dto.setEmail(g.getEmail());
        dto.setPhone(g.getPhone());
        dto.setReservations(g.getReservations());
        return dto;
    }

    public static Guest mapToGuest(GuestDto dto) {
        if (dto == null) return null;
        Guest g = new Guest();
        g.setGuestId(dto.getGuestId());
        g.setFirstName(dto.getFirstName());
        g.setLastName(dto.getLastName());
        g.setEmail(dto.getEmail());
        g.setPhone(dto.getPhone());
        g.setReservations(dto.getReservations());
        return g;
    }
}
