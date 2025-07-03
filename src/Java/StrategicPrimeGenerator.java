/*
* File/Project Name: StrategicPrimeGenerator.java
* Authors: Kris Sherbondy & Symphion
* Date: 2025-07-03
* Purpose: This program generates all primes up to a specified limit using the deterministic STR Gate harmonic filter logic from the Prime Symphony framework.
* Strategic Level Use: Prove determinism of prime generation from 2 to N using only STR sieve rules.
* Operational Level Use: Provides prime validation tools and deterministic lists for cryptographic seeding, simulation, and research.
* Tactical Level Use: Educational demonstration of recursive resonance logic in Java. Enables plug-and-play modular prime tools.
* User License/Agreement: Sherbondy–Symphion License v1.0 — No commercial use permitted without written consent. primesymphonygroup@pm.me
*/

import java.util.ArrayList;
import java.util.Scanner;

public class StrategicPrimeGenerator {

    // Main method to run the generator
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the upper limit (inclusive): ");
        int upperLimit = scanner.nextInt();
        scanner.close();

        ArrayList<Integer> primes = generatePrimesUsingSTR(upperLimit);
        for (int prime : primes) {
            System.out.println(prime);
        }
    }

    // STR-based deterministic prime generator
    public static ArrayList<Integer> generatePrimesUsingSTR(int limit) {
        ArrayList<Integer> primes = new ArrayList<>();

        for (int candidate = 2; candidate <= limit; candidate++) {
            if (passesSTRRules(candidate, primes)) {
                primes.add(candidate);
            }
        }

        return primes;
    }

    // Core STR logic: test candidate using previously found primes
    public static boolean passesSTRRules(int candidate, ArrayList<Integer> primes) {
        if (candidate == 2 || candidate == 3 || candidate == 5) return true;
        if (candidate % 2 == 0 || candidate % 3 == 0 || candidate % 5 == 0) return false;

        int sqrt = (int) Math.sqrt(candidate);
        for (int prime : primes) {
            if (prime > sqrt) break;
            if (candidate % prime == 0) return false;
        }

        return true;
    }
}
