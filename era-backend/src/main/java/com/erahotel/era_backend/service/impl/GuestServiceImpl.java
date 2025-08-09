package com.erahotel.era_backend.service.impl;


import com.erahotel.era_backend.dto.GuestDto;
import com.erahotel.era_backend.entity.Guest;
import com.erahotel.era_backend.exception.ResourceNotFoundException;
import com.erahotel.era_backend.repository.GuestRepository;
import com.erahotel.era_backend.mapper.GuestMapper;
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
    public GuestDto createGuest(GuestDto guestDto){
        Guest guest = GuestMapper.mapToGuest(guestDto);
        Guest savedGuest = guestRepository.save(guest);

        return GuestMapper.mapToGuestDto(savedGuest);
    }

    @Override
    public GuestDto getGuestById(Long guestId) {
        Guest guest = guestRepository.findById(guestId) //Lines 30- 32 that I added :in case any issues occurs -Ryley 3:53pm
                .orElseThrow(() ->
                        new ResourceNotFoundException("Guest is not found with given id : " + guestId));
        return GuestMapper.mapToGuestDto(guest); //changed null to GuestMapper.mapToGuestDto(guest); :Ran it, still works 3:53pm
    }

    @Override
    public List<GuestDto> getAllGuests() {
        List<Guest> guests = guestRepository.findAll(); //Added Line 38 -Ryley
        return guests.stream().map((guest) -> GuestMapper.mapToGuestDto(guest)) //List.of(); // in case of issues
                .collect(Collectors.toList()); //Added Line 41 -Ryley
    }

    @Override
    public GuestDto updateGuest(Long guestId, GuestDto updatedGuest) {

       Guest guest = guestRepository.findById(guestId).orElseThrow( //Line 47-49 added -Ryley
                ()-> new ResourceNotFoundException("Guest is not found with given id: " + guestId)
        );

       //guest.setGuestId(updatedGuest.getGuestId()); //not sure if this is needed or not
       guest.setFirst_name(updatedGuest.getFirstName()); //Added Lines 51-59 -Ryley
       guest.setLast_name(updatedGuest.getLastName());
       guest.setEmail(updatedGuest.getEmail());
       guest.setPhone(updatedGuest.getPhone());
       guest.setGuest_reservations(updatedGuest.getReservations());

       Guest updatedGuestObj = guestRepository.save(guest);
        return GuestMapper.mapToGuestDto(updatedGuestObj); //changed null to GuestMapper.mapToGuestDto(updatedGuestObj);
    }

    @Override
    public void deleteGuest(Long guestId) {

        Guest guest = guestRepository.findById(guestId).orElseThrow( //Line 65-66 added -Ryley
                ()-> new ResourceNotFoundException("Guest is not found with given id: " + guestId)
        );

        guestRepository.deleteById(guestId);

    }
}
