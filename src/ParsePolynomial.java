import java.math.BigInteger;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.*;
public class ParsePolynomial {
    
    private String polynomial;

    public ParsePolynomial(String polynomial) {
        this.polynomial = polynomial;
    }

    public TreeMap<Integer, Integer> Parse() throws Exception {
        TreeMap<Integer, Integer> aa = new TreeMap<>();
        this.polynomial = this.polynomial.replaceAll("\\s+", "");
        String regex = "([+-]?\\d+)?x\\^(\\d+)|([+-]?\\d*)x(\\^\\d+)?|([+-]?\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.polynomial);
        BigInteger maxIntValue = BigInteger.valueOf(Integer.MAX_VALUE);
        BigInteger minIntValue = BigInteger.valueOf(Integer.MIN_VALUE);

        while (matcher.find()) {
            String regex2 = "\\d+";
            Pattern pattern2 = Pattern.compile(regex2);
            boolean neg = matcher.group(0).contains("-");

            if (matcher.group(0).contains("^")) {
                String[] array = matcher.group(0).split("x\\^");
                Matcher matcher3 = pattern2.matcher(array[0]);
                Integer find;

                if (matcher3.find()) {
                    BigInteger test = new BigInteger(matcher3.group(0));
                    if (test.compareTo(maxIntValue) > 0 || test.compareTo(minIntValue) < 0)
                        throw new Exception("Coefficient outside of INTEGER boundaries");
                    find = neg ? Integer.parseInt(matcher3.group(0)) * (-1) : Integer.parseInt(matcher3.group(0));
                } else {
                    find = neg ? -1 : 1;
                }


                BigInteger test = new BigInteger(array[1]);
                if (test.compareTo(maxIntValue) > 0 || test.compareTo(minIntValue) < 0)
                    throw new Exception("Exponent outside of INTEGER boundaries");
                aa.put(Integer.parseInt(array[1]), find);
            } else {
                String[] array = matcher.group(0).split("x");
                Integer find;
                if (array.length == 0)
                    aa.put(1, 1);
                else {
                    Matcher matcher3 = pattern2.matcher(array[0]);

                if (matcher3.find()) {
                    BigInteger test = new BigInteger(matcher3.group(0));
                    if (test.compareTo(maxIntValue) > 0 || test.compareTo(minIntValue) < 0)
                        throw new Exception("Coefficient outside of INTEGER boundaries");
                    find = neg ? Integer.parseInt(matcher3.group(0)) * (-1) : Integer.parseInt(matcher3.group(0));
                } else {
                    find = neg ? -1 : 1;
                }
                
                if (matcher.group(0).contains("x"))
                    aa.put(1, find);
                else
                    aa.put(0, find);
                }    
            }
        }

        return aa;
    }
}