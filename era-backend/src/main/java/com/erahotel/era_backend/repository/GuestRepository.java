package com.erahotel.era_backend.repository;

import com.erahotel.era_backend.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuestRepository extends JpaRepository <Guest, Long> {
    Optional<Guest> findByEmail(String email);
}