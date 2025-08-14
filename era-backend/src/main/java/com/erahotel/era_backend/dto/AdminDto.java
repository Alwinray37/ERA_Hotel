package com.erahotel.era_backend.dto;

import lombok.*;

/**
 * Data Transfer Object for the Admin entity.
 * <p>
 * Used to transfer admin data between layers without exposing the entity directly.
 * Contains basic admin information such as ID, name, email, and password.
 * </p>
 *
 * @author Alwin Roble
 * @see com.erahotel.era_backend.entity.Admin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
    /**
     * The unique identifier of the admin.
     */
    private Long adminId;

    /**
     * The full name of the admin.
     */
    private String name;

    /**
     * The email address of the admin.
     */
    private String email;

    /**
     * The password of the admin.
     */
    private String password;
}
