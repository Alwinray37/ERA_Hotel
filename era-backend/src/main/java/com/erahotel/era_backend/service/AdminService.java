package com.erahotel.era_backend.service;

import com.erahotel.era_backend.dto.AdminLoginRequestDto;
import com.erahotel.era_backend.dto.AdminResponseDto;

public interface AdminService {
    AdminResponseDto login(AdminLoginRequestDto req);
}