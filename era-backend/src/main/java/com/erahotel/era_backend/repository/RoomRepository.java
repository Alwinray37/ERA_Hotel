package com.erahotel.era_backend.repository;

import com.erahotel.era_backend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long>{
    // CRUD methods

}
