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

    public List<RoomDto> findAll() {
        return roomRepository.findAll().stream()
                .map(RoomMapper::mapToRoomDto)
                .toList();
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

    public RoomDto update(Long id, RoomDto dto) {
        Room existing = roomRepository.findById(id).orElseThrow();
        existing.setRoomNumber(dto.getRoomNumber());
        existing.setRoomType(dto.getRoomType());
        existing.setPricePerNight(dto.getPricePerNight());
        existing.setReservations(dto.getReservations());
        return RoomMapper.mapToRoomDto(roomRepository.save(existing));
    }

    public void delete(Long id) {
        roomRepository.deleteById(id);
    }
}
