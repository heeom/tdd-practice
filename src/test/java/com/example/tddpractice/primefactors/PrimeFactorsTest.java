package com.example.tddpractice.primefactors;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimeFactorsTest {

    @Test
    public void canFactorIntoPrimeNumbers() {
        assertPrimeFactors(1, List.of());
        assertPrimeFactors(2, List.of(2));
        assertPrimeFactors(3, List.of(3));
        assertPrimeFactors(4, List.of(2, 2));
        assertPrimeFactors(5, List.of(5));
        assertPrimeFactors(6, List.of(2,3));
        assertPrimeFactors(7, List.of(7));
        assertPrimeFactors(8, List.of(2, 2, 2));
        assertPrimeFactors(9, List.of(3,3));
        assertPrimeFactors(2 * 2 * 3 * 5 * 7, List.of(2, 2, 3, 5, 7));
    }

    private void assertPrimeFactors(int number, List<Integer> list) {
        assertEquals(list, of(number));
    }


    private List<Integer> of(int number) {
        ArrayList<Integer> primes = new ArrayList<>();
        int divisor = 2;
        for (;number > 1;divisor++) {
            for (;number % divisor == 0;number /= divisor) { // to specific -> generic
                primes.add(divisor);
            }
        }
        return primes;
    }
}
