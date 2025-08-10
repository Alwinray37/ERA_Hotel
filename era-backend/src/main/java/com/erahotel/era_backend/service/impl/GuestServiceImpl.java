package com.erahotel.era_backend.service.impl;

import com.erahotel.era_backend.dto.GuestDto;
import com.erahotel.era_backend.entity.Guest;
import com.erahotel.era_backend.mapper.GuestMapper;
import com.erahotel.era_backend.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestServiceImpl {

    private final GuestRepository guestRepository;

    public List<GuestDto> findAll() {
        return guestRepository.findAll().stream()
                .map(GuestMapper::mapToGuestDto)
                .toList();
    }

    public GuestDto findById(Long id) {
        return guestRepository.findById(id)
                .map(GuestMapper::mapToGuestDto)
                .orElse(null);
    }

    public GuestDto create(GuestDto dto) {
        Guest saved = guestRepository.save(GuestMapper.mapToGuest(dto));
        return GuestMapper.mapToGuestDto(saved);
    }

    public GuestDto update(Long id, GuestDto dto) {
        Guest existing = guestRepository.findById(id).orElseThrow();
        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setEmail(dto.getEmail());
        existing.setPhone(dto.getPhone());
        existing.setReservations(dto.getReservations());
        return GuestMapper.mapToGuestDto(guestRepository.save(existing));
    }

    public void delete(Long id) {
        guestRepository.deleteById(id);
    }
}
