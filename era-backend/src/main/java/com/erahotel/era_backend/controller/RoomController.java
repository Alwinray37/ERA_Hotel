package com.erahotel.era_backend.controller;

import com.erahotel.era_backend.dto.RoomDto;
import com.erahotel.era_backend.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private RoomService roomService;

    // build Add Room REST Api
    @PostMapping
    public ResponseEntity<RoomDto> createRoom(@RequestBody RoomDto roomDto){
        RoomDto savedRoom = roomService.createRoom(roomDto);
        return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
    }

    // build GET room REST api
    @GetMapping("{id}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable("id") Long roomId){
        RoomDto roomDto = roomService.getRoomById(roomId);
        return ResponseEntity.ok(roomDto);
    }

    // build get all rooms rest api
    @GetMapping
    public ResponseEntity<List<RoomDto>> getAllRooms(){
        List<RoomDto> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    // build update room rest api
    @PutMapping("{id}")
    public ResponseEntity<RoomDto> updateRoom(@PathVariable("id") Long roomId,@RequestBody RoomDto updatedRoom){
        RoomDto roomDto =  roomService.updateRoom(roomId, updatedRoom);
        return ResponseEntity.ok(roomDto);
    }

    // build delete room delete rest api
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable("id") Long roomId){
        roomService.deleteRoom(roomId);
        return ResponseEntity.ok("Room Deleted Successfully.");
    }
}
