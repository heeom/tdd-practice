package com.example.tddpractice.movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * 1. 가장 쉬운 테스트부터 추가
 * 2. 실패하는 테스트를 작성하고, 프로덕션 코드를 작성
 * 3. 최소한의 코드로 실패를 해결
 * 4. 리팩토링할 부분 찾아보기 -> 중복코드
 *  - 테스트 클래스에 테스트 대상이 되는 객체는 테스트 클래스에서 field variable 로 존재해야 한다.
 */
public class MovieTest {

    private Movie movie; // 중복 -> 테스트 클래스에 테스트 대상이 되는 객체는 테스트 클래스에서 field variable 로 존재해야 한다.

    @BeforeEach
    void setUp() {
        movie = new Movie();
    }

    @Test
    public void should_return_0_when_created() {
        assertThat(movie.averageRating()).isEqualTo(0);
    }

    @Test
    public void should_return_1_when_1_was_rated() {
        movie.rate(1);
        assertThat(movie.averageRating()).isEqualTo(1); // 최소한의 코드로 해결
    }

    @Test
    public void should_return_3_when_2_and4_was_rated() {
        movie.rate(3);
        movie.rate(4);
        assertThat(movie.averageRating()).isEqualTo(3);
    }
}
