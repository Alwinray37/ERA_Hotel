package com.erahotel.era_backend.controller;

import com.erahotel.era_backend.dto.AdminDto;
import com.erahotel.era_backend.dto.AdminReservationSummaryDTO; // added for the dashboard
import com.erahotel.era_backend.repository.ReservationRepository; // added for the dashboard
import com.erahotel.era_backend.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing admins.
 * <p>
 * Provides endpoints for retrieving admin information by email and for listing all admins.
 * </p>
 *
 * @author alwin roble
 * @see com.erahotel.era_backend.dto.AdminDto
 * @see com.erahotel.era_backend.service.AdminService
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api/admins")
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final ReservationRepository reservationRepository; // added for the dashboard


    // get by email
    @GetMapping("/email/{email}")
    public ResponseEntity<AdminDto> getByAdminEmail(@PathVariable("email") String email) {
        AdminDto adminDto = adminService.getByAdminEmail(email);
        return ResponseEntity.ok(adminDto);
    }

    /**
     * Retrieves all admins.
     *
     * @return a list of all admins as {@link AdminDto} objects
     */
    @GetMapping
    public ResponseEntity<List<AdminDto>> getAllAdmins() {
        List<AdminDto> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    // NEW: Get admin reservation summary for dashboard
    @GetMapping("/reservations/summary")
    public List<AdminReservationSummaryDTO> getAdminReservationSummary() {
        return reservationRepository.findAdminReservationSummaries();
    }

}
