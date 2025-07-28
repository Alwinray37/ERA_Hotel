package com.erahotel.era_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rooms") // specifies the table name in mySql

public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )

    private long roomId;
    private int roomNumber;
    private RoomType roomType;
    private double nightlyPrice;
    private boolean isAvailable;
    private int maxOccupancy;
    private String roomDescription;
    private List<String> amenities;
    private List<String> reservations; // tracks all reservations for this room

    // Lombok is used here to automatically generate boilerplate code like getters, setters,
    // constructors, equals(), hashCode(), and toString() methods.
    // Annotations used:
    // - @Data: Generates getters, setters, toString(), equals(), and hashCode()
    // - @NoArgsConstructor: Generates a no-argument constructor
    // - @AllArgsConstructor: Generates a constructor with all fields as parameters
}

