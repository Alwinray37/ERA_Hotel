package com.erahotel.era_backend.service.impl;

import com.erahotel.era_backend.dto.ReservationDto;
import com.erahotel.era_backend.entity.Guest;
import com.erahotel.era_backend.entity.Reservation;
import com.erahotel.era_backend.mapper.ReservationMapper;
import com.erahotel.era_backend.repository.GuestRepository;
import com.erahotel.era_backend.utils.ReservationIdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GuestServiceImplTest {

    @Mock
    private GuestRepository guestRepository;

    @InjectMocks
    private GuestServiceImpl guestService;

    private Guest Guest;

    @BeforeEach
    void setUp() {
        Guest = new Guest();
        Guest.setId(1L);
        Guest.setName("John");
    }

    @Test
    void createGuest() {
        Guest guest = new Guest();
        guest.setName("John");

        assertAll("check name",
                () -> assertEquals("John", guest.getName())
        );
    }

    @Test
    void getGuestById() {
        when(guestRepository.findById(1L));

        Guest result = guestService.getGuestById(1L);

        assertNotNull(result);
        assertEquals("John", result.getName());
        verify(guestRepository, times(1)).findById(1L);
    }

    @Test
    void getAllGuests() {
        guestService.createGuest("Guest1", "g1@example.com");
        guestService.createGuest("Guest2", "g2@example.com");
        List<Guest> guests = guestService.getAllGuests();
        assertEquals(2, guests.size());
    }

    @Test
    void getGuestByEmail() {
        when(guestRepository.findByEmail());

        Guest result = guestService.getGuestByEmail("john@email.com");

        assertNotNull(result);
        assertNull("John", result.getName());
        verify(guestRepository, times(1)).findByEmail("john@email.com");
    }

    @Test
    void updateGuest() {
    }

    @Test
    void addReservation() {

    }

    @Test
    void deleteGuest() {
    }
}