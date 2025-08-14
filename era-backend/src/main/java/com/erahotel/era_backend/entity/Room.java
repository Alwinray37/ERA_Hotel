package com.erahotel.era_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Entity class representing a room in the hotel reservation system.
 * <p>
 * Maps to the "rooms" table in the database and contains room details such as
 * room number, description, price, and a list of reservation IDs associated with the room.
 * </p>
 * <p>
 * Lombok annotations are used to automatically generate boilerplate code:
 * <ul>
 *   <li>{@link Data} - Generates getters, setters, toString(), equals(), and hashCode()</li>
 *   <li>{@link NoArgsConstructor} - Generates a no-argument constructor</li>
 *   <li>{@link AllArgsConstructor} - Generates a constructor with all fields as parameters</li>
 * </ul>
 * </p>
 *
 * @author alwin roble
 * @see com.erahotel.era_backend.repository.RoomRepository
 * @see com.erahotel.era_backend.service.RoomService
 * @see com.erahotel.era_backend.dto.RoomDto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rooms") // specifies the table name in MySQL
public class Room {

    /**
     * The unique identifier for the room.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    /**
     * The room number (e.g., "A100").
     */
    private String roomNumber;

    /**
     * The description of the room (e.g., amenities, bed type).
     * Limited to 2000 characters in the database.
     */
    @Column(length = 2000)
    private String description;

    /**
     * The price per night for the room.
     */
    private BigDecimal price;

    /**
     * A list of reservation IDs associated with the room.
     * Each entry in the list represents a reservation for this room.
     */
    @ElementCollection
    private List<String> roomReservations;
}
