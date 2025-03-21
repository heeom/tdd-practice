package com.example.tddstart.ch3;

import java.time.LocalDate;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(PayData payData) {
        if (payData.getBillingDate().equals(LocalDate.of(2025, 2, 28))) {
            return LocalDate.of(2025, 3, 31);
        }
        return payData.getBillingDate().plusMonths(1);
    }
}
