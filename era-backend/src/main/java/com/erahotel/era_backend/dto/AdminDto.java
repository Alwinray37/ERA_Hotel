package com.erahotel.era_backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AdminDto {
    private Long adminId;
    private String name;
    private String email;
    private String password;
}