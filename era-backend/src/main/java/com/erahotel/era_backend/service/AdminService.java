package com.erahotel.era_backend.service;

import com.erahotel.era_backend.dto.AdminDto;

import java.util.List;

/**
 * Service interface for managing admins.
 * <p>
 * Defines business operations for retrieving admin information by email and for listing all admins.
 * </p>
 *
 * @author alwin roble
 * @see com.erahotel.era_backend.dto.AdminDto
 */
public interface AdminService {

    /**
     * Retrieves an admin by their email address.
     *
     * @param email the email address of the admin
     * @return the admin as an {@link AdminDto}
     */
    AdminDto getByAdminEmail(String email);

    /**
     * Retrieves all admins.
     *
     * @return a list of all admins as {@link AdminDto} objects
     */
    List<AdminDto> getAllAdmins();
}
