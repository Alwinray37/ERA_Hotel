package com.erahotel.era_backend.dto;

import com.erahotel.era_backend.entity.Reservation;
import lombok.*;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class GuestDto {
    private Long guestId;
    private String name;
    private String email;
    private String phone;
    private List<Reservation> guestReservations;
}
