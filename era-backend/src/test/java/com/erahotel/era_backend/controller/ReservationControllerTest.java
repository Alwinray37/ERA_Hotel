package com.erahotel.era_backend.controller;

import com.erahotel.era_backend.dto.ReservationDto;
import com.erahotel.era_backend.entity.Reservation;
import com.erahotel.era_backend.repository.AdminRepository;
import com.erahotel.era_backend.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservationController.class)
@ActiveProfiles("test")
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean                    // ← add this line
    private AdminRepository adminRepository;

    @InjectMocks
    private ReservationController reservationController;

    @MockBean
    private ReservationService reservationService;

    private ReservationDto reservationDto;

    @BeforeEach
    void setUp() {
        reservationDto = new ReservationDto();
        reservationDto.setReservationId("ABC123");
        reservationDto.setTotalCost(new BigDecimal("100.00"));
        reservationDto.setStartDate(new Date());
        reservationDto.setEndDate(new Date());
        reservationDto.setStatus("CONFIRMED");
    }

    @Test
    void testCreateReservation() throws Exception {
        when(reservationService.createReservation(Mockito.any()))
                .thenReturn(reservationDto);

        mockMvc.perform(post("/api/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reservationDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetReservationById() {
        when(reservationService.getReservationById("res123")).thenReturn(reservationDto);

        ResponseEntity<ReservationDto> response = reservationController.getReservationById("res123");

        System.out.println("Get By ID Response: " + response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("res123", response.getBody().getReservationId());
    }

    @Test
    void testGetAllReservations() {
        List<ReservationDto> reservations = Arrays.asList(reservationDto);
        when(reservationService.getAllReservations()).thenReturn(reservations);

        ResponseEntity<List<ReservationDto>> response = reservationController.getAllReservations();

        System.out.println("Get All Reservations Response: " + response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }
    @Test
    void testDeleteReservation() {
        doNothing().when(reservationService).deleteReservation("res123");

        ResponseEntity<String> response = reservationController.deleteReservation("res123");

        System.out.println("Delete Response: " + response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Reservation Deleted Successfully.", response.getBody());
        verify(reservationService, times(1)).deleteReservation("res123");
    }
    @Test
    void testUpdateReservation() {
        ReservationDto updatedReservation = new ReservationDto();
        updatedReservation.setReservationId("res123");
        updatedReservation.setTotalCost(new BigDecimal("300.75"));
        updatedReservation.setStartDate(new Date());
        updatedReservation.setEndDate(new Date());

        when(reservationService.updateReservation(eq("res123"), any(ReservationDto.class))).thenReturn(updatedReservation);

        ResponseEntity<ReservationDto> response = reservationController.updateReservation("res123", updatedReservation);

        System.out.println("Update Response: " + response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(new BigDecimal("300.75"), response.getBody().getTotalCost());
    }

}
