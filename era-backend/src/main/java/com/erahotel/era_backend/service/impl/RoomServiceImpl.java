package com.erahotel.era_backend.service.impl;

import com.erahotel.era_backend.dto.RoomDto;
import com.erahotel.era_backend.entity.Room;
import com.erahotel.era_backend.mapper.RoomMapper;
import com.erahotel.era_backend.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl {

    private final RoomRepository roomRepository;

<<<<<<< HEAD
    public List<RoomDto> findAll() {
        return roomRepository.findAll().stream()
                .map(RoomMapper::mapToRoomDto)
                .toList();
=======
    @Override
    public RoomDto createRoom(RoomDto roomDto) {
        Room room = RoomMapper.mapToRoom(roomDto);

        Room savedRoom =  roomRepository.save(room);

        return RoomMapper.mapToRoomDto(savedRoom);
>>>>>>> 539ae98d14fa3b1de7ad69506803434caf165626
    }

    public RoomDto findById(Long id) {
        return roomRepository.findById(id)
                .map(RoomMapper::mapToRoomDto)
                .orElse(null);
    }

    public RoomDto create(RoomDto dto) {
        Room saved = roomRepository.save(RoomMapper.mapToRoom(dto));
        return RoomMapper.mapToRoomDto(saved);
    }

<<<<<<< HEAD
    public RoomDto update(Long id, RoomDto dto) {
        Room existing = roomRepository.findById(id).orElseThrow();
        existing.setRoomNumber(dto.getRoomNumber());
        existing.setRoomType(dto.getRoomType());
        existing.setPricePerNight(dto.getPricePerNight());
        existing.setReservations(dto.getReservations());
        return RoomMapper.mapToRoomDto(roomRepository.save(existing));
=======
    @Override
    public RoomDto updateRoom(Long roomId, RoomDto updatedRoom) {
        Room room = roomRepository.findById(roomId).orElseThrow(
                () -> new ResourceNotFoundException("Room Not Found by Id:" + roomId)
        );

        room.setRoomNumber(updatedRoom.getRoomNumber());
        room.setDescription(updatedRoom.getDescription());
        room.setPrice(updatedRoom.getPrice());


        Room updatedRoomObj = roomRepository.save(room);
        return RoomMapper.mapToRoomDto(updatedRoomObj);
>>>>>>> 539ae98d14fa3b1de7ad69506803434caf165626
    }

    public void delete(Long id) {
        roomRepository.deleteById(id);
    }
<<<<<<< HEAD
=======

    @Override
    public RoomDto addReservation(Long roomId, String resId){
        Room room = roomRepository.findById(roomId)
                .orElseThrow( () -> new ResourceNotFoundException("Room not found"));
        room.getRoomReservations().add(resId);
        roomRepository.save(room);

        return RoomMapper.mapToRoomDto(room);
    }
>>>>>>> 539ae98d14fa3b1de7ad69506803434caf165626
}
