package com.example.tddstart.ch2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 검사할규칙은다음 세가지이다.
 * - 길이가 8글자 이상
 * - 0부터 9 사이의 숫자를 포함
 * - 대문자 포함
 *
 * • 세 규칙을 모두 충족하면 암호는 강함이다.
 * • 2개의 규칙을 층족하면 암호는 보통이다.
 * • 1개 이하의 규칙을 충족하면 암호는 약함이다
 *
 */
public class PasswordStrengthMeterTest {

    @Test
    @DisplayName("모든 조건을 충족하면 암호 강도는 강함이어야 함")
    void meets_all_criteria_then_strong() {
        PasswordStrengthMeter passwordStrengthMeter = new PasswordStrengthMeter();
        PasswordStrength result = passwordStrengthMeter.meter("ab1234AB");

        assertEquals(PasswordStrength.STRONG, result);

    }
}
