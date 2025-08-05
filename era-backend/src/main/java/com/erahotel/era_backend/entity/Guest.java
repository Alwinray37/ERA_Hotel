package com.erahotel.era_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guest")

public class Guest{
    private int guestId;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private List<Reservation> guest_reservations;
}
