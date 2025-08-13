package com.erahotel.era_backend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminResponseDto {
    private Long adminId;
    private String name;
    private String email;
}
