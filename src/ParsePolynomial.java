import java.util.Map;
import java.util.TreeMap;
import java.util.regex.*;
public class ParsePolynomial {
    
    private String polynomial;

    public ParsePolynomial(String polynomial) {
        this.polynomial = polynomial;
    }

    public TreeMap<Integer, Integer> Parse() {
        TreeMap<Integer, Integer> aa = new TreeMap<>();
        this.polynomial = this.polynomial.replaceAll("\\s+", "");
        String regex = "([+-]?\\d+)?x\\^(\\d+)|([+-]?\\d*)x(\\^\\d+)?|([+-]?\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.polynomial);

        while (matcher.find()) {
            String regex2 = "\\d+";
            Pattern pattern2 = Pattern.compile(regex2);
            Matcher matcher2 = pattern2.matcher(matcher.group(0));
            Boolean x = false, neg = false, exp = false;
            if (matcher.group(0).contains("-"))
                neg = true;

            if (matcher.group(0).contains("^")) {
                String[] array = matcher.group(0).split("x\\^");
                String regex3 = "[+-]?\\d+";
                Pattern pattern3 = Pattern.compile(regex2);
                Matcher matcher3 = pattern2.matcher(array[0]);
                Integer find;
                if (matcher3.find()) {
                    find = neg == true ? Integer.parseInt(matcher3.group(0)) * (-1) : Integer.parseInt(matcher3.group(0));
                } else {
                    find = neg == true ? -1 : 1;
                }

                aa.put(Integer.parseInt(array[1]), find);
            } else {
                String[] array = matcher.group(0).split("x");
                Integer find;
                if (array.length == 0)
                    aa.put(1, 1);
                else {

                
                String regex3 = "[+-]?\\d+";
                Pattern pattern3 = Pattern.compile(regex2);
                Matcher matcher3 = pattern2.matcher(array[0]);

                

                if (matcher3.find() && array.length > 0) {
                    find = neg == true ? Integer.parseInt(matcher3.group(0)) * (-1) : Integer.parseInt(matcher3.group(0));                        
                } else {
                    find = neg == true ? -1 : 1;    
                }
                
                if (matcher.group(0).contains("x"))
                    aa.put(1, find);
                else
                    aa.put(0, find);
                }    
            }
        }

        // for (Map.Entry<Integer, Integer> ok : aa.entrySet()) {
        //     System.out.println("coef: " + ok.getValue() + ":exp: " + ok.getKey());
        // }

        return aa;

    }
}