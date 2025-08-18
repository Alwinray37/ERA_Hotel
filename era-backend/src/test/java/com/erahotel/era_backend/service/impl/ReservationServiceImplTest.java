package com.erahotel.era_backend.service.impl;

import com.erahotel.era_backend.dto.ReservationDto;
import com.erahotel.era_backend.entity.Reservation;
import com.erahotel.era_backend.mapper.ReservationMapper;
import com.erahotel.era_backend.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    private Reservation reservation;
    private ReservationDto reservationDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create startDate = today
        Calendar calendar = Calendar.getInstance();
        Date startDate = calendar.getTime();

        // endDate = today + 2 days
        calendar.add(Calendar.DAY_OF_MONTH, 2);
        Date endDate = calendar.getTime();

        reservation = new Reservation();
        reservation.setReservationId("RES-12345");
        reservation.setGuestEmail("guest@test.com");
        reservation.setRoomNumber("101");
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setTotalCost(BigDecimal.valueOf(300.0));
        reservation.setStatus("CONFIRMED");

        reservationDto = ReservationMapper.mapToDto(reservation);
    }

    @Test
    void testCreateReservation() {
        when(reservationRepository.existsById(anyString())).thenReturn(false);
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        ReservationDto saved = reservationService.createReservation(reservationDto);

        assertNotNull(saved);
        assertEquals("RES-12345", saved.getReservationId());
        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    void testGetReservationById() {
        when(reservationRepository.findById("RES-12345")).thenReturn(Optional.of(reservation));

        ReservationDto found = reservationService.getReservationById("RES-12345");

        assertNotNull(found);
        assertEquals("guest@test.com", found.getGuestEmail());
    }

    @Test
    void testGetReservationById_NotFound() {
        when(reservationRepository.findById("RES-00000")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> reservationService.getReservationById("RES-00000"));
    }

    @Test
    void testGetAllReservations() {
        when(reservationRepository.findAll()).thenReturn(Collections.singletonList(reservation));

        List<ReservationDto> reservations = reservationService.getAllReservations();

        assertEquals(1, reservations.size());
        assertEquals("RES-12345", reservations.get(0).getReservationId());
    }

    @Test
    void testDeleteReservation() {
        doNothing().when(reservationRepository).deleteById("RES-12345");

        reservationService.deleteReservation("RES-12345");

        verify(reservationRepository, times(1)).deleteById("RES-12345");
    }

    @Test
    void testUpdateReservation() {
        when(reservationRepository.findById("RES-12345")).thenReturn(Optional.of(reservation));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        reservationDto.setStatus("CANCELLED");
        ReservationDto updated = reservationService.updateReservation("RES-12345", reservationDto);

        assertEquals("CANCELLED", updated.getStatus());
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testUpdateReservation_NotFound() {
        when(reservationRepository.findById("RES-00000")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () ->
                reservationService.updateReservation("RES-00000", reservationDto));
    }
}
