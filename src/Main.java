import java.beans.IndexedPropertyChangeEvent;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.*;

import javax.swing.SwingUtilities;

public class Main {
    int x;
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(Interface::new);
        Polynomial poly1 = new Polynomial("x^2 + 1");
        Polynomial poly2 = new Polynomial("x^3 - 2x^2 - 1");

        Map<Integer, Integer> result = poly1.Addition1(poly2.gTreeMap());
        Polynomial poly3 = new Polynomial(result);
        System.out.println(poly3.toStringInteger(poly3.gTreeMap()));
        //System.out.println(poly1.Addition1(poly2.gTreeMap()));
    }
}

