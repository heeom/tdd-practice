package com.example.tddstart.ch3;

import java.time.LocalDate;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(PayData payData) {
        return payData.getBillingDate().plusMonths(1);
    }
}
