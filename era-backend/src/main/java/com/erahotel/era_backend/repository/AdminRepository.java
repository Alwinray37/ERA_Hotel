package com.erahotel.era_backend.repository;

import com.erahotel.era_backend.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository interface for Admin entities.
 * <p>
 * Extends {@link JpaRepository} to provide CRUD operations and custom queries for the Admin entity.
 * </p>
 *
 * @author alwin roble
 * @see com.erahotel.era_backend.entity.Admin
 */
public interface AdminRepository extends JpaRepository<Admin, Long> {

    /**
     * Finds an admin by their email address.
     *
     * @param email the email address of the admin
     * @return an {@link Optional} containing the Admin if found, or empty if not found
     */
    Optional<Admin> findByEmail(String email);
}
