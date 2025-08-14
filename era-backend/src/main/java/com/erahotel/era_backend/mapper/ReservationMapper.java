package com.erahotel.era_backend.mapper;

import com.erahotel.era_backend.dto.ReservationDto;
import com.erahotel.era_backend.entity.Reservation;

/**
 * Utility class for mapping between Reservation entity and ReservationDto.
 * <p>
 * Provides static methods to convert Reservation entities to ReservationDto objects and vice versa.
 * </p>
 *
 * @author alwin roble
 * @see com.erahotel.era_backend.entity.Reservation
 * @see com.erahotel.era_backend.dto.ReservationDto
 */
public class ReservationMapper {

    /**
     * Maps a Reservation entity to a ReservationDto.
     *
     * @param reservation the Reservation entity to map
     * @return a ReservationDto containing the data from the Reservation entity
     */
    public static ReservationDto mapToDto(Reservation reservation) {
        ReservationDto dto = new ReservationDto();
        dto.setReservationId(reservation.getReservationId());
        dto.setGuestEmail(reservation.getGuestEmail());
        dto.setRoomNumber(reservation.getRoomNumber());
        dto.setStartDate(reservation.getStartDate());
        dto.setEndDate(reservation.getEndDate());
        dto.setTotalCost(reservation.getTotalCost());
        dto.setStatus(reservation.getStatus());
        return dto;
    }

    /**
     * Maps a ReservationDto to a Reservation entity.
     *
     * @param dto the ReservationDto to map
     * @return a Reservation entity containing the data from the ReservationDto
     */
    public static Reservation mapToEntity(ReservationDto dto) {
        Reservation reservation = new Reservation();
        reservation.setReservationId(dto.getReservationId()); // might be null on create
        reservation.setGuestEmail(dto.getGuestEmail());
        reservation.setRoomNumber(dto.getRoomNumber());
        reservation.setStartDate(dto.getStartDate());
        reservation.setEndDate(dto.getEndDate());
        reservation.setTotalCost(dto.getTotalCost());
        reservation.setStatus(dto.getStatus());
        return reservation;
    }
}
