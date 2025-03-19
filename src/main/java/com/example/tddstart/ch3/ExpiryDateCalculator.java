package com.example.tddstart.ch3;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpiryDateCalculator {


    public LocalDate calculateExpiryDate(LocalDate billingDate, BigDecimal payAmount) {
        return billingDate.plusMonths(1L);
    }
}
