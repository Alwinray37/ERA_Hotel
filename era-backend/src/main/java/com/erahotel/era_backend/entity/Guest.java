package com.erahotel.era_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guests")
public class Guest{


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guestId;

    private String name;
    private String email;
    private String phone;


    @ElementCollection
    private List<String> guestReservations = new ArrayList<>(); // tracks all reservations for this guest.
}
