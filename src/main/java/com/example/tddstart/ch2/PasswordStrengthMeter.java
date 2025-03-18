package com.example.tddstart.ch2;

import java.util.regex.Pattern;

public class PasswordStrengthMeter {

    private final Pattern NUMBER_PATTERN = Pattern.compile(".*[0-9].*");
    private final Pattern UPPER_CASE_PATTERN = Pattern.compile(".*[A-Z].*");

    public PasswordStrength meter(String password) {
        if (password == null || password.isEmpty()) {
            return PasswordStrength.INVALID;
        }
        boolean lengthEnough = password.length() >= 8;
        boolean containsUpperCase = UPPER_CASE_PATTERN.matcher(password).matches();
        boolean containsNumber = NUMBER_PATTERN.matcher(password).matches();

        if (lengthEnough && !containsUpperCase && !containsNumber) {
            return PasswordStrength.WEAK;
        }
        if (!lengthEnough && !containsUpperCase && containsNumber) {
            return PasswordStrength.WEAK;
        }
        if (!lengthEnough && containsUpperCase && !containsNumber) {
            return PasswordStrength.WEAK;
        }

        if (!lengthEnough) {
            return PasswordStrength.NORMAL;
        }
        if (!containsUpperCase) {
            return PasswordStrength.NORMAL;
        }
        if (!containsNumber) {
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
    }
}
