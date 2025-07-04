import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PinThreadedFloatHash {

    public static String pinThreadedSalt(int prime, int level, double floatSeed, String pin, double scaling, long modPrime) {
        // Pad PIN to 6 digits
        while (pin.length() < 6) {
            pin = "0" + pin;
        }

        double value = (prime / (double) level) + floatSeed;

        for (int i = 0; i < 6; i++) {
            int digit = Character.getNumericValue(pin.charAt(i)) + 1; // Avoid 0 multiplier

            if (i % 2 == 0) {
                // FLOAT → INT
                value = ((long) (value * digit * scaling)) % modPrime;
            } else {
                // INT → FLOAT
                value = value / (level * floatSeed * digit);
            }
        }

        return sha256(String.valueOf(value));
    }

    public static String compressExpandCycle(String salt, String password, String pepper, int rounds) {
        String current = sha256(salt);
        for (int i = 0; i < rounds; i++) {
            current = sha512(current + password + pepper);
        }
        return current;
    }

    public static String finalHashWithPinThreading(int prime, int level, String password, String pin, double floatSeed, String pepper) {
        String salt = pinThreadedSalt(prime, level, floatSeed, pin, 1e8, 982451653L);
        String evolved = compressExpandCycle(salt, password, pepper, 3);
        return sha512(evolved + pin);
    }

    public static String sha256(String input) {
        return hash(input, "SHA-256");
    }

    public static String sha512(String input) {
        return hash(input, "SHA-512");
    }

    private static String hash(String input, String algorithm) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hash = digest.digest(input.getBytes());
            BigInteger number = new BigInteger(1, hash);
            StringBuilder hexString = new StringBuilder(number.toString(16));
            while (hexString.length() < (algorithm.equals("SHA-256") ? 64 : 128)) {
                hexString.insert(0, "0");
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not found: " + algorithm);
        }
    }

    public static void main(String[] args) {
        int prime = 101;
        int level = 7;
        String password = "hunter2";
        String pin = "734928";
        double floatSeed = 0.4142;
        String pepper = "QuantumGuard2025";

        String floatidHash = finalHashWithPinThreading(prime, level, password, pin, floatSeed, pepper);
        System.out.println("FLOATID: " + floatidHash);
    }
}
