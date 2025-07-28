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

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;

    @Override
    public RoomDto createRoom(RoomDto roomDto) {

        Room room = RoomMapper.mapToRoom(roomDto);
        Room savedRoom =  roomRepository.save(room);

        return RoomMapper.mapToRoomDto(savedRoom);
    }

    @Override
    public RoomDto getRoomById(Long roomId) {

        Room room =  roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room is not found with the given ID" + roomId));
        return RoomMapper.mapToRoomDto(room);
    }

    @Override
    public List<RoomDto> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream().map((Room room)->RoomMapper.mapToRoomDto(room)).collect(Collectors.toList());
    }

    @Override
    public RoomDto updateRoom(Long roomId, RoomDto updatedRoom) {
        Room room = roomRepository.findById(roomId).orElseThrow(
                () -> new ResourceNotFoundException("Room Not Found by Id:" + roomId)
        );

        room.setRoomNumber(updatedRoom.getRoomNumber());
        room.setRoomType(updatedRoom.getRoomType());
        room.setNightlyPrice(updatedRoom.getNightlyPrice());
        room.setAvailable(updatedRoom.isAvailable());
        room.setMaxOccupancy(updatedRoom.getMaxOccupancy());
        room.setRoomDescription(updatedRoom.getRoomDescription());
        room.setAmenities(updatedRoom.getAmenities());
        room.setReservations(updatedRoom.getReservations());

        Room updatedRoomObj = roomRepository.save(room);
        return RoomMapper.mapToRoomDto(updatedRoomObj);
    }

    @Override
    public void deleteRoom(Long roomId) {
        Room room =  roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room is not found with the given ID" + roomId));

        roomRepository.deleteById(roomId);
    }


}
