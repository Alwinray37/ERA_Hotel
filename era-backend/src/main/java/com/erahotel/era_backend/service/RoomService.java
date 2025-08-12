package com.erahotel.era_backend.service;

import com.erahotel.era_backend.dto.RoomDto;
import com.erahotel.era_backend.entity.Room;

import java.util.List;

public interface RoomService {
    RoomDto createRoom(RoomDto roomDto);

    RoomDto getRoomById(Long roomId);

    List<RoomDto> getAllRooms();

    RoomDto updateRoom(Long roomId, RoomDto updatedRoom);

    void deleteRoom(Long roomId);

    RoomDto addReservation(Long roomId, String replace);
}
