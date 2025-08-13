package com.erahotel.era_backend.mapper;

import com.erahotel.era_backend.dto.AdminDto;
import com.erahotel.era_backend.dto.GuestDto;
import com.erahotel.era_backend.entity.Admin;
import com.erahotel.era_backend.entity.Guest;

public class AdminMapper {
    public static AdminDto mapToAdminDto(Admin admin){
        return new AdminDto(
                admin.getAdminId(),
                admin.getName(),
                admin.getEmail(),
                admin.getPassword()
        );
    }

    public static Admin mapToAdmin(AdminDto adminDto){
        return new Admin(
                adminDto.getAdminId(),
                adminDto.getName(),
                adminDto.getEmail(),
                adminDto.getPassword()
        );
    }
}
