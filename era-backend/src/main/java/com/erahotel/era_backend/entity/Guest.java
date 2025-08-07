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
@Table(name = "guests")

public class Guest{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long guestId;

    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private List<String> guest_reservations; //should be tracking all reservations of the room

    @ElementCollection
    public List<String> getGuestReservations(){return List.of();}
}
