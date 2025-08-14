package com.erahotel.era_backend.service;

import com.erahotel.era_backend.dto.RoomDto;

import java.util.List;

/**
 * Service interface for managing rooms.
 * <p>
 * Defines business operations for creating, retrieving, updating, and deleting rooms,
 * as well as adding reservations to rooms and retrieving rooms by room number.
 * </p>
 *
 * @author alwin roble
 * @see com.erahotel.era_backend.dto.RoomDto
 */
public interface RoomService {

    /**
     * Creates a new room.
     *
     * @param roomDto the data transfer object containing room information
     * @return the created room as a {@link RoomDto}
     */
    RoomDto createRoom(RoomDto roomDto);

    /**
     * Retrieves a room by its unique ID.
     *
     * @param roomId the ID of the room to retrieve
     * @return the room as a {@link RoomDto}
     */
    RoomDto getRoomById(Long roomId);

    /**
     * Retrieves all rooms.
     *
     * @return a list of all rooms as {@link RoomDto} objects
     */
    List<RoomDto> getAllRooms();

    /**
     * Updates the information of an existing room.
     *
     * @param roomId      the ID of the room to update
     * @param updatedRoom the updated room data
     * @return the updated room as a {@link RoomDto}
     */
    RoomDto updateRoom(Long roomId, RoomDto updatedRoom);

    /**
     * Deletes a room by its unique ID.
     *
     * @param roomId the ID of the room to delete
     */
    void deleteRoom(Long roomId);

    /**
     * Adds a reservation ID to the room's list of reservations.
     *
     * @param roomId  the ID of the room
     * @param resId   the reservation ID to add
     * @return the updated room as a {@link RoomDto}
     */
    RoomDto addReservation(Long roomId, String resId);

    /**
     * Retrieves a room by its room number.
     *
     * @param roomNumber the room number to search for
     * @return the room as a {@link RoomDto}
     */
    RoomDto getRoomByNumber(String roomNumber);
}
