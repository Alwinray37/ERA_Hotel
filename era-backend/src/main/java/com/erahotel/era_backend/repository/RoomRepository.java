package com.erahotel.era_backend.repository;

import com.erahotel.era_backend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long>{
    Optional<Room> findByRoomNumber(String roomNum);
    // CRUD methods

}
