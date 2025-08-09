package com.erahotel.era_backend.mapper;

import com.erahotel.era_backend.dto.RoomDto;
import com.erahotel.era_backend.entity.Room;

public class RoomMapper {

    public static RoomDto mapToRoomDto(Room room){
        return new RoomDto(
            room.getRoomId(),
            room.getRoomNumber(),
            room.getDescription(),
            room.getPrice(),
            room.getRoomReservations()
        );
    }

    public static Room mapToRoom(RoomDto roomDto){
        return new Room(
                roomDto.getRoomId(),
                roomDto.getRoomNumber(),
                roomDto.getDescription(),
                roomDto.getPrice(),
                roomDto.getRoomReservations()
        );
    }
}
