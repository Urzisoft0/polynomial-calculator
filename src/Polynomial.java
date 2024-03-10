import java.util.Map;
import java.util.TreeMap;

public class Polynomial{
    TreeMap<Integer, Integer> polyMap;

    public Polynomial(String poly) throws Exception {
        ParsePolynomial parser = new ParsePolynomial(poly);
        polyMap = parser.Parse();
    }

    public Polynomial(Map<Integer, Integer> polyMap) {
        this.polyMap = (TreeMap<Integer, Integer>) polyMap;
    }

    private Map<Integer, Integer> DeepCopy(Map<Integer, Integer> original) {
        Map<Integer, Integer> copy = new TreeMap<>();
        for (Map.Entry<Integer, Integer> entry : original.entrySet()) 
            copy.put(entry.getKey(), entry.getValue());
        return copy;
    }

    public Map<Integer, Integer> Addition1(Map<Integer, Integer> polyMap2) {
        Map<Integer, Integer> res = new TreeMap<>();
        Map<Integer, Integer> polyMapAux2;

        polyMapAux2 = DeepCopy(polyMap2);

        for (Map.Entry<Integer, Integer> var : polyMap.entrySet()) {
            Integer var_exponent = var.getKey();

            if (polyMapAux2.get(var_exponent) != null) { 
                Integer sum = var.getValue() + polyMapAux2.get(var_exponent);

                if (sum != 0) {
                    res.put(var_exponent, sum);
                    
                }
                polyMapAux2.remove(var_exponent);
                
            }
            else 
                res.put(var_exponent, var.getValue());
        }
        res.putAll(polyMapAux2);

        return res;
    }

    public Map<Integer, Integer> Subtraction(Map<Integer, Integer> polyMap2) {
        Map<Integer, Integer> res = new TreeMap<>();
        Map<Integer, Integer> polyMapAux2;

        polyMapAux2 = DeepCopy(polyMap2);

        for (Map.Entry<Integer, Integer> var : polyMap.entrySet()) {
            Integer var_exponent = var.getKey();

            if (polyMapAux2.get(var_exponent) != null) {  
                Integer sub = polyMap.get(var_exponent) - polyMapAux2.get(var_exponent);
                if (sub != 0) {
                    res.put(var_exponent, sub);
                    
                }
                polyMapAux2.remove(var_exponent); 
            }
            else
                res.put(var_exponent, var.getValue());
        }
        polyMapAux2.replaceAll((exponent, coefficient) -> coefficient * (-1));
        res.putAll(polyMapAux2);

        return res;
    }

    public Map<Integer, Integer> Multiply(Map<Integer, Integer> polyMap2) {
        Map<Integer, Integer> res = new TreeMap<>();
        
        for (Map.Entry<Integer, Integer> var : polyMap.entrySet()) {
            for (Map.Entry<Integer, Integer> var2 : polyMap2.entrySet()) {  
                Integer var_exponent = var.getKey() + var2.getKey();

                Integer var_coefficient = var.getValue() * var2.getValue();
                if (!res.containsKey(var_exponent)) 
                    res.put(var_exponent, var_coefficient); 
                else 
                    res.put(var_exponent, res.get(var_exponent) + var_coefficient);   
            }
        }

        return res;
    }
    public Map<Integer, Double> division(TreeMap<Integer, Integer> polyMap2) throws Exception {
        Polynomial test = new Polynomial(polyMap2);
        if (test.toStringInteger(test.gTreeMap()).compareTo("0") == 0)
            throw new Exception("Division by 0");

        Map<Integer, Double> res = new TreeMap<>();

        while (!this.polyMap.isEmpty() && !polyMap2.isEmpty() && polyMap2.lastKey() <= polyMap.lastKey()) {
            Integer high_key1 = polyMap.lastKey(), high_key2 = polyMap2.lastKey();

            Integer var_exponent = high_key1 - high_key2;
            Double var_coefficient = (double) this.polyMap.get(high_key1) / polyMap2.get(high_key2);

            TreeMap<Integer, Double> toSub = new TreeMap<>();
            for (Map.Entry<Integer, Integer> entry : polyMap2.entrySet()) {
                Integer aux_exponent = entry.getKey() + var_exponent;
                Double aux_coefficient = entry.getValue() * var_coefficient;
                toSub.put(aux_exponent, aux_coefficient);
            }

            for (Map.Entry<Integer, Double> entry : toSub.entrySet()) {
                Integer aux_exponent = entry.getKey();
                Double aux_coefficient = entry.getValue();

                if (polyMap.containsKey(aux_exponent)) {
                    Double sub_coefficient = polyMap.get(aux_exponent) - aux_coefficient;
                    if (sub_coefficient != 0)
                        polyMap.put(aux_exponent, sub_coefficient.intValue());
                    else
                        polyMap.remove(aux_exponent);
                } else
                    polyMap.put(aux_exponent, -aux_coefficient.intValue());
            }

            if (var_coefficient != 0.0) {
                res.put(var_exponent, var_coefficient);
            }
        }

        return res;
    }



    public Map<Integer, Integer> Differentiate() {
        Map<Integer, Integer> res = new TreeMap<>();
    
        for (Map.Entry<Integer, Integer> entry: polyMap.entrySet()) {
            Integer exponent = entry.getKey();
            Integer coefficient = entry.getValue();
            if (exponent != 0)
                res.put(exponent - 1, exponent * coefficient);
            else
                res.put(exponent, 0);
        }

        return res;
    }

    public Map<Integer, Double> Integrate() {
        Map<Integer, Double> res = new TreeMap<>();

        for (Map.Entry<Integer, Integer> entry: polyMap.entrySet())
            res.put(entry.getKey() + 1, (double)entry.getValue() / (entry.getKey() + 1));

        return res;
        
    }

    public TreeMap<Integer, Integer> gTreeMap() {
        return this.polyMap;
    }

    public String toStringInteger(TreeMap<Integer, Integer> polyMap) {
        StringBuilder result = new StringBuilder();
        boolean firstTerm = true;

        for (Map.Entry<Integer, Integer> var : polyMap.descendingMap().entrySet()) {
            int coefficient = var.getValue();
            int exponent = var.getKey();

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

            if (result.isEmpty())
                result.append(0);
        }
        return result.toString();
    }

}
