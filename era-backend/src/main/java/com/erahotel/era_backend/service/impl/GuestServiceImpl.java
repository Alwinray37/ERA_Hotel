package com.erahotel.era_backend.service.impl;

import com.erahotel.era_backend.dto.GuestDto;
import com.erahotel.era_backend.entity.Guest;
import com.erahotel.era_backend.exception.ResourceNotFoundException;
import com.erahotel.era_backend.mapper.GuestMapper;
import com.erahotel.era_backend.repository.GuestRepository;
import com.erahotel.era_backend.service.GuestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public GuestDto getGuestById(Long guestId) {
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Guest is not found with given id : " + guestId));

        return GuestMapper.maptoGuestDto(guest);
    }

    @Override
    public List<GuestDto> getAllGuests() {
       List<Guest> guests = guestRepository.findAll();
        return guests.stream().map((guest) -> GuestMapper.maptoGuestDto(guest))
                .collect(Collectors.toList());
    }
}
