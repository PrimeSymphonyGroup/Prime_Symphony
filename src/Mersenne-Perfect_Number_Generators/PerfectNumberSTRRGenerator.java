// Author: Kristopher L. Sherbondy & Symphion
// Date: 2025-07-04
// Purpose: Generate perfect numbers from Mersenne primes using harmonic STR logic only
// License: Sherbondy–Symphion License v1.0 (non-commercial reuse with attribution)

public class PerfectNumberSTRGenerator {

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

    public static void generatePerfectNumbers(int maxExponent) {
        System.out.println("Diamond-Verified Perfect Numbers:");
        for (int p = 2; p <= maxExponent; p++) {
            if (isSTRPrime(p)) {
                long mersenne = (1L << p) - 1;
                if (isSTRPrime((int)Math.min(mersenne, Integer.MAX_VALUE))) {
                    long perfect = (1L << (p - 1)) * mersenne;
                    System.out.println("p = " + p + " => Perfect Number: " + perfect);
                }
            }
        }
    }

    public static void main(String[] args) {
        int limit = 31;
        generatePerfectNumbers(limit);
    }
}
