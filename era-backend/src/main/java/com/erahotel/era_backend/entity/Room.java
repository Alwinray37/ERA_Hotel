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

// Room class: Defines the Room entity for our database, using lombok for boilerplate code for getters, setters, and constructors.
// Annotations:
    // @Entity: Marks this class as a JPA entity.
    // @Table(name = "rooms"): Specifies the database table name.
    // @Id and @GeneratedValue: Indicate the primary key and its auto-generation strategy.
    // Lombok annotations (@Data, @NoArgsConstructor, @AllArgsConstructor): Automatically generate getters, setters, constructors, and utility methods, reducing boilerplate code.

// RoomRepository connect with this entity to perform CRUD operations on the Room table in the database.
// RoomService and its implementation (RoomServiceImpl) use this entity to manage room data, including creating, retrieving, updating, and deleting rooms.
// RoomDto class is used to transfer room data between layers 
