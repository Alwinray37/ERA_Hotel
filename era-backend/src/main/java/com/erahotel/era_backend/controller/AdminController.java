package com.erahotel.era_backend.controller;

import com.erahotel.era_backend.dto.AdminDto;
import com.erahotel.era_backend.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // get all admins
//    GetMapping
//    public ResponseEntity<List<AdminDto>> getAllAdmins(){
//        List<AdminDto> admins = adminService.getAllAdmins();
//        return ResponseEntity.ok(admins);
//    }

    // get by email
    @GetMapping("/email/{email}")
    public ResponseEntity<AdminDto> getByAdminEmail(@PathVariable("email") String email){
        AdminDto adminDto = adminService.getByAdminEmail(email);
        return ResponseEntity.ok(adminDto);
    }


}

