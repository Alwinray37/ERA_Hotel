package com.erahotel.era_backend.utils;

import java.util.Random;

/**
 * Utility class for generating custom reservation IDs.
 * <p>
 * Generates a unique ID consisting of 3 random uppercase letters followed by 3 random digits (e.g., "ABC123").
 * Used for assigning IDs to reservations in the system.
 * </p>
 *
 * @author alwin roble
 */
public class ReservationIdGenerator {

    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final Random rand = new Random();

    /**
     * Generates a custom reservation ID.
     * <p>
     * The ID consists of 3 random uppercase letters followed by 3 random digits.
     * </p>
     *
     * @return a unique reservation ID (e.g., "ABC123")
     */
    public static String generateId() {
        StringBuilder sb = new StringBuilder();

        // Add 3 random letters
        for (int i = 0; i < 3; i++) {
            sb.append(LETTERS.charAt(rand.nextInt(LETTERS.length())));
        }
        // Add 3 random numbers
        for (int i = 0; i < 3; i++) {
            sb.append(NUMBERS.charAt(rand.nextInt(NUMBERS.length())));
        }

        return sb.toString();
    }
}
