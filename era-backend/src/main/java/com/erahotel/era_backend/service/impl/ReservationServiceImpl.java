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

        // Generate unique ID
        String id;
        do {
            id = ReservationIdGenerator.generateId();
        } while (reservationRepository.existsById(id));

        reservation.setReservationId(id);
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
}