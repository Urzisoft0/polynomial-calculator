import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.TreeMap;

public class Interface extends JFrame {
    private final JTextField poly1_input;
    private final JTextField poly2_input;
    private final JTextArea resultTextArea;

    public Interface() {
        setTitle("Polynomial Calculator");

        resultTextArea = new JTextArea(3, 33);
        resultTextArea.setEditable(false);

        poly1_input = new JTextField(25);
        poly2_input = new JTextField(25);

        JButton divisionB = new JButton("/ (Division)");
        JButton derivativeB = new JButton("d/dx (Poly 1)");
        JButton integrationB = new JButton("∫ (Poly 1)");
        JButton subtractB = new JButton("- (Subtraction)");
        JButton additionB = new JButton("+ (Addition)");
        JButton multiplicationB = new JButton("* (Multiplication)");

        JPanel input = new JPanel();
        input.add(new JLabel("P1(x):"));
        input.add(poly1_input);
        input.add(new JLabel("P2(x):"));
        input.add(poly2_input);

        JPanel operation = new JPanel();
        operation.add(additionB);
        operation.add(derivativeB);
        operation.add(integrationB);
        operation.add(multiplicationB);
        operation.add(subtractB);
        operation.add(divisionB);

        JPanel result = new JPanel();
        result.add(new JLabel("Result:"));
        result.add(new JScrollPane(resultTextArea));

        multiplicationB.addActionListener(e -> {
            try {
                performOperation('*');
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        divisionB.addActionListener(e -> {
            try {
                performOperation('/');
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        additionB.addActionListener(e -> {
            try {
                performOperation('+');
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        integrationB.addActionListener(e -> {
            try {
                performOperation('i');
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        derivativeB.addActionListener(e -> {
            try {
                performOperation('d');
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        subtractB.addActionListener(e -> {
            try {
                performOperation('-');
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        setLayout(new BorderLayout());
        add(input, BorderLayout.NORTH);
        add(operation, BorderLayout.CENTER);
        add(result, BorderLayout.SOUTH );

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void performOperation(char operation) {
        Map<Integer, Integer> result;
        Polynomial poly1, poly2;
        try {
            poly1 = new Polynomial(poly1_input.getText());
            poly2 = new Polynomial(poly2_input.getText());
        } catch (Exception g) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + g.getMessage(), "Error: Cannot Initialize either Poly1 or Poly2"
                    , JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            switch (operation) {
                case '+':
                    result = poly1.Addition1(poly2.gTreeMap());
                    toStringInteger((TreeMap<Integer, Integer>) result);
                    break;
                case '-':
                    result = poly1.Subtraction(poly2.gTreeMap());
                    toStringInteger((TreeMap<Integer, Integer>) result);
                    break;
                case '*':
                    result = poly1.Multiply(poly2.gTreeMap());
                    toStringInteger((TreeMap<Integer, Integer>) result);
                    break;
                case '/':
                    try {
                        Map<Integer, Double> result2 = poly1.division(poly2.gTreeMap());
                        toStringDouble((TreeMap<Integer, Double>) result2);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 'd':
                    result = poly1.Differentiate();
                    toStringInteger((TreeMap<Integer, Integer>) result);
                    break;
                case 'i':
                    Map<Integer, Double> res = poly1.Integrate();
                    toStringDouble((TreeMap<Integer, Double>) res);
                    break;
                default:
                    resultTextArea.setText("Invalid operation");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void toStringInteger(TreeMap<Integer, Integer> polyMap) {
        String result = "";
        boolean firstTerm = true;
    
        for (Map.Entry<Integer, Integer> var : polyMap.descendingMap().entrySet()) {
            int coefficient = var.getValue();
            int exponent = var.getKey();

            if (coefficient != 0) {
                if (coefficient > 0 && !firstTerm)
                    result = result + " + ";

                if (coefficient < 0)
                    result = result + " - ";

                if (Math.abs(coefficient) != 1 || exponent == 0)
                    result = result + Math.abs(coefficient);

                if (exponent != 0) {
                    result = result + "x";
                    if (exponent > 1)
                        result = result + "^" + exponent;
                }

                firstTerm = false;
            }

            if (result.isEmpty())
                result = result + 0;
            resultTextArea.setText(result);
        }
    }

    private void toStringDouble(TreeMap<Integer, Double> resultMap) {
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
        resultTextArea.setText(result.toString());
    }
}
