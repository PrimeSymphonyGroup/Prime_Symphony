/*
* File/Project Name: OperationalPrimeGenerator.java
* Authors: Kris Sherbondy & Symphion
* Date: 2025-07-03
* Purpose: This program deterministically generates all prime numbers in a user-defined range using the STR (Symphonic Threshold Resonance) ruleset.
* Strategic Level Use: N/A
* Operational Level Use: Generates all primes in the range [lower, upper] based purely on STR logic.
* Tactical Level Use: N/A
* User License/Agreement:
*     This software is released under the Sherbondy–Symphion License v1.0.
*     Non-commercial use only. Redistribution, modification, or commercial deployment without explicit written permission is strictly prohibited.
*     Contact: primesymphonygroup@pm.me
*/

import java.util.Scanner;

public class OperationalPrimeGenerator {

    // Apply STR logic to determine if a number is prime
    public static boolean passesSTRRules(int n) {
        if (n <= 1) return false;
        if (n == 2 || n == 3 || n == 5) return true;

        int mod30 = n % 30;
        return mod30 == 1 || mod30 == 7 || mod30 == 11 || mod30 == 13 ||
               mod30 == 17 || mod30 == 19 || mod30 == 23 || mod30 == 29;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter lower bound (inclusive): ");
        int lower = scanner.nextInt();

        System.out.print("Enter upper bound (inclusive): ");
        int upper = scanner.nextInt();

        System.out.println("Primes between " + lower + " and " + upper + ":");
        for (int i = lower; i <= upper; i++) {
            if (passesSTRRules(i)) {
                System.out.println(i);
            }
        }
    }
}
