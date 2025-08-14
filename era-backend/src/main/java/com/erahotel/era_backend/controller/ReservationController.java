package com.erahotel.era_backend.controller;

import com.erahotel.era_backend.dto.ReservationDto;
import com.erahotel.era_backend.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing reservations.
 * <p>
 * Provides endpoints for creating, retrieving, updating, and deleting reservations.
 * </p>
 *
 * @author Alwin Roble
 * @see com.erahotel.era_backend.dto.ReservationDto
 * @see com.erahotel.era_backend.service.ReservationService
 */
@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    /**
     * Creates a new reservation.
     *
     * @param reservationDto the data transfer object containing reservation information
     * @return the created reservation as a {@link ReservationDto}
     */
    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationDto reservationDto) {
        ReservationDto savedReservation = reservationService.createReservation(reservationDto);
        return new ResponseEntity<>(savedReservation, HttpStatus.CREATED);
    }

    /**
     * Retrieves a reservation by its unique ID.
     *
     * @param reservationId the ID of the reservation to retrieve
     * @return the reservation as a {@link ReservationDto}
     */
    @GetMapping("{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable("id") String reservationId) {
        ReservationDto reservationDto = reservationService.getReservationById(reservationId);
        return ResponseEntity.ok(reservationDto);
    }

    /**
     * Retrieves all reservations.
     *
     * @return a list of all reservations as {@link ReservationDto} objects
     */
    @GetMapping
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        List<ReservationDto> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    /**
     * Deletes a reservation by its unique ID.
     *
     * @param reservationId the ID of the reservation to delete
     * @return a confirmation message
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable("id") String reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.ok("Reservation Deleted Successfully.");
    }

    /**
     * Updates the information of an existing reservation.
     *
     * @param resId              the ID of the reservation to update
     * @param updatedReservation the updated reservation data
     * @return the updated reservation as a {@link ReservationDto}
     */
    @PutMapping("{id}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable("id") String resId, @RequestBody ReservationDto updatedReservation) {
        ReservationDto resDto = reservationService.updateReservation(resId, updatedReservation);
        return ResponseEntity.ok(resDto);
    }
}
