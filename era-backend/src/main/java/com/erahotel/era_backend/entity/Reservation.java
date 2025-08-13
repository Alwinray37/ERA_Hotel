package com.erahotel.era_backend.entity;

<<<<<<< HEAD
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservation")
=======
import com.erahotel.era_backend.utils.ReservationIdGenerator;
import jakarta.persistence.*;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor

>>>>>>> 539ae98d14fa3b1de7ad69506803434caf165626
public class Reservation {

    @Id
    @Column(unique = true, nullable = false)
    private String reservationId; // Custom ID like ABC123

<<<<<<< HEAD
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id")
    private Guest guest;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(nullable = false)
    private LocalDate checkIn;

    @Column(nullable = false)
    private LocalDate checkOut;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private BigDecimal totalAmount;
}
=======
    private String guestEmail;
    private String roomNumber;
    private Date startDate;
    private Date endDate;
    private BigDecimal totalCost;
    private String status; // confirmed, cancelled, finished

    @PrePersist
    public void assignReservationId() {
        if (this.reservationId == null) {
            this.reservationId = ReservationIdGenerator.generateId();
        }
    }
}
>>>>>>> 539ae98d14fa3b1de7ad69506803434caf165626
