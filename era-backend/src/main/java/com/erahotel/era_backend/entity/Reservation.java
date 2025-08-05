package com.erahotel.era_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservation")

public class Reservation {
    private int reservationId;
    public Guest guest;
    public Room room;
    public Date checkin;
    public Date checkout;
    public String status;

}
