/**
 * PrimeGapAnalyzer.java
 * Author: Kristopher L. Sherbondy & Symphion
 * Date: 2025-07-04
 * Purpose: Analyze prime gaps from STR Prime Generator to reveal harmonic structure linked to the Riemann Zeta Function.
 */

import java.util.*;
import java.math.BigInteger;
import java.io.*;

public class PrimeGapAnalyzer {

    public static List<Integer> generatePrimes(int limit) {
        List<Integer> primes = new ArrayList<>();
        if (limit >= 2) primes.add(2);
        for (int num = 3; num <= limit; num += 2) {
            if (isPrime(num)) {
                primes.add(num);
            }
        }
        return primes;
    }

    private static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        int sqrt = (int) Math.sqrt(n);
        for (int i = 5; i <= sqrt; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    public static Map<Integer, Integer> computeGapFrequencies(List<Integer> primes) {
        Map<Integer, Integer> gapCounts = new TreeMap<>();
        for (int i = 1; i < primes.size(); i++) {
            int gap = primes.get(i) - primes.get(i - 1);
            gapCounts.put(gap, gapCounts.getOrDefault(gap, 0) + 1);
        }
        return gapCounts;
    }

    public static void exportGapFrequencies(Map<Integer, Integer> gapCounts, String filename) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            out.println("Gap,Frequency");
            for (Map.Entry<Integer, Integer> entry : gapCounts.entrySet()) {
                out.println(entry.getKey() + "," + entry.getValue());
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        int limit = 100000; // Can be adjusted for deeper analysis
        List<Integer> primes = generatePrimes(limit);
        Map<Integer, Integer> gaps = computeGapFrequencies(primes);
        exportGapFrequencies(gaps, "prime_gaps.csv");

        System.out.println("Prime gap analysis complete. Data exported to prime_gaps.csv");
    }
} 
