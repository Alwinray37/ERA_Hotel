package com.erahotel.era_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuestDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<String> reservations;
}
