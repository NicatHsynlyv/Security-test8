package com.example.SpringS.Security;

import jakarta.persistence.Converts;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class GenerateCodeUtil {

    private GenerateCodeUtil() {}

    public static String generateCode() {
        String code;
        try {
            SecureRandom random = SecureRandom.getInstanceStrong();  // Creates an instance of SecureRandom that generates a random int value
            int c = random.nextInt(9000) + 1000;  // Generates a value between 0 and 8,999. We add 1,000 to each generated value. This way, we get values between 1,000 and 9,999 (4-digit random codes).
            code = String.valueOf(c);  // Converts the int to a String and returns it
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Problem when generating the random code.");
        }
        return code;
    }
}
