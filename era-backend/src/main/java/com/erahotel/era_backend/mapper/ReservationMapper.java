package com.erahotel.era_backend.mapper;

import com.erahotel.era_backend.dto.ReservationDto;
import com.erahotel.era_backend.entity.Reservation;

public class ReservationMapper {

    public static ReservationDto mapToDto(Reservation reservation) {
        ReservationDto dto = new ReservationDto();
        dto.setReservationId(reservation.getReservationId());
        dto.setGuest(reservation.getGuest());
        dto.setRoom(reservation.getRoom());
        dto.setStartDate(reservation.getStartDate());
        dto.setEndDate(reservation.getEndDate());
        dto.setTotalCost(reservation.getTotalCost());
        dto.setStatus(reservation.getStatus());
        return dto;
    }

    public static Reservation mapToEntity(ReservationDto dto) {
        Reservation reservation = new Reservation();
        reservation.setReservationId(dto.getReservationId()); // might be null on create
        reservation.setGuest(dto.getGuest());
        reservation.setRoom(dto.getRoom());
        reservation.setStartDate(dto.getStartDate());
        reservation.setEndDate(dto.getEndDate());
        reservation.setTotalCost(dto.getTotalCost());
        reservation.setStatus(dto.getStatus());
        return reservation;
    }
}