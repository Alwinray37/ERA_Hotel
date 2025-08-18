package com.erahotel.era_backend.service.impl;

import com.erahotel.era_backend.entity.Guest;
import com.erahotel.era_backend.repository.GuestRepository;
import com.erahotel.era_backend.service.GuestService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

public class GuestServiceImplTest2 {
    @Mock
    GuestService guestService;

    @Test
    void createGuest() {
        System.out.println(("Create guest");
        Guest guest = new Guest();
        guestService.createGuest(guest);
    }

    @Test
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
    }
}
/* //When I asked Chat/Copliot to generate data for it
package com.erahotel.era_backend.service.impl;

import com.erahotel.era_backend.model.Guest;
import com.erahotel.era_backend.model.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuestServiceImplTest {

    private GuestServiceImpl guestService;

    @BeforeEach
    void setUp() {
        guestService = new GuestServiceImpl();
    }

    @Test
    void createGuest() {
        Guest guest = guestService.createGuest("John Doe", "john@example.com");
        assertNotNull(guest);
        assertEquals("John Doe", guest.getName());
    }

    @Test
    void getGuestById() {
        Guest guest = guestService.createGuest("Jane Doe", "jane@example.com");
        Guest found = guestService.getGuestById(guest.getId());
        assertEquals(guest.getEmail(), found.getEmail());
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
        guestService.createGuest("Alice", "alice@example.com");
        Guest guest = guestService.getGuestByEmail("alice@example.com");
        assertNotNull(guest);
        assertEquals("Alice", guest.getName());
    }

    @Test
    void updateGuest() {
        Guest guest = guestService.createGuest("Bob", "bob@example.com");
        Guest updated = guestService.updateGuest(guest.getId(), "Bobby", "bobby@example.com");
        assertEquals("Bobby", updated.getName());
        assertEquals("bobby@example.com", updated.getEmail());
    }

    @Test
    void addReservation() {
        Guest guest = guestService.createGuest("Carl", "carl@example.com");
        Reservation res = new Reservation(1L, "Room 101", guest.getId());
        guestService.addReservation(guest.getId(), res);
        // No return value, so just check no exceptions and maybe expand later with reservation retrieval
        assertTrue(true);
    }

    @Test
    void deleteGuest() {
        Guest guest = guestService.createGuest("Dave", "dave@example.com");
        boolean deleted = guestService.deleteGuest(guest.getId());
        assertTrue(deleted);
        assertNull(guestService.getGuestById(guest.getId()));
    }
}*/