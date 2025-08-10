package com.erahotel.era_backend.service;

import com.erahotel.era_backend.dto.AdminReservationDTO;
import com.erahotel.era_backend.entity.Reservation;
import com.erahotel.era_backend.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminReservationService {

    private final ReservationRepository reservationRepository;

    public List<AdminReservationDTO> getAll() {
        return reservationRepository.findAllWithGuestAndRoom()
                .stream()
                .map(this::toDto)
                .toList();
    }

    private AdminReservationDTO toDto(Reservation r) {
        var g = r.getGuest();
        var room = r.getRoom();
        return new AdminReservationDTO(
                r.getReservationId(),
                r.getCheckIn(),
                r.getCheckOut(),
                r.getStatus(),
                r.getTotalAmount(),
                g != null ? g.getGuestId() : null,
                g != null ? g.getFirstName() : null,
                g != null ? g.getLastName() : null,
                g != null ? g.getEmail() : null,
                g != null ? g.getPhone() : null,
                room != null ? room.getRoomId() : null,
                room != null ? room.getRoomNumber() : null,
                room != null ? room.getRoomType() : null,
                room != null ? room.getPricePerNight() : null
        );
    }
}
