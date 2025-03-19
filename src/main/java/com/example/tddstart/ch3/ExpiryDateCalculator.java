package com.example.tddstart.ch3;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpiryDateCalculator {


    public LocalDate calculateExpiryDate(LocalDate billingDate, BigDecimal payAmount) {
        return calculateExpiryDate(new PayData(billingDate, payAmount, billingDate));
    }

    public LocalDate calculateExpiryDate(PayData payData) {
        return payData.getBillingDate().plusMonths(1);
    }
}
