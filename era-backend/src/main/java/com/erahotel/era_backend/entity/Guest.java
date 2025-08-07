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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int guestId;
    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "email_id", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_id", nullable = false, unique = true)
    private String phone;
    private List<Reservation> guest_reservations; //should be tracking all reservations of the room
}
