package com.erahotel.era_backend.dto;

import lombok.*;
import java.util.List;

/**
 * Data Transfer Object for the Guest entity.
 * <p>
 * Used to transfer guest data between layers without exposing the entity directly.
 * This DTO contains basic guest information and a list of reservation IDs associated with the guest.
 * </p>
 *
 * @author Alwin Roble
 * @see com.erahotel.era_backend.entity.Guest
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestDto {
    /**
     * The unique identifier of the guest.
     */
    private Long guestId;

    /**
     * The full name of the guest.
     */
    private String name;

    /**
     * The email address of the guest.
     */
    private String email;

    /**
     * The phone number of the guest.
     */
    private String phone;

    /**
     * A list of reservation IDs associated with the guest.
     * Each entry in the list represents a reservation made by the guest.
     */
    private List<String> guestReservations;
}