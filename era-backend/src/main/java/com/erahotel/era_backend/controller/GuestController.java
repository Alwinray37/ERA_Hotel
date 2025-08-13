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

    @PostMapping
    public ResponseEntity<GuestDto> createGuest(@RequestBody GuestDto guestDto){
        GuestDto savedGuest = guestService.createGuest(guestDto);
        return new ResponseEntity<>(savedGuest, HttpStatus.CREATED);
    }

    // get by Id
    @GetMapping("{id}")
    public ResponseEntity<GuestDto> getGuestById(@PathVariable("id") Long guestId){
        GuestDto guestDto = guestService.getGuestById(guestId);
        return ResponseEntity.ok(guestDto);
    }

    // get by email
    @GetMapping("/email/{email}")
    public ResponseEntity<GuestDto> getGuestByEmail(@PathVariable("email") String email){
        GuestDto guestDto = guestService.getGuestByEmail(email);
        return ResponseEntity.ok(guestDto);
    }

    // get all guests
    @GetMapping
    public ResponseEntity<List<GuestDto>> getAllGuests(){
        List<GuestDto> guests = guestService.getAllGuests();
        return ResponseEntity.ok(guests);
    }

    // build delete room delete rest api
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteGuest(@PathVariable("id") Long guestId){
        guestService.deleteGuest(guestId);
        return ResponseEntity.ok("Guest Deleted Successfully.");
    }

    // put mapping for updating guest info
    // used for when appending the reservation to the guestReservations list
    @PutMapping("/{id}")
    public ResponseEntity<GuestDto> updateGuest(@PathVariable("id") Long guestId, @RequestBody GuestDto updatedGuest){
        GuestDto guestDto =  guestService.updateGuest(guestId, updatedGuest);
        return ResponseEntity.ok(guestDto);
    }
    // Append a reservation to guestReservations
    @PostMapping("/{id}/reservations")
    public ResponseEntity<GuestDto> addReservationToGuest(
            @PathVariable("id") Long guestId,
            @RequestBody String reservationId) {
        GuestDto updatedGuest = guestService.addReservation(guestId, reservationId.replace("\"", ""));
        return ResponseEntity.ok(updatedGuest);
    }

}
