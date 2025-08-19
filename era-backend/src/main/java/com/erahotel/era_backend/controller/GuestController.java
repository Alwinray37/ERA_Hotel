package com.erahotel.era_backend.controller;

import com.erahotel.era_backend.dto.GuestDto;
import com.erahotel.era_backend.service.GuestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 *  This is the Guest Controller
 * REST controller for managing guest-related operations in the system
 * privides endpoints for creating, retrieving, updating, and deleting guest records, as well as appending reservation ID's to guests.
 *
 * <p>All endpoints are accessible under the "/api/guests" base path.</p>
 *
 * @author Alwin Roble
 */

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/guests")
public class GuestController {
    private GuestService guestService;

    /**
     * Retrieves all guests in the system.
     *
     * @return list of all guests
     */
    @GetMapping
    public ResponseEntity<List<GuestDto>> getAllGuests(){
        List<GuestDto> guests = guestService.getAllGuests();
        return ResponseEntity.ok(guests);
    }

    /**
     * Retrieves a guest by its unique ID.
     *
     * @param guestId the ID of the guest to retrieve
     * @return the matching guest record
     */
    @GetMapping("{id}")
    public ResponseEntity<GuestDto> getGuestById(@PathVariable("id") Long guestId){
        GuestDto guestDto = guestService.getGuestById(guestId);
        return ResponseEntity.ok(guestDto);
    }

    /**
     * Retrieves a guest by their email address.
     *
     * @param email the email address to search for
     * @return the matching guest record
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<GuestDto> getGuestByEmail(@PathVariable("email") String email){
        GuestDto guestDto = guestService.getGuestByEmail(email);
        return ResponseEntity.ok(guestDto);
    }

    /**
     * Creates a new guest record.
     *
     * @param guestDto the guest data to be saved
     * @return the saved guest record with a 201 CREATED status
     */
    @PostMapping
    public ResponseEntity<GuestDto> createGuest(@RequestBody GuestDto guestDto){
        GuestDto savedGuest = guestService.createGuest(guestDto);
        return new ResponseEntity<>(savedGuest, HttpStatus.CREATED);
    }

    /**
     * Deletes a guest by ID.
     *
     * @param guestId the ID of the guest to delete
     * @return success message
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteGuest(@PathVariable("id") Long guestId){
        guestService.deleteGuest(guestId);
        return ResponseEntity.ok("Guest Deleted Successfully.");
    }

    /**
     * Updates an existing guest's details.
     *
     * @param guestId the ID of the guest to update
     * @return the updated guest record
     */
    @PutMapping("/{id}")
    public ResponseEntity<GuestDto> updateGuest(@PathVariable("id") Long guestId, @RequestBody GuestDto updatedGuest){
        GuestDto guestDto =  guestService.updateGuest(guestId, updatedGuest);
        return ResponseEntity.ok(guestDto);
    }

    /**
     * Appends a reservation ID to the guest's reservation list.
     *
     * @param guestId the ID of the guest to update
     * @param reservationId the reservation ID to add
     * @return the updated guest record
     */
    @PostMapping("/{id}/reservations")
    public ResponseEntity<GuestDto> addReservationToGuest(
            @PathVariable("id") Long guestId,
            @RequestBody String reservationId) {
        GuestDto updatedGuest = guestService.addReservation(guestId, reservationId.replace("\"", ""));
        return ResponseEntity.ok(updatedGuest);
    }

}

