import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

interface Copy {
    public Map<Integer, Integer> DeepCopy(Map<Integer, Integer> original);
}

public class Polynomial implements Copy {
    TreeMap<Integer, Integer> polyMap = new TreeMap<>();

    public Polynomial(String poly) {
        ParsePolynomial parser = new ParsePolynomial(poly);
        polyMap = parser.Parse();
    }

    public void printPoly() {
        for (Map.Entry<Integer, Integer> var: polyMap.entrySet())
            System.out.println(var);
    }

    public Map<Integer, Integer> DeepCopy(Map<Integer, Integer> original) {
        Map<Integer, Integer> copy = new TreeMap<>();
        for (Map.Entry<Integer, Integer> entry : original.entrySet()) 
            copy.put(entry.getKey(), entry.getValue());
        return copy;
    }

    public Map<Integer, Integer> Addition1(Map<Integer, Integer> polyMap2) {
        Map<Integer, Integer> res = new TreeMap<>();
        Map<Integer, Integer> polyMapAux2 = new TreeMap<>();

        polyMapAux2 = DeepCopy(polyMap2);

        for (Map.Entry<Integer, Integer> var : polyMap.entrySet()) {
            int var_exponent = var.getKey();

            if (polyMapAux2.get(var_exponent) != null) { 
                int sum = var.getValue() + polyMapAux2.get(var_exponent);

                res.put(var_exponent, sum);
                polyMapAux2.remove(var_exponent);
            }
            else 
                res.put(var_exponent, var.getValue());
        }
        res.putAll(polyMapAux2);

        return res;
    }

    public Map<Integer, Integer> Substraction(Map<Integer, Integer> polyMap2) {
        Map<Integer, Integer> res = new TreeMap<>();
        Map<Integer, Integer> polyMapAux2 = new TreeMap<>();

        polyMapAux2 = DeepCopy(polyMap2);

        for (Map.Entry<Integer, Integer> var : polyMap.entrySet()) {
            int var_exponent = var.getKey();

            if (polyMapAux2.get(var_exponent) != null) {  
                int sub = polyMap.get(var_exponent) - polyMapAux2.get(var_exponent);
                if (sub != 0) {
                    res.put(var_exponent, sub);
                    polyMapAux2.remove(var_exponent); 
                }
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
                int var_exponent = var.getKey() + var2.getKey();

                int var_coefficient = var.getValue() * var2.getValue();
                if (!res.containsKey(var_exponent)) 
                    res.put(var_exponent, var_coefficient); 
                else 
                    res.put(var_exponent, res.get(var_exponent) + var_coefficient);   
            }
        }

        return res;
    }

    public Map<Integer, Integer> division(TreeMap<Integer, Integer> polyMap2) {
        Map<Integer, Integer> res = new TreeMap<>();
        Map<Integer, Integer> polyMapAux = new TreeMap<>();
        polyMapAux = DeepCopy(this.polyMap);

        while (!this.polyMap.isEmpty() && polyMap2.lastKey() <= polyMap.lastKey()) {
            int high_key1 = polyMap.lastKey(), high_key2 = polyMap2.lastKey();

            int var_expopent = high_key1 - high_key2;
            int var_coefficient = this.polyMap.get(high_key1) / polyMap2.get(high_key2);
            
            res.put(var_expopent, var_coefficient);

            TreeMap<Integer, Integer> toSub = new TreeMap<>();
            for (Map.Entry<Integer, Integer> entry : polyMap2.entrySet()) {
                int aux_exponent = entry.getKey() + var_expopent;
                int aux_coefficient = entry.getValue() * var_coefficient;
                toSub.put(aux_exponent, aux_coefficient);
            }

            for (Map.Entry<Integer, Integer> entry : toSub.entrySet()) {
                int aux_exponent = entry.getKey();
                int aux_coefficient = entry.getValue();

                if (polyMap.containsKey(aux_exponent)) {
                    int sub_coefficient = polyMap.get(aux_exponent) - aux_coefficient;
                    if (sub_coefficient != 0)
                        polyMap.put(aux_exponent, sub_coefficient);
                    else
                        polyMap.remove(aux_exponent);
                } else
                    polyMap.put(aux_exponent, -aux_coefficient);
            }
        }

        return res;
    }

    public Map<Integer, Integer> Differentiate() {
        Map<Integer, Integer> res = new TreeMap<>();
    
        for (Map.Entry<Integer, Integer> entry: polyMap.entrySet()) {
            int exponent = entry.getKey();
            int coefficient = entry.getValue();
            if (exponent != 0)
                res.put(exponent - 1, exponent * coefficient);
        }

        return res;
    }

    public Map<Integer, Double> Integrate() {
        Map<Integer, Double> res = new TreeMap<>();

        for (Map.Entry<Integer, Integer> entry: polyMap.entrySet())
            res.put(entry.getKey() + 1, (double)entry.getValue() / (entry.getKey() + 1));

        return res;
        
    }

    @Override
    public String toString() {
        String res = "";
        for (Map.Entry<Integer, Integer> var : polyMap.entrySet()) {
            if (var.getValue() > 0) {
                if (var.getValue() != 1)
                    res = res + "+" + var.getValue() + "x^" + var.getKey() + " ";
                else
                    res = res + "+" + var.getValue() + " ";
            }
                
            else if (var.getValue() < 0) {
                if (var.getValue() != -1)
                    res = res + var.getValue() + "x^" + var.getKey() + " ";
                else
                    res = res + "x^" + var.getKey() + " ";
            }
                
        }    
        return res;
    }

    public TreeMap<Integer, Integer> gTreeMap() {
        return polyMap;
    }
}
