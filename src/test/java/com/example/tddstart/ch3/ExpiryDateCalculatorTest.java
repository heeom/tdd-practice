package com.example.tddstart.ch3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpiryDateCalculatorTest {

    @Test
    @DisplayName("10000원을 납부하면, 한달 뒤가 서비스 만료일이 된다")
    public void pay_10000_then_expiry_date_should_be_1_month_later() {
        assertExpiryDate(
                new PayData(
                        BigDecimal.valueOf(10_000),
                        LocalDate.of(2025, 3, 19)
                ),
                LocalDate.of(2025, 4, 19)
        );
        assertExpiryDate(
                new PayData(
                        BigDecimal.valueOf(10_000),
                        LocalDate.of(2025, 4, 19)
                ),
                LocalDate.of(2025, 5, 19)
        );
    }

    @Test
    @DisplayName("납부일과 한달뒤 날짜가 같지 않음")
    public void billing_date_not_matched_with_1_month_later_date() {
        assertExpiryDate(
                new PayData(
                        BigDecimal.valueOf(10_000),
                        LocalDate.of(2025, 1, 31)
                ),
                LocalDate.of(2025, 2, 28)
        );

        assertExpiryDate(
                new PayData(
                        BigDecimal.valueOf(10_000),
                        LocalDate.of(2025, 5, 31)
                ),
                LocalDate.of(2025, 6, 30)
        );

        assertExpiryDate(
                new PayData(
                        BigDecimal.valueOf(10_000),
                        LocalDate.of(2024, 1, 31)
                ),
                LocalDate.of(2024, 2, 29)
        );
    }

    @Test
    @DisplayName("첫번째 납부일과 두번째 납부일의 납부일자가 다를 때 10000원을 납부하면 만료일은 첫번째 납부일을 기준으로 정한다")
    public void first_billing_date_not_equal_to_second_billing_date() {
        PayData payData = new PayData(
                LocalDate.of(2025, 1, 31),
                BigDecimal.valueOf(10_000),
                LocalDate.of(2025, 2, 28)
        );

        assertExpiryDate(payData, LocalDate.of(2025, 3, 31));

        PayData secondPayData = new PayData(
                LocalDate.of(2025, 1, 30),
                BigDecimal.valueOf(10_000),
                LocalDate.of(2025, 2, 28)
        );

        assertExpiryDate(secondPayData, LocalDate.of(2025, 3, 30));

        PayData thirdPayData = new PayData(
                LocalDate.of(2025, 5, 31),
                BigDecimal.valueOf(10_000),
                LocalDate.of(2025, 6, 30)
        );

        assertExpiryDate(thirdPayData, LocalDate.of(2025, 7, 31));
    }

    @Test
    @DisplayName("20_000원을 납부하면 만료일은 2개월 뒤가 된다")
    public void pay_20_000_then_expiry_date_should_be_2_month_later() {

    }

    @Test
    @DisplayName("30_000원을 납부하면 만료일은 3개월 뒤가 된다")
    public void pay_30_000_then_expiry_date_should_be_3_month_later() {

    }


    private void assertExpiryDate(LocalDate billingDate, BigDecimal payAmount, LocalDate expectedExpiryDate) {
        assertExpiryDate(new PayData(billingDate, payAmount, billingDate), expectedExpiryDate);
    }

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator expiryDateCalculator = new ExpiryDateCalculator();

        LocalDate expiryDate = expiryDateCalculator.calculateExpiryDate(payData);

        Assertions.assertEquals(expectedExpiryDate, expiryDate);
    }
}
