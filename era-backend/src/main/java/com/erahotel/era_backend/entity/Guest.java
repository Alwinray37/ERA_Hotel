package com.erahotel.era_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
<<<<<<< HEAD
=======

import java.util.ArrayList;
>>>>>>> 539ae98d14fa3b1de7ad69506803434caf165626
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guest")
@JsonIgnoreProperties({"reservations"})
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long guestId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email_id", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_id", nullable = false, unique = true)
    private String phone;

<<<<<<< HEAD
    @OneToMany(mappedBy = "guest")
    private List<Reservation> reservations;
=======
    // maps the one-to-many relationships with reservations
    // mappedBy = "guest" indicates the Reservation entity owns the relationship
    // cascade = CascadeType.All means all operations (persist, merge, remove..) are cascaded to reservations
    // orphanRemoval = true means if a reservation is removed from this list, it will be deleted from the database.

    // list of reservationId "ABC123"
    @ElementCollection
    private List<String> guestReservations = new ArrayList<>(); // tracks all reservations for this guest.
>>>>>>> 539ae98d14fa3b1de7ad69506803434caf165626
}
