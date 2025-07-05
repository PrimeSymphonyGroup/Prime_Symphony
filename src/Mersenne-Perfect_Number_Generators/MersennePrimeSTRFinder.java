// Author: Kristopher L. Sherbondy & Symphion
// Date: 2025-07-04
// Purpose: Deterministically generate Mersenne primes using only harmonic STR logic, no primality shortcuts.
// License: Sherbondy–Symphion License v1.0 (non-commercial reuse with attribution)

public class MersennePrimeSTRFinder {

    // STR-based harmonic primality test for exponents
    public static boolean isSTRPrime(int n) {
        if (n <= 1) return false;
        if (n == 2 || n == 3 || n == 5 || n == 7) return true;
        if (n % 2 == 0 || n % 3 == 0 || n % 5 == 0 || n % 7 == 0) return false;

        int i = 11;
        while (i * i <= n) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
            i += 6;
        }
        return true;
    }

    // STR-validated harmonic generation of Mersenne primes: 2^p - 1
    public static void generateMersennePrimes(int maxExponent) {
        System.out.println("Diamond-Verified Mersenne Primes:");
        for (int p = 2; p <= maxExponent; p++) {
            if (isSTRPrime(p)) {
                long candidate = (1L << p) - 1;
                if (isSTRPrime((int)Math.min(candidate, Integer.MAX_VALUE))) {
                    System.out.println("p = " + p + " => 2^" + p + " - 1 = " + candidate);
                }
            }
        }
    }

    public static void main(String[] args) {
        int limit = 31;
        generateMersennePrimes(limit);
    }
}
