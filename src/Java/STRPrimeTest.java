/*
* File/Project Name: STRPrimeTest.java
* Authors: Kris Sherbondy & Symphion
* Date: 2025-07-03
* Purpose: Unit tests for validating STR-based deterministic prime generator logic.
* Strategic Level Use: Ensures correctness of prime rule filter via recursive STR logic.
* Operational Level Use: Used for ongoing verification of algorithm evolution and PR validation.
* Tactical Level Use: Teaching/testbed for verifying deterministic logic implementations.
* User License/Agreement: Sherbondy–Symphion License v1.0 — No commercial use permitted without written consent. primesymphonygroup@pm.me
*/

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class STRPrimeTest {

    @Test
    public void testKnownPrimes() {
        ArrayList<Integer> primesSoFar = new ArrayList<>();
        assertTrue(StrategicPrimeGenerator.passesSTRRules(2, primesSoFar));  primesSoFar.add(2);
        assertTrue(StrategicPrimeGenerator.passesSTRRules(3, primesSoFar));  primesSoFar.add(3);
        assertTrue(StrategicPrimeGenerator.passesSTRRules(5, primesSoFar));  primesSoFar.add(5);
        assertTrue(StrategicPrimeGenerator.passesSTRRules(7, primesSoFar));  primesSoFar.add(7);
        assertTrue(StrategicPrimeGenerator.passesSTRRules(29, primesSoFar)); primesSoFar.add(29);
    }

    @Test
    public void testKnownNonPrimes() {
        ArrayList<Integer> primesSoFar = new ArrayList<>();
        primesSoFar.add(2); primesSoFar.add(3); primesSoFar.add(5); primesSoFar.add(7); primesSoFar.add(11);
        assertFalse(StrategicPrimeGenerator.passesSTRRules(4, primesSoFar));
        assertFalse(StrategicPrimeGenerator.passesSTRRules(9, primesSoFar));
        assertFalse(StrategicPrimeGenerator.passesSTRRules(15, primesSoFar));
        assertFalse(StrategicPrimeGenerator.passesSTRRules(21, primesSoFar));
    }

    @Test
    public void testEdgeCases() {
        ArrayList<Integer> primesSoFar = new ArrayList<>();
        assertFalse(StrategicPrimeGenerator.passesSTRRules(1, primesSoFar));
        assertFalse(StrategicPrimeGenerator.passesSTRRules(0, primesSoFar));
    }
}
