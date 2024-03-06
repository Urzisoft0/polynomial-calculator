import java.beans.IndexedPropertyChangeEvent;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) {
        Polynomial p1 = new Polynomial("3x^2 -5x -1");
        Polynomial p2 = new Polynomial("x");

        for (Map.Entry<Integer, Integer> var: p1.Addition1(p2.gTreeMap()).entrySet())
            System.out.println(var);

        

    }
}

