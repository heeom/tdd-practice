package com.example.tddstart.mockito;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;

@ExtendWith(MockitoExtension.class)
public class JUnit5ExtensionTest {

    @Mock
    private GameNumGen game;

    @Mock
    private List<String> mockList;

    @Test
    void stubTest() {
        given(game.generate(any())).willReturn("123");
        String result = game.generate(GameLevel.EASY);
        assertEquals("123", result);
    }

    @Test
    void mockThrowExceptionTest() {
        given(game.generate(null)).willThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> game.generate(null));
    }

    @Test
    void voidMethodWillThrowTest() {
        willThrow(UnsupportedOperationException.class).given(mockList).clear();

        assertThrows(UnsupportedOperationException.class, () -> mockList.clear());
    }
}
