package com.example.tddpractice.wordwrap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordWrapTest {

    @Test
    public void should_wrap() {
        assertEquals(wrap("word word", 4), "word\nword");
        assertEquals(wrap("a dog", 5), "a dog");
        assertEquals(wrap("a dog with a bone", 5), "a dog\nwith a \nbone");

    }

    private String wrap(String s, int length) {
        return s.length() > length ? s.replaceAll(" ", "\n") : s;

    }
}
