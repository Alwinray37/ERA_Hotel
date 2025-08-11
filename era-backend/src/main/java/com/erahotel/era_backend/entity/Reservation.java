package com.erahotel.era_backend.entity;

import com.erahotel.era_backend.utils.ReservationIdGenerator;
import jakarta.persistence.*;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Reservation {

    @Id
    @Column(unique = true, nullable = false)
    private String reservationId; // Custom ID like ABC123

    private String guestEmail;
    private String roomNumber;
    private Date startDate;
    private Date endDate;
    private BigDecimal totalCost;
    private String status; // confirmed, cancelled, finished

    @PrePersist
    public void assignReservationId() {
        if (this.reservationId == null) {
            this.reservationId = ReservationIdGenerator.generateId();
        }
    }
}