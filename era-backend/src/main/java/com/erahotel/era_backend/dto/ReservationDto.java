package com.erahotel.era_backend.dto;

import com.erahotel.era_backend.entity.Guest;
import com.erahotel.era_backend.entity.Room;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ReservationDto {
    private String reservationId;
//    private Guest guest;
//    private Room room;

    private String guestEmail;
    private String roomNumber;

    private Date startDate;
    private Date endDate;
    private BigDecimal totalCost;
    private String status;
}