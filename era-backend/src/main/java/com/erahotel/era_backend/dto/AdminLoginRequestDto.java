package com.erahotel.era_backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminLoginRequestDto {
    private String email;
    private String password;
}