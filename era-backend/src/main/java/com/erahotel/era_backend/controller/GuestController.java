package com.erahotel.era_backend.controller;

import com.erahotel.era_backend.dto.GuestDto;
import com.erahotel.era_backend.service.GuestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestController {

    private GuestService guestService;

    //Build Add Guest REST API
    @PostMapping
    public ResponseEntity<GuestDto> createGuest(@RequestBody GuestDto guestDto){
       GuestDto savedGuest = guestService.createGuest(guestDto);
       return new ResponseEntity<>(savedGuest, HttpStatus.CREATED);
    }

    //Build Get Guest REST API
    @GetMapping("{id}")
    public ResponseEntity<GuestDto> getGuestById(@PathVariable("id") Long guestId){
        GuestDto guestDto = guestService.getGuestById(guestId);
        return ResponseEntity.ok(guestDto);

    }
    //Build Get All Guests REST API
    @GetMapping
    public ResponseEntity<List<GuestDto>> getAllGuests(){
       List<GuestDto> guests = guestService.getAllGuests();
       return ResponseEntity.ok(guests);
    }
}
