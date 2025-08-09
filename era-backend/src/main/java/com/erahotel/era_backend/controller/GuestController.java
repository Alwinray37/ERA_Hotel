package com.erahotel.era_backend.controller;

import com.erahotel.era_backend.dto.GuestDto;
import com.erahotel.era_backend.service.GuestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin("*")
@AllArgsConstructor
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

    //Build Get ALl Guests REST API
    //All of the below is typed by me -Ryley
    @GetMapping
    public ResponseEntity<List<GuestDto>> getAllGuests(){
       List<GuestDto> guests = guestService.getAllGuests();
       return ResponseEntity.ok(guests);
    }

    //Build Update Guest REST API
    @PutMapping("{id}")
    public ResponseEntity<GuestDto> updateGuest(@PathVariable("id") Long guestId,
                                                @RequestBody GuestDto updatedGuest){
       GuestDto guestDto = guestService.updateGuest(guestId, updatedGuest);
       return ResponseEntity.ok(guestDto);

    }

    //Build Delete Guest REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteGuest(@PathVariable("id") Long guestId){
        guestService.deleteGuest(guestId);
        return ResponseEntity.ok("Guest deleted successfully.");
    }

}
