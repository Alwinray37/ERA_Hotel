package com.erahotel.era_backend.repository;

import com.erahotel.era_backend.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for Guest entities.
 * <p>
 * Extends {@link JpaRepository} to provide CRUD operations and custom queries for the Guest entity.
 * </p>
 *
 * @author Alwin Roble
 * @see com.erahotel.era_backend.entity.Guest
 */
public interface GuestRepository extends JpaRepository<Guest, Long> {

    /**
     * Finds a guest by their email address.
     *
     * @param email the email address of the guest
     * @return an {@link Optional} containing the Guest if found, or empty if not found
     */
    Optional<Guest> findByEmail(String email);
}
