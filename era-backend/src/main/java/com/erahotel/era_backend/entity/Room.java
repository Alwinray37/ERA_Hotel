package com.erahotel.era_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rooms")

public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int roomId;
    private String roomNumber;
    private RoomType roomType;
    private double nightlyPrice;
    private boolean isAvailable;
    private int maxOccupancy;
    private String roomDescription;
    private List<String> amenities;
    private List<String> reservations; // tracks all reservations for this room

    // constructor for Room class
//    public Room(int roomId, String roomNumber, RoomType roomType, double nightlyPrice, boolean isAvailable, int maxOccupancy, String roomDescription, List<String> amenities, List<String> reservations) {
//        this.roomId = roomId;
//        this.roomNumber = roomNumber;
//        this.roomType = roomType;
//        this.nightlyPrice = nightlyPrice;
//        this.isAvailable = isAvailable;
//        this.maxOccupancy = maxOccupancy;
//        this.roomDescription = roomDescription;
//        this.amenities = amenities;
//        this.reservations = reservations;
//    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public double getNightlyPrice() {
        return nightlyPrice;
    }

    public void setNightlyPrice(double nightlyPrice) {
        this.nightlyPrice = nightlyPrice;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public List<String> getReservations() {
        return reservations;
    }

    public void setReservations(List<String> reservations) {
        this.reservations = reservations;
    }
}

