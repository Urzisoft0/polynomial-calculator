import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

public class Interface extends JFrame {
    private JTextField poly1TextField;
    private JTextField poly2TextField;
    private JTextArea resultTextArea;
    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton differentiateButton;
    private JButton integrateButton;

    public Interface() {
        // Frame Title
        setTitle("Polynomial Calculator");

        // Text Fields for Polynomial Input
        poly1TextField = new JTextField(20);
        poly2TextField = new JTextField(20);

        // Text Area for Showing Result
        resultTextArea = new JTextArea(5, 20);
        resultTextArea.setEditable(false);

        // Operation Buttons
        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        differentiateButton = new JButton("d/dx (Poly 1)");
        integrateButton = new JButton("∫ (Poly 1)");

        // Panel for Inputs
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Poly 1:"));
        inputPanel.add(poly1TextField);
        inputPanel.add(new JLabel("Poly 2:"));
        inputPanel.add(poly2TextField);

        // Panel for Operations
        JPanel operationPanel = new JPanel();
        operationPanel.add(addButton);
        operationPanel.add(subtractButton);
        operationPanel.add(multiplyButton);
        operationPanel.add(divideButton);
        operationPanel.add(differentiateButton);
        operationPanel.add(integrateButton);

        // Panel for Result
        JPanel resultPanel = new JPanel();
        resultPanel.add(new JLabel("Result:"));
        resultPanel.add(new JScrollPane(resultTextArea));

        // Adding ActionListeners to Buttons
        addButton.addActionListener(e -> performOperation('+'));
        subtractButton.addActionListener(e -> performOperation('-'));
        multiplyButton.addActionListener(e -> performOperation('*'));
        divideButton.addActionListener(e -> performOperation('/'));
        differentiateButton.addActionListener(e -> performOperation('d'));
        integrateButton.addActionListener(e -> performOperation('i'));

        // Frame Layout
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(operationPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);

        // Frame Setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void performOperation(char operation) {
        Polynomial poly1 = new Polynomial(poly1TextField.getText());
        Polynomial poly2 = new Polynomial(poly2TextField.getText());
        Map<Integer, Integer> result;


        switch (operation) {
            case '+':
                result = poly1.Addition1(poly2.gTreeMap());
                break;
            case '-':
                result = poly1.Substraction(poly2.gTreeMap());
                break;
            case '*':
                result = poly1.Multiply(poly2.gTreeMap());
                break;
            case '/':
                result = poly1.division(poly2.gTreeMap());
                break;
            case 'd':
                result = poly1.Differentiate();
                break;
            case 'i':
                Map<Integer, Double> intResult = poly1.Integrate();
                displayIntegrationResult(intResult);
                return;
            default:
                resultTextArea.setText("Invalid operation");
                return;
        }
        displayResult(result);
    }

    private void displayResult(Map<Integer, Integer> resultMap) {
        // Initialize an empty StringBuilder for efficiently concatenating strings
        StringBuilder result = new StringBuilder();
        boolean isFirstTerm = true;
    
        // Iterate over the entry set of the resultMap
        for (Map.Entry<Integer, Integer> entry : resultMap.entrySet()) {
            int coefficient = entry.getValue();
            int exponent = entry.getKey();
    
            // Skip the term if the coefficient is 0
            if (coefficient == 0) {
                continue;
            }
    
            // Add '+' for positive coefficients, except for the first term
            if (coefficient > 0 && !isFirstTerm) {
                result.append(" + ");
            }
    
            // Directly append negative coefficients (the '-' sign will be included automatically)
            if (coefficient < 0) {
                result.append(" - ");
            }
    
            // Append the absolute value of the coefficient if it's not 1 or -1,
            // or if the exponent is 0 (which means the term is a constant)
            if (Math.abs(coefficient) != 1 || exponent == 0) {
                result.append(Math.abs(coefficient));
            } else if (coefficient == -1 && exponent != 0) {
                // If the coefficient is -1 and exponent is not 0, don't append anything,
                // as we already handle the sign before, and 1 is implicit for x^n
            }
    
            // Append the variable part if the exponent is not 0
            if (exponent > 0) {
                result.append("x");
                // Append the exponent if it's greater than 1
                if (exponent > 1) {
                    result.append("^").append(exponent);
                }
            }
    
            // After the first term, set isFirstTerm to false
            isFirstTerm = false;
        }
    
        // Check if the result is empty (all coefficients were 0), set to "0"
        if (result.length() == 0) {
            result.append("0");
        }
    
        // Set the text of the result text area to the built string
        resultTextArea.setText(result.toString());
    }
    

    private void displayIntegrationResult(Map<Integer, Double> resultMap) {
        // Initialize an empty StringBuilder for efficiently concatenating strings
        StringBuilder result = new StringBuilder();
        boolean isFirstTerm = true;
    
        // Iterate over the entry set of the resultMap
        for (Map.Entry<Integer, Double> entry : resultMap.entrySet()) {
            double coefficient = entry.getValue();
            int exponent = entry.getKey();
    
            // Skip the term if the coefficient is 0
            if (coefficient == 0) {
                continue;
            }
    
            // Add '+' for positive coefficients, except for the first term
            if (coefficient > 0 && !isFirstTerm) {
                result.append(" + ");
            }
    
            // Directly append negative coefficients (the '-' sign will be included automatically)
            if (coefficient < 0) {
                result.append(" - ");
            }
    
            // Append the absolute value of the coefficient if it's not 1 or -1,
            // or if the exponent is 0 (which means the term is a constant)
            if (Math.abs(coefficient) != 1 || exponent == 0) {
                result.append(Math.abs(coefficient));
            } else if (coefficient == -1 && exponent != 0) {
                // If the coefficient is -1 and exponent is not 0, don't append anything,
                // as we already handle the sign before, and 1 is implicit for x^n
            }
    
            // Append the variable part if the exponent is not 0
            if (exponent > 0) {
                result.append("x");
                // Append the exponent if it's greater than 1
                if (exponent > 1) {
                    result.append("^").append(exponent);
                }
            }
    
            // After the first term, set isFirstTerm to false
            isFirstTerm = false;
        }
    
        // Check if the result is empty (all coefficients were 0), set to "0"
        if (result.length() == 0) {
            result.append("0");
        }
    
        // Set the text of the result text area to the built string
        resultTextArea.setText(result.toString());
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Interface::new);
    }
}
