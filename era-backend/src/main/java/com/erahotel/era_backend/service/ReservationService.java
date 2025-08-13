package com.erahotel.era_backend.service;

import com.erahotel.era_backend.dto.ReservationDto;

import java.util.List;

public interface ReservationService {
    ReservationDto createReservation(ReservationDto resDto);
    ReservationDto getReservationById(String reservationId);
    List<ReservationDto> getAllReservations();
    void deleteReservation(String reservationId);
    ReservationDto updateReservation(String resId, ReservationDto reservationDto);
}
