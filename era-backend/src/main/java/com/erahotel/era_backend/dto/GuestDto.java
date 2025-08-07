package com.erahotel.era_backend.dto;

import com.erahotel.era_backend.entity.Reservation;
import lombok.Getter;
import lombok.*;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class GuestDto {
    private Long guestId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<String> reservations;
}
