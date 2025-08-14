package com.erahotel.era_backend.service.impl;

import com.erahotel.era_backend.dto.AdminDto;
import com.erahotel.era_backend.entity.Admin;
import com.erahotel.era_backend.exception.ResourceNotFoundException;
import com.erahotel.era_backend.mapper.AdminMapper;
import com.erahotel.era_backend.repository.AdminRepository;
import com.erahotel.era_backend.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the {@link AdminService} interface.
 * <p>
 * Provides business logic for managing admins, including retrieval by email and listing all admins.
 * Uses {@link AdminRepository} for data access and {@link AdminMapper} for entity-DTO conversion.
 * </p>
 *
 * @author alwin roble
 * @see com.erahotel.era_backend.service.AdminService
 * @see com.erahotel.era_backend.repository.AdminRepository
 * @see com.erahotel.era_backend.mapper.AdminMapper
 */
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    /**
     * Retrieves all admins.
     *
     * @return a list of all admins as {@link AdminDto} objects
     */
    @Override
    public List<AdminDto> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return admins.stream()
                .map(AdminMapper::mapToAdminDto)
                .toList();
    }

    /**
     * Retrieves an admin by their email address.
     *
     * @param email the email address of the admin
     * @return the admin as an {@link AdminDto}
     * @throws ResourceNotFoundException if no admin is found with the given email
     */
    @Override
    public AdminDto getByAdminEmail(String email) {
        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found"));
        return AdminMapper.mapToAdminDto(admin);
    }
}
