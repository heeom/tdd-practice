package com.example.tddstart.ch5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * 실행 결과

 * new LifeCycleTest()
 * set up
 * A test
 * tear down

 * new LifeCycleTest()
 * set up
 * B test
 * tear down

 * 1. 테스트 메서드 포함 객체 생성
 * 2. @BeforeEach 애노테이션이 붙은 메서드 실행
 * 3. @Test 애노테이션이 붙은 메서드 실행
 * 4. @AfterEach 애노테이션이 붙은 메서드 실행
 *
 *
 * @Test, @BeforeEach, @AfterEach 애너테이션은 자바 리플렉션을 사용해서 동작하기 때문에 접근 제어자가 private이면 안된다.
 * 최소 default여야 함
 */

public class LifeCycleTest {

    public LifeCycleTest() {
        System.out.println("new LifeCycleTest()");
    }

    @BeforeEach
    void setup() {
        System.out.println("set up");
    }

    @Test
    void a() {
        System.out.println("A test");
    }

    @Test
    void b() {
        System.out.println("B test");
    }

    @AfterEach
    void teardown() {
        System.out.println("tear down");
    }
}
