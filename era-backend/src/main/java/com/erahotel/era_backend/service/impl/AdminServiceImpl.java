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

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public List<AdminDto> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return admins.stream()
                .map(AdminMapper::mapToAdminDto)
                .toList();
    }

    @Override
    public AdminDto getByAdminEmail(String email) {
        Admin admin = adminRepository.findByEmail(email)
                .orElseThrow( () -> new ResourceNotFoundException("Admin not found"));
        return AdminMapper.mapToAdminDto(admin);
    }
}
