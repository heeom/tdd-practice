package com.example.tddstart.ch3;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

public class ExpiryDateCalculator {

    private static final BigDecimal DIVISOR = BigDecimal.valueOf(10_000);

    public LocalDate calculateExpiryDate(PayData payData) {
        int added_month = payData.getPayAmount().divide(DIVISOR).intValue();

        if (payData.getFirstBillingDate() != null) {
            int firstBillingDay = getDay(payData.getFirstBillingDate());
            LocalDate candidateExpiryDate = getCandidateExpiryDate(payData.getBillingDate(), added_month);
            if (firstBillingDay != getDay(candidateExpiryDate)) {
                int endOfCandidateExpiryMonth = YearMonth.from(candidateExpiryDate).lengthOfMonth();
                if (endOfCandidateExpiryMonth < firstBillingDay) {
                    return candidateExpiryDate.withDayOfMonth(endOfCandidateExpiryMonth);
                }
                return candidateExpiryDate.withDayOfMonth(firstBillingDay);
            }
        }
        return getCandidateExpiryDate(payData.getBillingDate(), added_month);
    }

    private LocalDate getCandidateExpiryDate(LocalDate date, int month) {
        return date.plusMonths(month);
    }

    private int getDay(LocalDate date) {
        return date.getDayOfMonth();
    }
}
