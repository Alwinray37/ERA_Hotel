package com.erahotel.era_backend.service.impl;

import com.erahotel.era_backend.entity.Guest;
import com.erahotel.era_backend.repository.GuestRepository;
import com.erahotel.era_backend.service.GuestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.;

@ExtendWith((MockitoExtension.class))



public class GuestServiceImplTest2 {
    @Mock
    GuestRepository guestRepository;

    @InjectMocks Mock
    GuestService guestService;

    @Test
    void createGuest() {
        System.out.println(("Create guest");
        Guest guest = new Guest();
        guest.setGuestId(1);
        guest.setName("John Doe");
        guest.setEmail("john@email.com");
        guest.getPhone("888-888-8888");
        Mockito.when(guestRepository.save(guest)).thenReturn(guest);
        Guest createGuest = guestService.createGuest(guest);
        //Test guest = matched guest
        Assertions.assertNotNull(createGuest));
        Assertions.assertEquals(guest.getGuestId(), createGuest.getGuestId());
        Assertions.assertEquals(guest.getName(), createGuest.getName());
        Assertions.assertEquals(guest.getEmail(), createGuest.getEmail());
        Assertions.assertEquals(guest.getPhone(), createGuest.getPhone());
        Assertions.assertTrue(guest.getGuestId()==1);
    }

    @Test
    void getGuestById() {
        System.out.println(("get guest id");
        Guest guest = new Guest();
        guest.setGuestId(1);
        guest.setName("John Doe");
        Mockito.when(guestRepository.save(guest)).thenReturn(guest);
        Guest createGuest = guestService.createGuest(guest);
        //Test guest = matched guest
        Assertions.assertNotNull(createGuest));
        Assertions.assertEquals(guest.getGuestId(), createGuest.getGuestId());
        Assertions.assertEquals(guest.getName(), createGuest.getName());
        Assertions.assertTrue(guest.getGuestId() == 1);
    }

    @Test
    void getAllGuests() {
    }

    @Test
    void getGuestByEmail() {
        System.out.println(("get guest email");
        Guest guest = new Guest();
        guest.setGuestId(1);
        guest.setEmail("john@email.com");
        Mockito.when(guestRepository.save(guest)).thenReturn(guest);
        Guest createGuest = guestService.createGuest(guest);
        //Test guest = matched guest
        Assertions.assertNotNull(createGuest));
        Assertions.assertEquals(guest.getGuestId(), createGuest.getGuestId());
        Assertions.assertEquals(guest.getEmail(), createGuest.getEmail());
        Assertions.assertTrue(guest.getGuestId()==1);
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