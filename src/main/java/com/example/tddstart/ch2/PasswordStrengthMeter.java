package com.example.tddstart.ch2;

import java.util.regex.Pattern;

public class PasswordStrengthMeter {

    private final Pattern NUMBER_PATTERN = Pattern.compile(".*[0-9].*");

    public PasswordStrength meter(String password) {
        if (password == null || password.isEmpty()) {
            return PasswordStrength.INVALID;
        }
        if (password.length() < 8 || !NUMBER_PATTERN.matcher(password).matches()) {
            return PasswordStrength.NORMAL;
        }
        return PasswordStrength.STRONG;
    }
}
