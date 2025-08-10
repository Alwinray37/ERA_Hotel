package com.erahotel.era_backend.mapper;

import com.erahotel.era_backend.dto.RoomDto;
import com.erahotel.era_backend.entity.Room;

public class RoomMapper {

    public static RoomDto mapToRoomDto(Room r) {
        if (r == null) return null;
        RoomDto dto = new RoomDto();
        dto.setRoomId(r.getRoomId());
        dto.setRoomNumber(r.getRoomNumber());
        dto.setRoomType(r.getRoomType());
        dto.setPricePerNight(r.getPricePerNight());
        dto.setReservations(r.getReservations());
        return dto;
    }

    public static Room mapToRoom(RoomDto dto) {
        if (dto == null) return null;
        Room r = new Room();
        r.setRoomId(dto.getRoomId());
        r.setRoomNumber(dto.getRoomNumber());
        r.setRoomType(dto.getRoomType());
        r.setPricePerNight(dto.getPricePerNight());
        r.setReservations(dto.getReservations());
        return r;
    }
}
