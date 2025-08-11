package com.erahotel.era_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guests")
public class Guest{

    // primary key for the guest entity, uses auto-increment strategy
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guestId;

    private String name; // add validation
    private String email;
    private String phone;

    // maps the one-to-many relationships with reservations
    // mappedBy = "guest" indicates the Reservation entity owns the relationship
    // cascade = CascadeType.All means all operations (persist, merge, remove..) are cascaded to reservations
    // orphanRemoval = true means if a reservation is removed from this list, it will be deleted from the database.

    // list of reservationId "ABC123"
    private List<String> guestReservations; // tracks all reservations for this guest.
}
