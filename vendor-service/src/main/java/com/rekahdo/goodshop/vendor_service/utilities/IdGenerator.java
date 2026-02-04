package com.rekahdo.goodshop.vendor_service.utilities;

import java.security.SecureRandom;

public class IdGenerator {
    
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final String USER_PREFIX = "GSV";
    private static final String ALPHA_NUMERIC = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
    private static final int ALPHA_NUMERIC_LENGTH = ALPHA_NUMERIC.length();
    private static final int LENGTH = 4;

    /**
     * Generates a referral code style user ID
     * Format: GS-XXXX-YYYY
     * Example: GS-3AB9-C7D2
     */
    public static String generateId() {
        String part1 = generateRandomAlphanumeric().toUpperCase();
        String part2 = generateRandomAlphanumeric().toUpperCase();
        return String.format("%s-%s-%s", USER_PREFIX, part1, part2);
    }

    private static String generateRandomAlphanumeric() {
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            int index = SECURE_RANDOM.nextInt(ALPHA_NUMERIC_LENGTH);
            sb.append(ALPHA_NUMERIC.charAt(index));
        }
        return sb.toString();
    }

}