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

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

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

    @Override
    public ReservationDto getReservationById(String reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        return ReservationMapper.mapToDto(reservation);
    }

    @Override
    public List<ReservationDto> getAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(ReservationMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteReservation(String reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    @Override
    public ReservationDto updateReservation(String resId, ReservationDto reservationDto){
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