package com.erahotel.era_backend.service.impl;

import com.erahotel.era_backend.dto.RoomDto;
import com.erahotel.era_backend.entity.Room;
import com.erahotel.era_backend.exception.ResourceNotFoundException;
import com.erahotel.era_backend.mapper.RoomMapper;
import com.erahotel.era_backend.repository.RoomRepository;
import com.erahotel.era_backend.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link RoomService} interface.
 * <p>
 * Provides business logic for managing rooms, including creation, retrieval,
 * updating, deletion, and adding reservations to rooms.
 * Uses {@link RoomRepository} for data access and {@link RoomMapper} for entity-DTO conversion.
 * </p>
 *
 * @author alwin roble
 * @see com.erahotel.era_backend.service.RoomService
 * @see com.erahotel.era_backend.repository.RoomRepository
 * @see com.erahotel.era_backend.mapper.RoomMapper
 */
@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;

    /**
     * Creates a new room.
     *
     * @param roomDto the data transfer object containing room information
     * @return the created room as a {@link RoomDto}
     */
    @Override
    public RoomDto createRoom(RoomDto roomDto) {
        Room room = RoomMapper.mapToRoom(roomDto);
        Room savedRoom = roomRepository.save(room);
        return RoomMapper.mapToRoomDto(savedRoom);
    }

    /**
     * Retrieves a room by its unique ID.
     *
     * @param roomId the ID of the room to retrieve
     * @return the room as a {@link RoomDto}
     * @throws ResourceNotFoundException if no room is found with the given ID
     */
    @Override
    public RoomDto getRoomById(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room is not found with the given ID " + roomId));
        return RoomMapper.mapToRoomDto(room);
    }

    /**
     * Retrieves a room by its room number.
     *
     * @param roomNum the room number to search for
     * @return the room as a {@link RoomDto}
     * @throws ResourceNotFoundException if no room is found with the given room number
     */
    @Override
    public RoomDto getRoomByNumber(String roomNum) {
        Room room = roomRepository.findByRoomNumber(roomNum)
                .orElseThrow(() -> new ResourceNotFoundException("Room is not found with the given room number " + roomNum));
        return RoomMapper.mapToRoomDto(room);
    }

    /**
     * Retrieves all rooms.
     *
     * @return a list of all rooms as {@link RoomDto} objects
     */
    @Override
    public List<RoomDto> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream()
                .map(RoomMapper::mapToRoomDto)
                .collect(Collectors.toList());
    }

    /**
     * Updates the information of an existing room.
     *
     * @param roomId      the ID of the room to update
     * @param updatedRoom the updated room data
     * @return the updated room as a {@link RoomDto}
     * @throws ResourceNotFoundException if no room is found with the given ID
     */
    @Override
    public RoomDto updateRoom(Long roomId, RoomDto updatedRoom) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room Not Found by Id: " + roomId));

        room.setRoomNumber(updatedRoom.getRoomNumber());
        room.setDescription(updatedRoom.getDescription());
        room.setPrice(updatedRoom.getPrice());

        Room updatedRoomObj = roomRepository.save(room);
        return RoomMapper.mapToRoomDto(updatedRoomObj);
    }

    /**
     * Deletes a room by its unique ID.
     *
     * @param roomId the ID of the room to delete
     * @throws ResourceNotFoundException if no room is found with the given ID
     */
    @Override
    public void deleteRoom(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room is not found with the given ID " + roomId));
        roomRepository.deleteById(roomId);
    }

    /**
     * Adds a reservation ID to the room's list of reservations.
     *
     * @param roomId the ID of the room
     * @param resId  the reservation ID to add
     * @return the updated room as a {@link RoomDto}
     * @throws ResourceNotFoundException if no room is found with the given ID
     */
    @Override
    public RoomDto addReservation(Long roomId, String resId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        room.getRoomReservations().add(resId);
        roomRepository.save(room);
        return RoomMapper.mapToRoomDto(room);
    }
}
