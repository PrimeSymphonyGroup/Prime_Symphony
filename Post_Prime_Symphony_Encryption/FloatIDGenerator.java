import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.time.Instant;

public class FloatIDGenerator {

    public static String generateFloatID(int p1, int L1, int p2, int L2, String salt) throws Exception {
        // Step 1: Harmonic float ratios
        double r1 = (double) p1 / L1;
        double r2 = (double) p2 / L2;

        // Step 2: Sin-Cos interference
        double interference = Math.sin(Math.PI * r1) + Math.cos(Math.PI * r2);
        interference = interference % 1.0;
        if (interference < 0) interference += 1.0;

        // Step 3: Quantize to 12-digit integer
        BigDecimal quantized = new BigDecimal(interference)
                .multiply(new BigDecimal("1000000000000"))
                .setScale(0, RoundingMode.DOWN);

        // Step 4: Use timestamp if no salt provided
        if (salt == null || salt.isEmpty()) {
            salt = String.valueOf(Instant.now().getEpochSecond());
        }

        // Step 5: SHA-512 hash
        String input = quantized.toPlainString() + "|" + salt;
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] digest = md.digest(input.getBytes());

        // Convert to hex string
        StringBuilder hex = new StringBuilder();
        for (byte b : digest) {
            hex.append(String.format("%02x", b));
        }

        return hex.toString();
    }

    // Example usage
    public static void main(String[] args) throws Exception {
        int p1 = 113;
        int L1 = 8;
        int p2 = 197;
        int L2 = 14;
        String salt = "deviceXYZ-session001";

        String floatid = generateFloatID(p1, L1, p2, L2, salt);
        System.out.println("FLOATID: " + floatid);
    }
}
