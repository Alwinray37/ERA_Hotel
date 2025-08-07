package com.erahotel.era_backend.service.impl;

import com.erahotel.era_backend.dto.GuestDto;
import com.erahotel.era_backend.entity.Guest;
import com.erahotel.era_backend.mapper.GuestMapper;
import com.erahotel.era_backend.repository.GuestRepository;
import com.erahotel.era_backend.service.GuestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GuestServiceImpl implements GuestService {

    private GuestRepository guestRepository;

    @Override
    public GuestDto createGuest(GuestDto guestDto) {

        Guest guest = GuestMapper.mapToGuest(guestDto);
        Guest savedGuest = guestRepository.save(guest);
        return GuestMapper.maptoGuestDto(savedGuest);
    }
}
