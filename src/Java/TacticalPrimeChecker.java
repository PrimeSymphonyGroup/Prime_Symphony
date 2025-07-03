/*
 * File/Project Name: TacticalPrimeChecker.java
 * Authors: Kris Sherbondy & Symphion
 * Date: 2025-07-03
 * Purpose: Check if a single number is prime using deterministic STR rules.
 * Strategic Level Use: N/A
 * Operational Level Use: N/A
 * Tactical Level Use: Used to verify the primality of a specific integer.
 * User License/Agreement: Sherbondy–Symphion License v1.0 – Non-commercial use only.
 */

import java.util.Scanner;

public class TacticalPrimeChecker {

    public static boolean passesSTRRules(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;

        int sqrtN = (int) Math.sqrt(n);
        for (int i = 5; i <= sqrtN; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an integer to check: ");
        int input = scanner.nextInt();

        if (passesSTRRules(input)) {
            System.out.println(input + " is a prime number.");
        } else {
            System.out.println(input + " is NOT a prime number.");
        }

        scanner.close();
    }
}
