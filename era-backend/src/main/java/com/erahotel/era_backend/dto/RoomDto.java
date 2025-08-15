package com.erahotel.era_backend.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.*;

/**
 * Data Transfer Object for the Room entity.
 * <p>
 * Used to transfer room data between layers without exposing the entity directly.
 * Contains room details such as ID, room number, description, price, and a list of reservation IDs associated with the room.
 * </p>
 *
 * @author alwin roble
 * @see com.erahotel.era_backend.entity.Room
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {

    /**
     * The unique identifier of the room.
     */
    private Long roomId;

    /**
     * The room number (e.g., "A100").
     */
    private String roomNumber;

    /**
     * The description of the room (e.g., amenities, bed type).
     */
    private String description;

    /**
     * The price per night for the room.
     */
    private BigDecimal price;

    /** stores the room type */
    private String type;

    /** stores the base url for the room img to be implemented in viewRooms component
     * "../assets/Images/Room {101}.jpg"*/
    private String roomImgUrl;

    /**
     * A list of reservation IDs associated with the room.
     */
    private List<String> roomReservations;
}
