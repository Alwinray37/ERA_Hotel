package com.erahotel.era_backend.service.impl;


import com.erahotel.era_backend.dto.GuestDto;
import com.erahotel.era_backend.entity.Guest;
import com.erahotel.era_backend.repository.GuestRepository;
import com.erahotel.era_backend.mapper.GuestMapper;
import com.erahotel.era_backend.service.GuestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GuestServiceImpl implements GuestService {
    private GuestRepository guestRepository;

    @Override
    public GuestDto createGuest(GuestDto guestDto){
        Guest guest = GuestMapper.mapToGuest(guestDto);
        Guest savedGuest = guestRepository.save(guest);

        return GuestMapper.mapToGuestDto(savedGuest);
    }

    @Override
    public GuestDto getGuestById(Long guestId) {
        return null;
    }

    @Override
    public List<GuestDto> getAllGuests() {
        return List.of();
    }

    @Override
    public GuestDto updateGuest(Long guestId, GuestDto updatedGuest) {
        return null;
    }

    @Override
    public void deleteGuest(Long guestId) {

    }
}
