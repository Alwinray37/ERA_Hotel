package com.erahotel.era_backend.controller;

import com.erahotel.era_backend.dto.RoomDto;
import com.erahotel.era_backend.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing rooms.
 * <p>
 * Provides endpoints for creating, retrieving, updating, and deleting rooms,
 * as well as managing room reservations.
 * </p>
 *
 * @author Alwin Roble
 * @see com.erahotel.era_backend.dto.RoomDto
 * @see com.erahotel.era_backend.service.RoomService
 */
@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private RoomService roomService;

    /**
     * Creates a new room.
     *
     * @param roomDto the data transfer object containing room information
     * @return the created room as a {@link RoomDto}
     */
    @PostMapping
    public ResponseEntity<RoomDto> createRoom(@RequestBody RoomDto roomDto) {
        RoomDto savedRoom = roomService.createRoom(roomDto);
        return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
    }

    /**
     * Retrieves a room by its unique ID.
     *
     * @param roomId the ID of the room to retrieve
     * @return the room as a {@link RoomDto}
     */
    @GetMapping("{id}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable("id") Long roomId) {
        RoomDto roomDto = roomService.getRoomById(roomId);
        return ResponseEntity.ok(roomDto);
    }

    /**
     * Retrieves a room by its room number.
     *
     * @param roomNumber the room number to search for
     * @return the room as a {@link RoomDto}
     */
    @GetMapping("/room/{roomNumber}")
    public ResponseEntity<RoomDto> getRoomByNumber(@PathVariable("roomNumber") String roomNumber) {
        RoomDto roomDto = roomService.getRoomByNumber(roomNumber);
        return ResponseEntity.ok(roomDto);
    }

    /**
     * Retrieves all rooms.
     *
     * @return a list of all rooms as {@link RoomDto} objects
     */
    @GetMapping
    public ResponseEntity<List<RoomDto>> getAllRooms() {
        List<RoomDto> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    /**
     * Updates the information of an existing room.
     *
     * @param roomId      the ID of the room to update
     * @param updatedRoom the updated room data
     * @return the updated room as a {@link RoomDto}
     */
    @PutMapping("{id}")
    public ResponseEntity<RoomDto> updateRoom(@PathVariable("id") Long roomId, @RequestBody RoomDto updatedRoom) {
        RoomDto roomDto = roomService.updateRoom(roomId, updatedRoom);
        return ResponseEntity.ok(roomDto);
    }

    /**
     * Deletes a room by its unique ID.
     *
     * @param roomId the ID of the room to delete
     * @return a confirmation message
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable("id") Long roomId) {
        roomService.deleteRoom(roomId);
        return ResponseEntity.ok("Room Deleted Successfully.");
    }

    /**
     * Adds a reservation ID to the room's list of reservations.
     *
     * @param roomId        the ID of the room
     * @param reservationId the reservation ID to add
     * @return the updated room as a {@link RoomDto}
     */
    @PostMapping("/{id}/reservations")
    public ResponseEntity<RoomDto> addReservationToRoom(
            @PathVariable("id") Long roomId,
            @RequestBody String reservationId) {
        RoomDto updatedRoom = roomService.addReservation(roomId, reservationId.replace("\"", ""));
        return ResponseEntity.ok(updatedRoom);
    }
}
