package com.example.tddstart.mockito;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.mock;

/**
 * Mockito 기초 사용법
 * - 의존 설정
 * - 모의객체 설정
 * - 스텁 설정
 * - 행위 검증
 * - 인자 캡쳐
 */
public class GameGenMockTest {

    @Test
    void mockTest() {
        // 1. 모의 객체 생성 - Mockito.mock() : 클래스, 인터페이스, 추상클래스에 대해 모의 객체를 생성할 수 있다.
        GameNumGen genMock = mock(GameNumGen.class); // 모의 객체 생성
        // 2. 스텁 설정 - BDDMockito.given() : 모의 객체에 스텁을 구성할 수 있다. 예외발생시키려면 willThrow() 메서드를 사용하면 된다.
        given(genMock.generate(GameLevel.EASY)).willReturn("123");
        // 3. 스텁 설정에 매칭되는 메서드 실행
        String num = genMock.generate(GameLevel.EASY);
        assertEquals("123", num);
    }

    @Test
    void mockThrowExceptionTest() {
        GameNumGen genMock = mock(GameNumGen.class);

        // 타입 대신에 Exception 객체를 인자로 받는 willThrow 메서드를 사용해도 된다. new IllegalArgumentException();
        given(genMock.generate(null)).willThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> genMock.generate(null));
    }

    @Test
    void voidMethodWillThrowTest() {
        List<String> mockList = mock(List.class);

        // 리턴 타입이 void 인 메서드에 대해 Exception을 발생하려면, BDDMockito.willThrow() 메서드로 시작
        // given에는 모의객체의 메서드 실행이 아니라 모의객체를 전달 받는다.
        // given 메서드는 인자로 전달 받은 모의객체 자신을 리턴하는데 이때 Exception을 발생할 메서드를 호출한다. (clear())
        willThrow(UnsupportedOperationException.class).given(mockList).clear();
    }

    @Test
    void anyMatchTest() {
        // Mockito는 일치하는 stub 설정이 없을 경우 리턴타입의 기본값을 리턴
        // mockito.ArgumentMatchers 클래스로 임의의 값에 대해 일치하도록 설정 가능
        // Mockito, BDDMockito 클래스 모두 ArgumentMatchers 클래스 상속 -> Mockito.any(), BDDMockito.any() 사용가능
        GameNumGen mockGen = mock(GameNumGen.class);
        given(mockGen.generate(any())).willReturn("456");

        String num = mockGen.generate(GameLevel.EASY);
        assertEquals("456", num);

        String num2 = mockGen.generate(GameLevel.MEDIUM);
        assertEquals("456", num2);
    }

    @Test
    void mixAnyAndEq() {
        List<String> mockList = mock(List.class);
//        ArgumentMatchers의 anyInt()나 any() 등의 메서드는 내부적으로 인자의 일치 여부를 판 단하기 위해 ArgumentMatcher를 등록한다. Mockito는 한 인자라도 ArgumentMatcherS" 사용해서 설정한 경우 모든 인자를 ArgumentMatcher를 이용해서 설정하도록 하고 있다.
//        given(mockList.set(anyInt(), "123")).willReturn("456");

        // -> 임의의 값과 일치하는 인자와 정확하게 일치하는 인자를 함께 사용하고 싶다면 ArgumentMatchers.eq() 메서드를 사용해야 한다.
        given(mockList.set(anyInt(), eq("123"))).willReturn("456");

        String result = mockList.set(1, "123");
        assertEquals("456", result);
    }
}
