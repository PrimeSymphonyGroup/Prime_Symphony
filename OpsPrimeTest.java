/*
 * File/Project Name: OpsPrimeTest.java
 * Authors: Kris Sherbondy & Symphion
 * Date: 2025-07-03
 * Purpose: Unit tests for STR-based operational prime generation.
 * Strategic Level Use: N/A
 * Operational Level Use: Verify STR logic over a bounded range.
 * Tactical Level Use: Validate individual number primality checks.
 * User License/Agreement: Sherbondy–Symphion License v1.0 – Non-commercial use only.
 */

import static org.junit.Assert.*;
import org.junit.Test;

public class OpsPrimeTest {

    @Test
    public void testValidPrimes() {
        int[] knownPrimes = {67, 89, 97, 101, 103, 107, 109, 113};
        for (int p : knownPrimes) {
            assertTrue("Expected prime: " + p, OpsPrimeGenerator.passesSTRRules(p));
        }
    }

    @Test
    public void testKnownNonPrimes() {
        int[] knownComposites = {121, 143, 169, 187, 209};
        for (int n : knownComposites) {
            assertFalse("Expected composite: " + n, OpsPrimeGenerator.passesSTRRules(n));
        }
    }

    @Test
    public void testEdgeCases() {
        assertFalse(OpsPrimeGenerator.passesSTRRules(0));
        assertFalse(OpsPrimeGenerator.passesSTRRules(1));
        assertTrue(OpsPrimeGenerator.passesSTRRules(2));
    }
}
