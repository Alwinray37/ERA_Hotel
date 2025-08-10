package com.erahotel.era_backend.dto;

import com.erahotel.era_backend.entity.Reservation;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class RoomDto {
    private Long roomId;
    private String roomNumber;
    private String roomType;
    private BigDecimal pricePerNight;
    private List<Reservation> reservations;
}
