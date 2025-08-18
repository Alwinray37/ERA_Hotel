package com.erahotel.era_backend.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReservationIdGeneratorTest {

    @Test
    void testGenerateIdFormat() {
        String id = ReservationIdGenerator.generateId();
        System.out.println("Generated ID: " + id);

        // Check length
        if (id.length() == 6) {
            System.out.println("PASS: ID is 6 characters long");
        } else {
            System.out.println("FAIL: ID is not 6 characters long");
        }
        assertEquals(6, id.length(), "ID should be 6 characters long");

        // Check first 3 are uppercase letters
        String letters = id.substring(0, 3);
        if (letters.matches("[A-Z]{3}")) {
            System.out.println("PASS: First 3 characters are uppercase letters");
        } else {
            System.out.println("FAIL: First 3 characters are not uppercase letters");
        }
        assertTrue(letters.matches("[A-Z]{3}"), "First 3 characters should be uppercase letters");

        // Check last 3 are digits
        String digits = id.substring(3, 6);
        if (digits.matches("[0-9]{3}")) {
            System.out.println("PASS: Last 3 characters are digits");
        } else {
            System.out.println("FAIL: Last 3 characters are not digits");
        }
        assertTrue(digits.matches("[0-9]{3}"), "Last 3 characters should be digits");
    }

    @Test
    void testGenerateIdRandomness() {
        int sampleSize = 1000;
        var ids = new java.util.HashSet<String>();
        for (int i = 0; i < sampleSize; i++) {
            String id = ReservationIdGenerator.generateId();
            ids.add(id);
            if (i < 5) { // Print first 5 generated IDs for demonstration
                System.out.println("Sample ID " + (i + 1) + ": " + id);
            }
        }
        System.out.println("Generated " + sampleSize + " IDs, unique count: " + ids.size());
        if (ids.size() > 990) {
            System.out.println("PASS: IDs are mostly unique");
        } else {
            System.out.println("FAIL: Too many duplicate IDs");
        }
        assertTrue(ids.size() > 990, "IDs should be mostly unique");
    }
}
