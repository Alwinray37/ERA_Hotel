package com.erahotel.era_backend.service.impl;


import com.erahotel.era_backend.dto.GuestDto;
import com.erahotel.era_backend.entity.Guest;
import com.erahotel.era_backend.entity.Reservation;
import com.erahotel.era_backend.exception.ResourceNotFoundException;
import com.erahotel.era_backend.repository.GuestRepository;
import com.erahotel.era_backend.mapper.GuestMapper;
import com.erahotel.era_backend.service.GuestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow( () -> new ResourceNotFoundException("Guest not found by Id: " + guestId));
        return GuestMapper.mapToGuestDto(guest);
    }

    @Override
    public List<GuestDto> getAllGuests() {
        List<Guest> guests = guestRepository.findAll();

        return guests.stream()
                .map(GuestMapper::mapToGuestDto)
                .toList();
    }
    @Override
    public GuestDto getGuestByEmail(String email) {
        Guest guest = guestRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found with email: " + email));
        return GuestMapper.mapToGuestDto(guest);
    }

    @Override
    public GuestDto updateGuest(Long guestId, GuestDto updatedGuest) {
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new RuntimeException("Guest not found with Id: " + guestId));

        // update guest fields
        guest.setName(updatedGuest.getName());
        guest.setPhone(updatedGuest.getPhone());
        guest.setEmail(updatedGuest.getEmail());

        // append new reservations from dto
//        if((updatedGuest.getGuestReservations() != null && !updatedGuest.getGuestReservations().isEmpty())){
//            guest.getGuestReservations().add(updatedGuest.getGuestReservations().get(0));
//        }

        Guest savedGuest = guestRepository.save(guest);
        return GuestMapper.mapToGuestDto(savedGuest);
    }

    @Override
    public GuestDto addReservation(Long guestId, String reservationId) {
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found"));

        guest.getGuestReservations().add(reservationId);
        guestRepository.save(guest);

        return GuestMapper.mapToGuestDto(guest);
    }



    @Override
    public void deleteGuest(Long guestId) {
        if (!guestRepository.existsById(guestId)) {
            throw new RuntimeException("Guest not found with id " + guestId);
        }
        guestRepository.deleteById(guestId);
    }
}
