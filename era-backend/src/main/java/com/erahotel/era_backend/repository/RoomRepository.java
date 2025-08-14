package com.erahotel.era_backend.repository;

import com.erahotel.era_backend.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for Room entities.
 * <p>
 * Extends {@link JpaRepository} to provide CRUD operations and custom queries for the Room entity.
 * </p>
 *
 * @author alwin roble
 * @see com.erahotel.era_backend.entity.Room
 */
public interface RoomRepository extends JpaRepository<Room, Long> {

    /**
     * Finds a room by its room number.
     *
     * @param roomNum the room number to search for
     * @return an {@link Optional} containing the Room if found, or empty if not found
     */
    Optional<Room> findByRoomNumber(String roomNum);
    // Additional CRUD methods are inherited from JpaRepository
}
