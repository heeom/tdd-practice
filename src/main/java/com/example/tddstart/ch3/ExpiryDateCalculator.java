package com.example.tddstart.ch3;

import java.time.LocalDate;

public class ExpiryDateCalculator {

    private static final int ADDED_MONTH = 1;

    public LocalDate calculateExpiryDate(PayData payData) {
        if (payData.getFirstBillingDate() != null) {
            int firstBillingDay = getDay(payData.getFirstBillingDate());
            LocalDate candidateExpiryDate = getCandidateExpiryDate(payData.getBillingDate(), ADDED_MONTH);
            if (firstBillingDay != getDay(candidateExpiryDate)) {
                return candidateExpiryDate.withDayOfMonth(firstBillingDay);
            }
        }
        return getCandidateExpiryDate(payData.getBillingDate(), ADDED_MONTH);
    }

    private LocalDate getCandidateExpiryDate(LocalDate date, int month) {
        return date.plusMonths(month);
    }

    private int getDay(LocalDate date) {
        return date.getDayOfMonth();
    }
}
