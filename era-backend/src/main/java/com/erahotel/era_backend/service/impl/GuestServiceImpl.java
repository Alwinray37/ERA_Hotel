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

/**
 * Implementation of the {@link GuestService} interface.
 * <p>
 * Provides business logic for managing guests, including creation, retrieval, updating, and deletion.
 * Uses {@link GuestRepository} for data access and {@link GuestMapper} for entity-DTO conversion.
 * </p>
 *
 * @author Alwin Roble
 * @see com.erahotel.era_backend.service.GuestService
 * @see com.erahotel.era_backend.repository.GuestRepository
 * @see com.erahotel.era_backend.mapper.GuestMapper
 */
@Service
@AllArgsConstructor
public class GuestServiceImpl implements GuestService {
    private GuestRepository guestRepository;

    /**
     * Creates a new guest.
     *
     * @param guestDto the data transfer object containing guest information
     * @return the created guest as a {@link GuestDto}
     */
    @Override
    public GuestDto createGuest(GuestDto guestDto) {
        Guest guest = GuestMapper.mapToGuest(guestDto);
        Guest savedGuest = guestRepository.save(guest);
        return GuestMapper.mapToGuestDto(savedGuest);
    }

    /**
     * Retrieves a guest by their unique ID.
     *
     * @param guestId the ID of the guest to retrieve
     * @return the guest as a {@link GuestDto}
     * @throws ResourceNotFoundException if no guest is found with the given ID
     */
    @Override
    public GuestDto getGuestById(Long guestId) {
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found by Id: " + guestId));
        return GuestMapper.mapToGuestDto(guest);
    }

    /**
     * Retrieves all guests.
     *
     * @return a list of all guests as {@link GuestDto} objects
     */
    @Override
    public List<GuestDto> getAllGuests() {
        List<Guest> guests = guestRepository.findAll();
        return guests.stream()
                .map(GuestMapper::mapToGuestDto)
                .toList();
    }

    /**
     * Retrieves a guest by their email address.
     *
     * @param email the email address of the guest
     * @return the guest as a {@link GuestDto}
     * @throws ResourceNotFoundException if no guest is found with the given email
     */
    @Override
    public GuestDto getGuestByEmail(String email) {
        Guest guest = guestRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found with email: " + email));
        return GuestMapper.mapToGuestDto(guest);
    }

    /**
     * Updates the information of an existing guest.
     *
     * @param guestId      the ID of the guest to update
     * @param updatedGuest the updated guest data
     * @return the updated guest as a {@link GuestDto}
     * @throws RuntimeException if no guest is found with the given ID
     */
    @Override
    public GuestDto updateGuest(Long guestId, GuestDto updatedGuest) {
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new RuntimeException("Guest not found with Id: " + guestId));

        // Update guest fields
        guest.setName(updatedGuest.getName());
        guest.setPhone(updatedGuest.getPhone());
        guest.setEmail(updatedGuest.getEmail());

        // Optionally append new reservations from DTO (commented out)
        // if (updatedGuest.getGuestReservations() != null && !updatedGuest.getGuestReservations().isEmpty()) {
        //     guest.getGuestReservations().add(updatedGuest.getGuestReservations().get(0));
        // }

        Guest savedGuest = guestRepository.save(guest);
        return GuestMapper.mapToGuestDto(savedGuest);
    }

    /**
     * Adds a reservation ID to the guest's list of reservations.
     *
     * @param guestId       the ID of the guest
     * @param reservationId the reservation ID to add
     * @return the updated guest as a {@link GuestDto}
     * @throws ResourceNotFoundException if no guest is found with the given ID
     */
    @Override
    public GuestDto addReservation(Long guestId, String reservationId) {
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found"));

        guest.getGuestReservations().add(reservationId);
        guestRepository.save(guest);

        return GuestMapper.mapToGuestDto(guest);
    }

    /**
     * Deletes a guest by their unique ID.
     *
     * @param guestId the ID of the guest to delete
     * @throws RuntimeException if no guest is found with the given ID
     */
    @Override
    public void deleteGuest(Long guestId) {
        if (!guestRepository.existsById(guestId)) {
            throw new RuntimeException("Guest not found with id " + guestId);
        }
        guestRepository.deleteById(guestId);
    }
}
