import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HarmonicHashEngine {

    // Compute binomial coefficient C(n, k)
    public static BigInteger binomialCoefficient(int n, int k) {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= k; i++) {
            result = result.multiply(BigInteger.valueOf(n - (i - 1)))
                           .divide(BigInteger.valueOf(i));
        }
        return result;
    }

    // Generate Pascal diagonal values for a given index
    public static BigInteger[] getPascalDiagonal(int diagonalIndex, int depth) {
        BigInteger[] values = new BigInteger[depth];
        for (int i = 0; i < depth; i++) {
            values[i] = binomialCoefficient(diagonalIndex + i, diagonalIndex);
        }
        return values;
    }

    // Harmonic hash function
    public static String harmonicHash(String input, String pin, int rounds) throws NoSuchAlgorithmException {
        double accumulator = 0.0;
        String hashMaterial = input + pin;

        for (int i = 0; i < rounds; i++) {
            int pinDigit = Character.getNumericValue(pin.charAt(i % pin.length()));
            int diagIndex = 5 + ((i * pinDigit) % 96); // Between 5 and 100
            BigInteger[] diagonal = getPascalDiagonal(diagIndex, 200);
            BigInteger modValRaw = diagonal[i % diagonal.length].mod(BigInteger.valueOf(pinDigit + 1));
            int modVal = modValRaw.intValue();

            double harmonic = Math.sin(accumulator + pinDigit) + Math.cos(accumulator + pinDigit);
            accumulator += harmonic * modVal;

            char c = hashMaterial.charAt(i % hashMaterial.length());
            if (i % 2 == 0) {
                accumulator *= ((double) c) / (pinDigit + 1);
            } else {
                accumulator += ((int) c) * pinDigit;
            }
        }

        // Convert final float accumulator to SHA-512 hash
        String accString = Double.toString(accumulator);
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        byte[] hash = digest.digest(accString.getBytes());

        StringBuilder hexHash = new StringBuilder();
        for (byte b : hash) {
            hexHash.append(String.format("%02x", b));
        }

        return hexHash.toString();
    }

    // Example usage
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String input = "DSS-PrimeSymphony-Test";
        String pin = "582947";
        int rounds = 64;

        String hash = harmonicHash(input, pin, rounds);
        System.out.println("HARMONIC HASH: " + hash);
    }
}
