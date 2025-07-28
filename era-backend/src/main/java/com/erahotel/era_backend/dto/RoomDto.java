package com.erahotel.era_backend.dto;

import com.erahotel.era_backend.entity.RoomType;

import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RoomDto {

    private long roomId;
    private int roomNumber;
    private RoomType roomType;
    private double nightlyPrice;
    private boolean isAvailable;
    private int maxOccupancy;
    private String roomDescription;
    private List<String> amenities;
    private List<String> reservations;
}
