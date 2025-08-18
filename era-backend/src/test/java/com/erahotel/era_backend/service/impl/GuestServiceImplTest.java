package com.erahotel.era_backend.service.impl;

import com.erahotel.era_backend.dto.GuestDto;
import com.erahotel.era_backend.entity.Guest;
import com.erahotel.era_backend.exception.ResourceNotFoundException;
import com.erahotel.era_backend.mapper.GuestMapper;
import com.erahotel.era_backend.repository.GuestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GuestServiceImplTest {

    @Mock
    GuestRepository guestRepository;

    @InjectMocks
    GuestServiceImpl guestServiceImpl;

    private GuestDto dto(long id, String name, String phone, String email) {
        return new GuestDto(id, name, phone, email, new ArrayList<>());
    }

    private Guest entity(GuestDto dto) {
        return GuestMapper.mapToGuest(dto);
    }

    @Test
    void createGuest() {
        GuestDto requestDto = dto(1L, "John Doe", "888-123-4567", "john@email.com");
        when(guestRepository.save(any(Guest.class))).thenReturn(entity(requestDto));

        GuestDto created = guestServiceImpl.createGuest(requestDto);

        assertNotNull(created);
        assertEquals(1L, created.getGuestId());
        assertEquals("John Doe", created.getName());
    }

    @Test
    void getGuestById() {
        GuestDto dto = dto(1L, "John Doe", "888-123-4567", "john@email.com");
        when(guestRepository.findById(1L)).thenReturn(Optional.of(entity(dto)));

        GuestDto found = guestServiceImpl.getGuestById(1L);

        assertNotNull(found);
        assertEquals("John Doe", found.getName());
    }

    @Test
    void getAllGuests() {
        GuestDto g1 = dto(1L, "John Doe", "888-123-4567", "john@example.com");
        GuestDto g2 = dto(2L, "Jane Smith", "800-888-8888", "jane@example.com");
        when(guestRepository.findAll()).thenReturn(Arrays.asList(entity(g1), entity(g2)));

        List<GuestDto> result = guestServiceImpl.getAllGuests();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Jane Smith", result.get(1).getName());
    }

    @Test
    void getGuestByEmail() {
        GuestDto dto = dto(1L, "John Doe", "888-123-4567", "john@email.com");
        when(guestRepository.findByEmail("john@email.com"))
                .thenReturn(Optional.of(entity(dto)));

        GuestDto found = guestServiceImpl.getGuestByEmail("john@email.com");

        assertNotNull(found);
        assertEquals("John Doe", found.getName());
    }

    @Test
    void updateGuest() {
        GuestDto existing = dto(1L, "Old Name", "888-123-4567", "john@email.com");
        when(guestRepository.findById(1L)).thenReturn(Optional.of(entity(existing)));
        when(guestRepository.save(any(Guest.class))).thenAnswer(inv -> inv.getArgument(0));

        GuestDto updatedDto = dto(1L, "Updated Name", "888-123-4567", "john@email.com");
        GuestDto result = guestServiceImpl.updateGuest(1L, updatedDto);

        assertEquals("Updated Name", result.getName());
    }

    @Test
    void addReservation() {
        GuestDto existing = dto(1L, "John Doe", "888-123-4567", "john@email.com");
        when(guestRepository.findById(1L)).thenReturn(Optional.of(entity(existing)));
        when(guestRepository.save(any(Guest.class))).thenAnswer(inv -> inv.getArgument(0));

        GuestDto result = guestServiceImpl.addReservation(1L, "RSV101");

        assertTrue(result.getGuestReservations().contains("RSV101"));
    }

    @Test
    void deleteGuest() {
        when(guestRepository.existsById(1L)).thenReturn(true);
        doNothing().when(guestRepository).deleteById(1L);

        guestServiceImpl.deleteGuest(1L);

        verify(guestRepository).deleteById(1L);
    }
}