package com.erahotel.era_backend.mapper;

import com.erahotel.era_backend.dto.AdminDto;
import com.erahotel.era_backend.entity.Admin;

/**
 * Utility class for mapping between Admin entity and AdminDto.
 * <p>
 * Provides static methods to convert Admin entities to AdminDto objects and vice versa.
 * </p>
 *
 * @author alwin roble
 * @see com.erahotel.era_backend.entity.Admin
 * @see com.erahotel.era_backend.dto.AdminDto
 */
public class AdminMapper {

    /**
     * Maps an Admin entity to an AdminDto.
     *
     * @param admin the Admin entity to map
     * @return an AdminDto containing the data from the Admin entity
     */
    public static AdminDto mapToAdminDto(Admin admin) {
        return new AdminDto(
                admin.getAdminId(),
                admin.getName(),
                admin.getEmail(),
                admin.getPassword()
        );
    }

    /**
     * Maps an AdminDto to an Admin entity.
     *
     * @param adminDto the AdminDto to map
     * @return an Admin entity containing the data from the AdminDto
     */
    public static Admin mapToAdmin(AdminDto adminDto) {
        return new Admin(
                adminDto.getAdminId(),
                adminDto.getName(),
                adminDto.getEmail(),
                adminDto.getPassword()
        );
    }
}
