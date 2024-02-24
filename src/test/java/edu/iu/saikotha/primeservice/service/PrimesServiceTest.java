package edu.iu.saikotha.primeservice.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimesServiceTest {

    PrimesService primesService = new PrimesService();

    @Test
    void _45IsNotPrime() {
        int n = 45;
        boolean expected = false;
        boolean actual = primesService.isPrime(n);

        assertEquals(expected, actual);
    }

    @Test
        void _7659834758934797IsNotPrime()
        {
            long n = 7659834758934797L;
            boolean expected = false;
            boolean actual = primesService.isPrime(n);

            assertEquals(expected, actual);
        }

    @Test
    void _285191IsPrime()
    {
        int n = 285191;
        boolean expected = true;
        boolean actual = primesService.isPrime(n);

        assertEquals(expected, actual);
    }
}

