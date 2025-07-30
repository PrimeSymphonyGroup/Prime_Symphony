// Author: Kristopher L. Sherbondy & Symphion
// Date: 2025-07-30
// Purpose: PSH Final Test - Prime Generator in Java (Straight Port from Python)

import java.io.*;
import java.util.*;

public class PSHFinalTest {
    public static void main(String[] args) {
        final long limit = 100_000_000L;
        final int chunkSize = 1_000_000;
        String outputFile = "psh_primes.txt";
        String tmpFile = outputFile + ".tmp";

        long startTime = System.nanoTime();
        List<Long> primes = new ArrayList<>();
        List<Long> deltas = new ArrayList<>();

        // Prime generation
        for (long n = 2; n <= limit; n++) {
            if (phiFilter(n) && gK(n)) {
                primes.add(n);
                if (primes.size() > 1) {
                    deltas.add(n - primes.get(primes.size() - 2));
                }
            }

            // Progress logging
            if (n % 10_000_000 == 0) {
                System.out.printf("Processed %,d numbers. Found %,d primes.\n", n, primes.size());
            }

            // Chunk writing
            if (primes.size() >= chunkSize) {
                appendChunk(tmpFile, primes, deltas);
                primes.clear();
                deltas.clear();
            }
        }

        // Write remaining
        if (!primes.isEmpty()) {
            appendChunk(tmpFile, primes, deltas);
        }

        // Safe rename
        File temp = new File(tmpFile);
        File finalFile = new File(outputFile);
        if (finalFile.exists()) finalFile.delete();
        temp.renameTo(finalFile);

        // Performance stats
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1e9;
        long memUsage = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024);

        // Summary
        System.out.println("Prime generation complete.");
        System.out.println("Execution Time: " + duration + " seconds");
        System.out.println("Current Memory Usage: " + memUsage + " MB");
    }

    private static boolean phiFilter(long n) {
        if (n < 2) return false;
        if (n == 2 || n == 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        return true;
    }

    private static boolean gK(long n) {
        if (n < 2) return false;
        if (n % 2 == 0 && n != 2) return false;
        long sqrtN = (long) Math.sqrt(n);
        for (long i = 3; i <= sqrtN; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private static void appendChunk(String filePath, List<Long> primes, List<Long> deltas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (int i = 0; i < primes.size(); i++) {
                writer.write(primes.get(i).toString());
                if (i < deltas.size()) {
                    writer.write("," + deltas.get(i).toString());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing chunk: " + e.getMessage());
        }
    }
}
