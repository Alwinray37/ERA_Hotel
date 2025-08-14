package com.erahotel.era_backend.mapper;

import com.erahotel.era_backend.dto.RoomDto;
import com.erahotel.era_backend.entity.Room;

/**
 * Utility class for mapping between Room entity and RoomDto.
 * <p>
 * Provides static methods to convert Room entities to RoomDto objects and vice versa.
 * </p>
 *
 * @author alwin roble
 * @see com.erahotel.era_backend.entity.Room
 * @see com.erahotel.era_backend.dto.RoomDto
 */
public class RoomMapper {

    /**
     * Maps a Room entity to a RoomDto.
     *
     * @param room the Room entity to map
     * @return a RoomDto containing the data from the Room entity
     */
    public static RoomDto mapToRoomDto(Room room) {
        return new RoomDto(
                room.getRoomId(),
                room.getRoomNumber(),
                room.getDescription(),
                room.getPrice(),
                room.getRoomReservations()
        );
    }

    /**
     * Maps a RoomDto to a Room entity.
     *
     * @param roomDto the RoomDto to map
     * @return a Room entity containing the data from the RoomDto
     */
    public static Room mapToRoom(RoomDto roomDto) {
        return new Room(
                roomDto.getRoomId(),
                roomDto.getRoomNumber(),
                roomDto.getDescription(),
                roomDto.getPrice(),
                roomDto.getRoomReservations()
        );
    }
}
