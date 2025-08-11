package com.erahotel.era_backend.entity;

import com.erahotel.era_backend.entity.Guest;
import com.erahotel.era_backend.entity.Room;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservations")

public class Reservation {
    @Id
    @Column(unique = true, nullable = false)
    // this tells JPA that reservationId is the primary key, must be unique, and it will be provided manually
    // custom ID 3 letters and 3 numbers (ABC123)
    private String reservationId;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonIgnore
    private Room room;

    private Date startDate;
    private Date endDate;
    private BigDecimal totalCost;
    private String status; // Confirmed, Cancelled, Finished

}
