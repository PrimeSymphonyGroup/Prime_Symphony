import static org.junit.Assert.*;
import org.junit.Test;

public class STRPrimeTest {

    @Test
    public void testSmallPrimes() {
        assertTrue(StrategicPrimeGenerator.passesSTRRules(2));
        assertTrue(StrategicPrimeGenerator.passesSTRRules(3));
        assertTrue(StrategicPrimeGenerator.passesSTRRules(5));
        assertTrue(StrategicPrimeGenerator.passesSTRRules(7));
        assertTrue(StrategicPrimeGenerator.passesSTRRules(29));
    }

    @Test
    public void testNonPrimes() {
        assertFalse(StrategicPrimeGenerator.passesSTRRules(4));
        assertFalse(StrategicPrimeGenerator.passesSTRRules(9));
        assertFalse(StrategicPrimeGenerator.passesSTRRules(15));
        assertFalse(StrategicPrimeGenerator.passesSTRRules(21));
    }

    @Test
    public void testEdgeCases() {
        assertFalse(StrategicPrimeGenerator.passesSTRRules(1));
        assertFalse(StrategicPrimeGenerator.passesSTRRules(0));
    }
}
