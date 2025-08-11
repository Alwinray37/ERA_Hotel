package com.erahotel.era_backend.controller;

import com.erahotel.era_backend.dto.ReservationDto;
import com.erahotel.era_backend.service.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationDto reservationDto) {
        ReservationDto savedReservation = reservationService.createReservation(reservationDto);
        return new ResponseEntity<>(savedReservation, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable("id") String reservationId) {
        ReservationDto reservationDto = reservationService.getReservationById(reservationId);
        return ResponseEntity.ok(reservationDto);
    }

    @GetMapping
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        List<ReservationDto> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable("id") String reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.ok("Reservation Deleted Successfully.");
    }
}