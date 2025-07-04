/*
 * File Name: CompleteCryptAlgorithm.java
 * Authors: Kristopher L. Sherbondy & Symphion
 * Date: 2025-07-04
 * Purpose: Unified DSS-FLOATID identity generator using harmonic entropy, PIN threading,
 *          Pascal Triangle seed, and custom trigonometric-bitfold hashing.
 * License: Sherbondy–Symphion License v1.0 (Non-commercial use only)
 */

import java.util.*;
import java.math.BigInteger;

public class CompleteCryptAlgorithm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a 6-digit PIN: ");
        String pin = scanner.nextLine().trim();

        if (!pin.matches("\\d{6}")) {
            System.out.println("Invalid PIN. Must be 6 digits.");
            return;
        }

        List<Integer> pascalDiagonal = generatePascalDiagonal();
        List<Double> threaded = applyPinThreading(pascalDiagonal, pin);
        List<Double> trigged = applyTrigMix(threaded);
        String hash = bitfold(trigged);

        System.out.println("\nPIN: " + pin);
        System.out.println("Pascal Diagonal Used: " + (selectedDiagonal));
        System.out.println("TRIG-HARMONIC FLOATID: " + hash);
    }

    static int selectedDiagonal;

    public static List<Integer> generatePascalDiagonal() {
        Random rand = new Random();
        selectedDiagonal = rand.nextInt(96) + 5; // Diagonal 5 to 100
        List<Integer> diagonal = new ArrayList<>();
        for (int i = 0; i < selectedDiagonal; i++) {
            diagonal.add(binomialCoefficient(i + selectedDiagonal, selectedDiagonal));
        }
        return diagonal;
    }

    public static int binomialCoefficient(int n, int k) {
        BigInteger numerator = BigInteger.ONE;
        BigInteger denominator = BigInteger.ONE;
        for (int i = 1; i <= k; i++) {
            numerator = numerator.multiply(BigInteger.valueOf(n - i + 1));
            denominator = denominator.multiply(BigInteger.valueOf(i));
        }
        return numerator.divide(denominator).intValue();
    }

    public static List<Double> applyPinThreading(List<Integer> seed, String pin) {
        List<Double> output = new ArrayList<>();
        for (int i = 0; i < seed.size(); i++) {
            int pinDigit = Character.getNumericValue(pin.charAt(i % pin.length()));
            double modified = (i % 2 == 0) ? seed.get(i) * pinDigit : (double) seed.get(i) / (pinDigit + 1);
            output.add(modified);
        }
        return output;
    }

    public static List<Double> applyTrigMix(List<Double> input) {
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            double x = input.get(i);
            result.add(Math.sin(x) * Math.cos(x + i));
        }
        return result;
    }

    public static String bitfold(List<Double> input) {
        StringBuilder hashBuilder = new StringBuilder();
        int accumulator = 0;
        for (double d : input) {
            long bits = Double.doubleToLongBits(d);
            accumulator ^= (int) (bits & 0xFFFFFFFF);
            accumulator = Integer.rotateLeft(accumulator, 3) ^ 0xA5A5A5A5;
            hashBuilder.append(String.format("%08x", accumulator));
        }
        return hashBuilder.toString();
    }
}
