package com.example.tddpractice.wordwrap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordWrapTest {

    @Test
    public void should_wrap() {
        assertWraps(null, 1, "");
        assertWraps("", 1, "");
        assertWraps("s", 1, "s");
        assertWraps("ss", 1, "s\ns");
        assertWraps("sss", 1, "s\ns\ns");
        assertWraps("s s", 1, "s\ns");
        assertWraps("s ss", 3, "s\nss");
        assertWraps("four score and seven years ago our fathers brought forth upon this continent", 7, "four\nscore\nand\nseven\nyears\nago our\nfathers\nbrought\nforth\nupon\nthis\ncontine\nnt");
    }

    private void assertWraps(String s, int length, String expected) {
        assertEquals(wrap(s, length), expected);
    }

    private String wrap(String s, int i) {
        if (s == null) {
            return "";
        }
        if (s.length() <= i) {
            return s;
        } else {
            int point = s.lastIndexOf(" ", i);
            if (point == -1) {
                point = i;
            }
            return s.substring(0, point) + "\n" + wrap(s.substring(point).trim(), i);
        }
    }

}
