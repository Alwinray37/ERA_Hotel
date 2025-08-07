package com.erahotel.era_backend.mapper;

import com.erahotel.era_backend.dto.RoomDto;
import com.erahotel.era_backend.entity.Room;

public class RoomMapper {

    public static RoomDto mapToRoomDto(Room room){
        return new RoomDto(
            room.getRoomId(),
            room.getRoomNumber(),
            room.getRoomType(),
            room.getNightlyPrice(),
            room.getMaxOccupancy(),
            room.getRoomDescription(),
            room.getAmenities(),
            room.getReservations()
        );
    }

    public static Room mapToRoom(RoomDto roomDto){
        return new Room(
                roomDto.getRoomId(),
                roomDto.getRoomNumber(),
                roomDto.getRoomType(),
                roomDto.getNightlyPrice(),
                roomDto.getMaxOccupancy(),
                roomDto.getRoomDescription(),
                roomDto.getRoomAmenities(),
                roomDto.getRoomReservations()
        );
    }
}
