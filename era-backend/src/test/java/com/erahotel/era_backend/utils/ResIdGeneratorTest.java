// src/test/java/com/erahotel/era_backend/utils/ReservationIdGeneratorTest.java
package com.erahotel.era_backend.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class  ReservationIdGeneratorTest {

    @Test
    void testGenerateIdFormat() {
        String id = ReservationIdGenerator.generateId();
        assertNotNull(id);
        assertEquals(6, id.length(), "ID should be 6 characters long");

        String letters = id.substring(0, 3);
        String numbers = id.substring(3, 6);

        // Check that first 3 are uppercase letters
        assertTrue(letters.matches("[A-Z]{3}"), "First 3 should be uppercase letters");

        // Check that last 3 are digits
        assertTrue(numbers.matches("[0-9]{3}"), "Last 3 should be digits");
    }

    @Test
    void testGenerateIdUniqueness() {
        // Generate 1000 IDs and check for duplicates
        int count = 1000;
        java.util.Set<String> ids = new java.util.HashSet<>();
        for (int i = 0; i < count; i++) {
            String id = ReservationIdGenerator.generateId();
            assertFalse(ids.contains(id), "Duplicate ID found: " + id);
            ids.add(id);
        }
        assertEquals(count, ids.size(), "All IDs should be unique");
    }

    @Test
    void testMultipleCallsProduceDifferentIds() {
        String id1 = ReservationIdGenerator.generateId();
        String id2 = ReservationIdGenerator.generateId();
        assertNotEquals(id1, id2, "Two consecutive IDs should not be the same");
    }
}
