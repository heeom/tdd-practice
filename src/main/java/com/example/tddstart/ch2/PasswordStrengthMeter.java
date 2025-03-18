package com.example.tddstart.ch2;

import java.util.regex.Pattern;

public class PasswordStrengthMeter {

    private final Pattern NUMBER_PATTERN = Pattern.compile(".*[0-9].*");
    private final Pattern UPPER_CASE_PATTERN = Pattern.compile(".*[A-Z].*");

    public PasswordStrength meter(String password) {
        if (password == null || password.isEmpty()) {
            return PasswordStrength.INVALID;
        }
        int count = getMetCriteriaCounts(password);

        if (count <= 1) {
            return PasswordStrength.WEAK;
        }
        if (count == 2) {
            return PasswordStrength.NORMAL;
        }

        return PasswordStrength.STRONG;
    }

    private int getMetCriteriaCounts(String password) {
        int count = 0;
        if (password.length() >= 8) {
            count++;
        }
        if (UPPER_CASE_PATTERN.matcher(password).matches()) {
            count++;
        }
        if (NUMBER_PATTERN.matcher(password).matches()) {
            count++;
        }
        return count;
    }
}
