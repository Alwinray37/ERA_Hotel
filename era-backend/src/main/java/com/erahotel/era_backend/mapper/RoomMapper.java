package com.erahotel.era_backend.mapper;

import com.erahotel.era_backend.dto.RoomDto;
import com.erahotel.era_backend.entity.Room;

public class RoomMapper {

    public static RoomDto mapToRoomDto(Room r) {
        if (r == null) return null;
        RoomDto dto = new RoomDto();
        dto.setRoomId(r.getRoomId());
        dto.setRoomNumber(r.getRoomNumber());
        dto.setReservations(r.getRoomReservations());
        return dto;
    }

    public static Room mapToRoom(RoomDto dto) {
        if (dto == null) return null;
        Room r = new Room();
        r.setRoomId(dto.getRoomId());
        r.setRoomNumber(dto.getRoomNumber());
        r.setPrice(dto.getPricePerNight());
        r.setRoomReservations(dto.getReservations());
        return r;
    }
}
