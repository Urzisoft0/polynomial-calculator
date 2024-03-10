import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;
import java.util.TreeMap;
class PolynomialTest {
    @org.junit.jupiter.api.Test
    void addition1() throws Exception {
        Polynomial poly1 = new Polynomial("x^2 + 1");
        Polynomial poly2 = new Polynomial("x^3 - 2x^2 - 1");
        Map<Integer, Integer> result = poly1.Addition1(poly2.gTreeMap());
        Polynomial poly3 = new Polynomial(result);
        assertEquals("x^3 - x^2", poly3.toStringInteger(poly3.gTreeMap()));
    }

    @org.junit.jupiter.api.Test
    void addition2() throws Exception {
        Polynomial poly1 = new Polynomial("72x + 4x^3 - 5x^2");
        Polynomial poly2 = new Polynomial("-4x^3 + 5x^2 - 72x^1");
        Map<Integer, Integer> result = poly1.Addition1(poly2.gTreeMap());
        Polynomial poly3 = new Polynomial(result);
        assertEquals("", poly3.toStringInteger(poly3.gTreeMap()));
    }

    @org.junit.jupiter.api.Test
    void addition3() throws Exception {
        Polynomial poly1 = new Polynomial("");
        Polynomial poly2 = new Polynomial("72x^45 - 2x^3 + 7x^ - 9x^0");
        Map<Integer, Integer> result = poly1.Addition1(poly2.gTreeMap());
        Polynomial poly3 = new Polynomial(result);
        assertEquals("72x^45 - 2x^3 + 7x - 9", poly3.toStringInteger(poly3.gTreeMap()));
    }

    @org.junit.jupiter.api.Test
    void addition4() throws Exception {
        Polynomial poly1 = new Polynomial("321x^4 - 32");
        Polynomial poly2 = new Polynomial("22x^3 - 4x + 21474483645");
        Map<Integer, Integer> result = poly1.Addition1(poly2.gTreeMap());
        Polynomial poly3 = new Polynomial(result);
        assertEquals("321x^4 + 22x^3 - 4x - 32", poly3.toStringInteger(poly3.gTreeMap()));
    }

    @org.junit.jupiter.api.Test
    void subtraction1() throws Exception {
        Polynomial poly1 = new Polynomial("45x^2 - 2x^1 + 991");
        Polynomial poly2 = new Polynomial("38x^4 - 7x^2 + 10x");
        Map<Integer, Integer> result = poly1.Subtraction(poly2.gTreeMap());
        Polynomial poly3 = new Polynomial(result);
        assertEquals(" - 38x^4 + 52x^2 - 12x + 991", poly3.toStringInteger(poly3.gTreeMap()));
    }

    @org.junit.jupiter.api.Test
    void subtraction2() throws Exception {
        Polynomial poly1 = new Polynomial("-12x^6  + 2");
        Polynomial poly2 = new Polynomial("-12x^6 + 2x^0");
        Map<Integer, Integer> result = poly1.Subtraction(poly2.gTreeMap());
        Polynomial poly3 = new Polynomial(result);
        assertEquals("", poly3.toStringInteger(poly3.gTreeMap()));
    }

    @org.junit.jupiter.api.Test
    void subtraction3() throws Exception {
        Polynomial poly1 = new Polynomial("1233112333212300");
        Polynomial poly2 = new Polynomial("-2 + x^222");
        Map<Integer, Integer> result = poly1.Subtraction(poly2.gTreeMap());
        Polynomial poly3 = new Polynomial(result);
        assertEquals(" - x^222 + 2", poly3.toStringInteger(poly3.gTreeMap()));
    }

    @org.junit.jupiter.api.Test
    void multiply1() throws Exception {
        Polynomial poly1 = new Polynomial("0");
        Polynomial poly2 = new Polynomial("2x^2 - 98x + 13333");
        Map<Integer, Integer> result = poly1.Multiply(poly2.gTreeMap());
        Polynomial poly3 = new Polynomial(result);
        assertEquals("0", poly3.toStringInteger(poly3.gTreeMap()));
    }

    @org.junit.jupiter.api.Test
    void multiply2() throws Exception {
        Polynomial poly1 = new Polynomial("2x^5 - 19x^10 + 4x");
        Polynomial poly2 = new Polynomial("x^4 + 1 - 22x^7");
        Map<Integer, Integer> result = poly1.Multiply(poly2.gTreeMap());
        Polynomial poly3 = new Polynomial(result);
        assertEquals("418x^17 - 19x^14 - 44x^12 - 19x^10 + 2x^9 - 88x^8 + 6x^5 + 4x", poly3.toStringInteger(poly3.gTreeMap()));
    }

    @org.junit.jupiter.api.Test
    void multiply3() throws Exception {
        Polynomial poly1 = new Polynomial("3213123x");
        Polynomial poly2 = new Polynomial("3123123331x^3");
        Map<Integer, Integer> result = poly1.Multiply(poly2.gTreeMap());
        Polynomial poly3 = new Polynomial(result);
        assertEquals("", poly3.toStringInteger(poly3.gTreeMap()));
    }

    @org.junit.jupiter.api.Test
    void division() throws Exception {
        Polynomial poly1 = new Polynomial("x^4 + 5");
        Polynomial poly2 = new Polynomial("0");
        Exception exception = assertThrows(Exception.class, () -> {
            Map<Integer, Double> result = poly1.division(poly2.gTreeMap());
        });
        assertTrue(exception.getMessage().contains("Division by 0"));
    }

    @org.junit.jupiter.api.Test
    void division2() throws Exception {
        Polynomial poly1 = new Polynomial("0");
        Polynomial poly2 = new Polynomial("6x^5 - 23x^669 - 990x^21 + 1x");
        Map<Integer, Double> result = poly1.division(poly2.gTreeMap());
        String ok = String.valueOf(toStringDouble((TreeMap<Integer, Double>) result));
        assertEquals("0", ok);
    }

    @org.junit.jupiter.api.Test
    void division3() throws Exception {
        Polynomial poly1 = new Polynomial("5x^5 -4x^4 +3x^3 + x^2 -10");
        Polynomial poly2 = new Polynomial("x^2 - x + 2");
        Map<Integer, Double> result = poly1.division(poly2.gTreeMap());
        String ok = String.valueOf(toStringDouble((TreeMap<Integer, Double>) result));
        assertEquals("5.0x^3 + x^2 - 6.0x - 7.0", ok);
    }

    @org.junit.jupiter.api.Test
    void division4() throws Exception {
        Polynomial poly1 = new Polynomial("32x^10 - 23x^5 + 98x^4");
        Polynomial poly2 = new Polynomial("-7x^6 + 5x^5 + 23");
        Map<Integer, Double> result = poly1.division(poly2.gTreeMap());
        String ok = String.valueOf(toStringDouble((TreeMap<Integer, Double>) result));
        assertEquals(" - 4.571428571428571x^4 - 3.142857142857143x^3 - 2.142857142857143x^2 - 1.4285714285714286x - 1.0", ok);
    }

    @org.junit.jupiter.api.Test
    void differentiate() throws Exception {
        Polynomial poly1 = new Polynomial("53x");
        Map<Integer, Integer> result = poly1.Differentiate();
        Polynomial poly2 = new Polynomial(result);
        assertEquals("53", poly2.toStringInteger(poly2.gTreeMap()));
    }

    @org.junit.jupiter.api.Test
    void differentiate2() throws Exception {
        Polynomial poly1 = new Polynomial("5x^6 - 2x^9 + 1");
        Map<Integer, Integer> result = poly1.Differentiate();
        Polynomial poly2 = new Polynomial(result);
        assertEquals(" - 18x^8 + 30x^5", poly2.toStringInteger(poly2.gTreeMap()));
    }

    @org.junit.jupiter.api.Test
    void differentiate3() throws Exception {
        Polynomial poly1 = new Polynomial("2451");
        Map<Integer, Integer> result = poly1.Differentiate();
        Polynomial poly2 = new Polynomial(result);
        assertEquals("0", poly2.toStringInteger(poly2.gTreeMap()));
    }

    @org.junit.jupiter.api.Test
    void integrate() throws Exception {
        Polynomial poly1 = new Polynomial("4x");
        Map<Integer, Double> result = poly1.Integrate();
        String ok = String.valueOf(toStringDouble((TreeMap<Integer, Double>) result));
        assertEquals("2.0x^2", ok);
    }

    @org.junit.jupiter.api.Test
    void integrate2() throws Exception {
        Polynomial poly1 = new Polynomial("3x^5");
        Map<Integer, Double> result = poly1.Integrate();
        String ok = String.valueOf(toStringDouble((TreeMap<Integer, Double>) result));
        assertEquals("0.5x^6", ok);
    }

    @org.junit.jupiter.api.Test
    void integrate3() throws Exception {
        Polynomial poly1 = new Polynomial("23x^5");
        Map<Integer, Double> result = poly1.Integrate();
        String ok = String.valueOf(toStringDouble((TreeMap<Integer, Double>) result));
        assertEquals("3.8333333333333335x^6", ok);
    }

    @org.junit.jupiter.api.Test
    void integrate4() throws Exception {
        Polynomial poly1 = new Polynomial("5x^4 - 99x^10 - 4x^2");
        Map<Integer, Double> result = poly1.Integrate();
        String ok = String.valueOf(toStringDouble((TreeMap<Integer, Double>) result));
        assertEquals(" - 9.0x^11 + x^5 - 1.3333333333333333x^3", ok);
    }

    private StringBuilder toStringDouble(TreeMap<Integer, Double> resultMap) {

        StringBuilder result = new StringBuilder();
        boolean firstTerm = true;

        for (Map.Entry<Integer, Double> entry : resultMap.descendingMap().entrySet()) {
            double coefficient = entry.getValue();
            int exponent = entry.getKey();

            if (coefficient != 0) {
                if (coefficient > 0 && !firstTerm)
                    result.append(" + ");
                if (coefficient < 0)
                    result.append(" - ");
                if (Math.abs(coefficient) != 1 || exponent == 0)
                    result.append(Math.abs(coefficient));
                if (exponent != 0) {
                    result.append("x");
                    if (exponent > 1)
                        result.append("^").append(exponent);
                }

                firstTerm = false;
            }
        }
        if (result.isEmpty())
            result.append(0);

        return result;
    }
}