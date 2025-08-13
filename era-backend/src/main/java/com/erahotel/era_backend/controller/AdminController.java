package com.erahotel.era_backend.controller;

import com.erahotel.era_backend.dto.AdminLoginRequestDto;
import com.erahotel.era_backend.dto.AdminResponseDto;
import com.erahotel.era_backend.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // POST http://localhost:8080/api/admin/login
    @PostMapping("/login")
    public AdminResponseDto login(@RequestBody AdminLoginRequestDto req) {
        return adminService.login(req);
    }
}
