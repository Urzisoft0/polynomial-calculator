import java.beans.IndexedPropertyChangeEvent;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.*;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Interface::new);
        String x = "4x - 2";
        x = x.replaceAll("\\s+", "");
        System.out.println(x);

    }
}

