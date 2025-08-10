package com.erahotel.era_backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AdminReservationDTO(
        Long reservationId,
        LocalDate checkIn,
        LocalDate checkOut,
        String status,
        BigDecimal totalAmount,

        Long guestId,
        String guestFirstName,
        String guestLastName,
        String guestEmail,
        String guestPhone,

        Long roomId,
        String roomNumber,
        String roomType,
        BigDecimal pricePerNight
) { }
