package com.erahotel.era_backend.service;

import com.erahotel.era_backend.dto.GuestDto;

import java.util.List;

public interface GuestService {
    GuestDto createGuest(GuestDto guestDto);
    GuestDto getGuestById(Long guestId);
    List<GuestDto> getAllGuests();
    GuestDto updateGuest(Long guestId, GuestDto updatedGuest);
    void deleteGuest(Long guestId);
}
