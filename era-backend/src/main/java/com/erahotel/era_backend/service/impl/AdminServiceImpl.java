package com.erahotel.era_backend.service;

import com.erahotel.era_backend.dto.AdminLoginRequestDto;
import com.erahotel.era_backend.dto.AdminResponseDto;
import com.erahotel.era_backend.entity.Admin;
import com.erahotel.era_backend.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public AdminResponseDto login(AdminLoginRequestDto req) {
        Admin admin = adminRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password"));

        // Plain-text compare for simplicity
        if (!admin.getPassword().equals(req.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        return new AdminResponseDto(admin.getAdminId(), admin.getName(), admin.getEmail());
    }
}
