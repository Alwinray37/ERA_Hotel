package com.erahotel.era_backend.service.impl;

import com.erahotel.era_backend.dto.GuestDto;
import com.erahotel.era_backend.entity.Guest;
import com.erahotel.era_backend.mapper.GuestMapper;
import com.erahotel.era_backend.repository.GuestRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class GuestServiceImplTest {
    @Mock
    GuestRepository guestRepository;

    @Mock
    GuestMapper guestMapper;

    @InjectMocks
    GuestServiceImpl guestService;

    @Test
    void createGuest() {
        // Create a GuestDto for input
        GuestDto guestDto = new GuestDto();
        guestDto.setName("John Doe");
        guestDto.setEmail("john@email.com");
        guestDto.setPhone("888-888-8888");

        // Create a Guest entity for repository
        Guest guest = new Guest();
        guest.setGuestId(1L);
        guest.setName("John Doe");
        guest.setEmail("john@email.com");
        guest.setPhone("888-888-8888");

        // Mock the mapping and repository save
        Mockito.when(guestMapper.mapToGuest(guestDto)).thenReturn(guest);
        Mockito.when(guestRepository.save(guest)).thenReturn(guest);
        Mockito.when(guestMapper.mapToGuestDto(guest)).thenReturn(guestDto);

        // Call the service
        GuestDto createdGuestDto = guestService.createGuest(guestDto);

        // Assertions
        Assertions.assertNotNull(createdGuestDto);
        Assertions.assertEquals(guestDto.getName(), createdGuestDto.getName());
        Assertions.assertEquals(guestDto.getEmail(), createdGuestDto.getEmail());
        Assertions.assertEquals(guestDto.getPhone(), createdGuestDto.getPhone());
    }

    @Test
    void getGuestById() {
        Guest guest = new Guest();
        guest.setGuestId(1L);
        guest.setName("John Doe");

        GuestDto guestDto = new GuestDto();
        guestDto.setGuestId(1L);
        guestDto.setEmail("john@email.com");

        Mockito.when(guestRepository.findById(1L)).thenReturn(Optional.of(guest));

        Mockito.when(guestRepository.findById(1L)).thenReturn(Optional.of(guest));
        Mockito.when(guestMapper.mapToGuestDto(guest)).thenReturn(guestDto);

        GuestDto foundGuest = guestService.getGuestById(1L);

        Assertions.assertNotNull(foundGuest);
        Assertions.assertEquals(guest.getGuestId(), foundGuest.getGuestId());
        Assertions.assertEquals(guest.getName(), foundGuest.getName());
    }

    @Test
    void getGuestByEmail() {
        Guest guest = new Guest();
        guest.setGuestId(1L);
        guest.setEmail("john@email.com");

        GuestDto guestDto = new GuestDto();
        guestDto.setGuestId(1L);
        guestDto.setEmail("john@email.com");

        Mockito.when(guestRepository.findByEmail("john@email.com")).thenReturn(Optional.of(guest));
        Mockito.when(guestMapper.mapToGuestDto(guest)).thenReturn(guestDto);

        GuestDto foundGuestDto = guestService.getGuestByEmail("john@email.com");

        Assertions.assertNotNull(foundGuestDto);
        Assertions.assertEquals(guestDto.getGuestId(), foundGuestDto.getGuestId());
        Assertions.assertEquals(guestDto.getEmail(), foundGuestDto.getEmail());
    }
}

//Old junit I tried to do- Ryley
/*package com.erahotel.era_backend.service.impl;

import com.erahotel.era_backend.entity.Guest;
import com.erahotel.era_backend.repository.GuestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

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

*/

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