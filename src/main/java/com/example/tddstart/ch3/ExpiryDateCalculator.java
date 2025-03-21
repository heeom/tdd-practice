package com.example.tddstart.ch3;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {

    private static final BigDecimal DIVISOR = BigDecimal.valueOf(10_000);

    public LocalDate calculateExpiryDate(PayData payData) {
        int added_month = payData.getPayAmount().divide(DIVISOR).intValue();

        if (payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, added_month);
        }
        return getCandidateExpiryDate(payData.getBillingDate(), added_month);
    }

    private LocalDate expiryDateUsingFirstBillingDate(PayData payData, int added_month) {
        final int firstBillingDay = dayOfMonth(payData.getFirstBillingDate());
        LocalDate candidateExpiryDate = getCandidateExpiryDate(payData.getBillingDate(), added_month);

        if (isSameDayOfMonth(firstBillingDay, candidateExpiryDate)) {
            return getCandidateExpiryDate(payData.getBillingDate(), added_month);
        }

        int endOfCandidateExpiryMonth = lastDayOfMonth(candidateExpiryDate);
        if (endOfCandidateExpiryMonth < firstBillingDay) {
            return candidateExpiryDate.withDayOfMonth(endOfCandidateExpiryMonth);
        }
        return candidateExpiryDate.withDayOfMonth(firstBillingDay);

    }

    private static int lastDayOfMonth(LocalDate candidateExpiryDate) {
        return YearMonth.from(candidateExpiryDate).lengthOfMonth();
    }

    private boolean isSameDayOfMonth(int firstBillingDay, LocalDate candidateExpiryDate) {
        return firstBillingDay == dayOfMonth(candidateExpiryDate);
    }

    private LocalDate getCandidateExpiryDate(LocalDate date, int month) {
        return date.plusMonths(month);
    }

    private int dayOfMonth(LocalDate date) {
        return date.getDayOfMonth();
    }
}
