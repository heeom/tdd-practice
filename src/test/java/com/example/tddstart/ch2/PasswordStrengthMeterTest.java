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

    private PasswordStrengthMeter passwordStrengthMeter = new PasswordStrengthMeter();

    @Test
    @DisplayName("모든 조건을 충족하면 암호 강도는 강함이어야 한다")
    void meets_all_criteria_then_strong() {
        assertStrength(PasswordStrength.STRONG,"ab1234AB");
        assertStrength(PasswordStrength.STRONG,"abc1!Add");
    }

    @Test
    @DisplayName("길이조건을 충족하지 않고, 나머지 두 조건은 충족하는 경우 강도는 보통이어야 한다")
    public void meets_other_criteria_except_for_length_then_normal() {
        assertStrength(PasswordStrength.NORMAL,"ab12AB");
    }

    @Test
    @DisplayName("숫자를 포함하지 않고, 나머지 두 조건은 충족하는 경우 강도는 보통이어야 한다")
    public void meets_other_criteria_except_for_number_then_strong() {
        assertStrength(PasswordStrength.NORMAL,"ABabcdEF");
    }

    @Test
    @DisplayName("null을 입력하면, INVALID를 리턴해야한다")
    public void input_null_then_invalid() {
        assertStrength(PasswordStrength.INVALID,null);
    }

    @Test
    @DisplayName("빈문자열을 입력하면, INVALID를 리턴해야한다")
    public void input_empty_then_invalid() {
        assertStrength(PasswordStrength.INVALID,"");
    }

    @Test
    @DisplayName("대문자를 포함하지 않고, 나머지 조건은 모두 충족하는 경우 강도는 보통이어야 한다")
    public void meets_other_criteria_except_for_uppercase_then_normal() {
        assertStrength(PasswordStrength.NORMAL,"ab1234cd");
    }

    @Test
    @DisplayName("길이가 8글자 이상인 조건만 충족하는 경우 강도는 약함이어야 한다")
    public void meets_only_length_criteria_then_weak() {
        assertStrength(PasswordStrength.WEAK, "abcdefgh");
    }

    @Test
    @DisplayName("숫자 포함 조건만 충족하는 경우 강도는 약함이어야 한다")
    public void meets_only_number_criteria_then_weak() {
        assertStrength(PasswordStrength.WEAK, "a123456");
    }

    private void assertStrength(PasswordStrength expected, String password) {
        PasswordStrength result = passwordStrengthMeter.meter(password);
        assertEquals(expected, result);
    }
}
