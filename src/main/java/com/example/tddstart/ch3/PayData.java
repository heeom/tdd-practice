package com.example.tddstart.ch3;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PayData {

    private LocalDate firstBillingDate;
    private BigDecimal payAmount;
    private LocalDate billingDate;

    public PayData(LocalDate firstBillingDate, BigDecimal payAmount, LocalDate billingDate) {
        this.firstBillingDate = firstBillingDate;
        this.payAmount = payAmount;
        this.billingDate = billingDate;
    }

    public LocalDate getFirstBillingDate() {
        return firstBillingDate;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }
}
