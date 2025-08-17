package com.erahotel.era_backend.service.impl;

import com.erahotel.era_backend.entity.Guest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuestServiceImplTest {

    @Test
    void createGuest() {
        // Arrange: create a guest and define expected behavior
        Guest guest = new Guest();
        guest.setGuestId(1L);
        guest.setName("John Doe");
        guest.setEmail("john@email.com");
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("john@email.com", result.getEmail());
    }

    /*@Test
    void getGuestById() {
    }

    @Test
    void getAllGuests() {
    }

    @Test
    void getGuestByEmail() {
    }

    @Test
    void updateGuest() {
    }

    @Test
    void addReservation() {
    }

    @Test
    void deleteGuest() {
    }*/
}
