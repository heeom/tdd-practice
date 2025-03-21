package com.example.tddstart.ch3;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpiryDateCalculator {

    public LocalDate calculateExpiryDate(PayData payData) {
        int firstBillingDay = payData.getFirstBillingDate().getDayOfMonth();
        int candidateExpiryDay = payData.getBillingDate().plusMonths(1).getDayOfMonth();
        if (firstBillingDay != candidateExpiryDay) {
            return payData.getBillingDate().plusMonths(1).withDayOfMonth(firstBillingDay);
        }
        return payData.getBillingDate().plusMonths(1);
    }
}
