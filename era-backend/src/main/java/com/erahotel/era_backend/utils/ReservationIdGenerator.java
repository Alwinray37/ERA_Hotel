package com.erahotel.era_backend.utils;

import java.util.Random;

public class ReservationIdGenerator {
    // this will create a manual id for Reservation class

    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final Random rand = new Random();

    public static String generateId(){
        StringBuilder sb = new StringBuilder();

        // add 3 random letters
        for(int i = 0; i < 3; i++){
            sb.append(LETTERS.charAt(rand.nextInt(LETTERS.length())));
        }
        // add 3 numbers
        for(int i = 0; i < 3; i++){
            sb.append(NUMBERS.charAt(rand.nextInt(NUMBERS.length())));
        }

        return sb.toString();
    }
}
