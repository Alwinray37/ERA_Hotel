package com.erahotel.era_backend.service.impl;

import com.erahotel.era_backend.dto.ReservationDto;
import com.erahotel.era_backend.entity.Reservation;
import com.erahotel.era_backend.mapper.ReservationMapper;
import com.erahotel.era_backend.repository.ReservationRepository;
import com.erahotel.era_backend.service.ReservationService;
import com.erahotel.era_backend.utils.ReservationIdGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link ReservationService} interface.
 * <p>
 * Provides business logic for managing reservations, including creation, retrieval,
 * updating, and deletion. Ensures unique custom reservation IDs and uses
 * {@link ReservationRepository} for data access and {@link ReservationMapper} for entity-DTO conversion.
 * </p>
 *
 * @author alwin roble
 * @see com.erahotel.era_backend.service.ReservationService
 * @see com.erahotel.era_backend.repository.ReservationRepository
 * @see com.erahotel.era_backend.mapper.ReservationMapper
 */
@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    /**
     * Creates a new reservation, ensuring a unique custom reservation ID.
     *
     * @param reservationDto the data transfer object containing reservation information
     * @return the created reservation as a {@link ReservationDto}
     */
    @Override
    public ReservationDto createReservation(ReservationDto reservationDto) {
        Reservation reservation = ReservationMapper.mapToEntity(reservationDto);

        // Ensure custom ID is set (in case @PrePersist doesn’t trigger)
        if (reservation.getReservationId() == null) {
            String id;
            do {
                id = ReservationIdGenerator.generateId();
            } while (reservationRepository.existsById(id)); // Prevent duplicates
            reservation.setReservationId(id);
        }

        Reservation savedReservation = reservationRepository.save(reservation);
        return ReservationMapper.mapToDto(savedReservation);
    }

    /**
     * Retrieves a reservation by its unique ID.
     *
     * @param reservationId the ID of the reservation to retrieve
     * @return the reservation as a {@link ReservationDto}
     * @throws RuntimeException if no reservation is found with the given ID
     */
    @Override
    public ReservationDto getReservationById(String reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        return ReservationMapper.mapToDto(reservation);
    }

    /**
     * Retrieves all reservations.
     *
     * @return a list of all reservations as {@link ReservationDto} objects
     */
    @Override
    public List<ReservationDto> getAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(ReservationMapper::mapToDto)
                .collect(Collectors.toList());
    }

    /**
     * Deletes a reservation by its unique ID.
     *
     * @param reservationId the ID of the reservation to delete
     */
    @Override
    public void deleteReservation(String reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    /**
     * Updates the information of an existing reservation.
     *
     * @param resId           the ID of the reservation to update
     * @param reservationDto  the updated reservation data
     * @return the updated reservation as a {@link ReservationDto}
     * @throws RuntimeException if no reservation is found with the given ID
     */
    @Override
    public ReservationDto updateReservation(String resId, ReservationDto reservationDto) {
        Reservation existingReservation = reservationRepository.findById(resId)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id: " + resId));

        existingReservation.setGuestEmail(reservationDto.getGuestEmail());
        existingReservation.setRoomNumber(reservationDto.getRoomNumber());
        existingReservation.setStartDate(reservationDto.getStartDate());
        existingReservation.setEndDate(reservationDto.getEndDate());
        existingReservation.setTotalCost(reservationDto.getTotalCost());
        existingReservation.setStatus(reservationDto.getStatus());

        Reservation updatedReservation = reservationRepository.save(existingReservation);
        return ReservationMapper.mapToDto(updatedReservation);
    }
}
