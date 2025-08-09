package com.erahotel.era_backend.dto;

import com.erahotel.era_backend.entity.Reservation;
import com.erahotel.era_backend.entity.RoomType;

import java.math.BigDecimal;
import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RoomDto {

    private Long roomId;
    private String roomNumber;
    private String description;
    private BigDecimal price;
    private List<Reservation> roomReservations;
}
