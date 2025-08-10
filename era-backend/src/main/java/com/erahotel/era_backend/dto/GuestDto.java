package com.erahotel.era_backend.dto;

import com.erahotel.era_backend.entity.Reservation;
import lombok.Data;
import java.util.List;

@Data
public class GuestDto {
    private Long guestId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<Reservation> reservations;
}
