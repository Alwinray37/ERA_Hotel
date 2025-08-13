package com.erahotel.era_backend.repository;

import com.erahotel.era_backend.entity.Reservation;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("""
           select r from Reservation r
           left join fetch r.guest
           left join fetch r.room
           """)
    List<Reservation> findAllWithGuestAndRoom();
=======
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, String>{
>>>>>>> 539ae98d14fa3b1de7ad69506803434caf165626
}
