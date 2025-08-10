package com.erahotel.era_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room")
@JsonIgnoreProperties({"reservations"})
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Column(nullable = false, unique = true)
    private String roomNumber;
    @Column(length = 2000)
    private String description;
    private BigDecimal price;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> roomReservations; // tracks all reservations for this room

    // Lombok is used here to automatically generate boilerplate code like getters,
    // setters,
    // constructors, equals(), hashCode(), and toString() methods.
    // Annotations used:
    // - @Data: Generates getters, setters, toString(), equals(), and hashCode()
    // - @NoArgsConstructor: Generates a no-argument constructor
    // - @AllArgsConstructor: Generates a constructor with all fields as parameters
}

// Room class: Defines the Room entity for our database, using lombok for
// boilerplate code for getters, setters, and constructors.
// Annotations:
// @Entity: Marks this class as a JPA entity.
// @Table(name = "rooms"): Specifies the database table name.
// @Id and @GeneratedValue: Indicate the primary key and its auto-generation
// strategy.
// Lombok annotations (@Data, @NoArgsConstructor, @AllArgsConstructor):
// Automatically generate getters, setters, constructors, and utility methods,
// reducing boilerplate code.

// RoomRepository connect with this entity to perform CRUD operations on the
// Room table in the database.
// RoomService and its implementation (RoomServiceImpl) use this entity to
// manage room data, including creating, retrieving, updating, and deleting
// rooms.
// RoomDto class is used to transfer room data between layers
