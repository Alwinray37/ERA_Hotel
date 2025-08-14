package com.erahotel.era_backend.dto;

import java.math.BigDecimal;
import java.util.Date;

public class AdminReservationSummaryDTO {
    private String reservationId;
    private String guestName;
    private String guestEmail;
    private String guestPhone;
    private String roomNumber;
    private Date startDate;   // <-- Date
    private Date endDate;     // <-- Date
    private BigDecimal totalCost;
    private String status;

    public AdminReservationSummaryDTO(
            String reservationId,
            String guestName,
            String guestEmail,
            String guestPhone,
            String roomNumber,
            Date startDate,
            Date endDate,
            BigDecimal totalCost,
            String status
    ) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.guestEmail = guestEmail;
        this.guestPhone = guestPhone;
        this.roomNumber = roomNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
        this.status = status;
    }


}
