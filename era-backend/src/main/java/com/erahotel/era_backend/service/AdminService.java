package com.erahotel.era_backend.service;

import com.erahotel.era_backend.dto.AdminDto;

import java.util.List;

public interface AdminService {

    AdminDto getByAdminEmail(String email);

    List<AdminDto> getAllAdmins();
}