package problemsolver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Hand-written Swing calculator — no NetBeans GUI Builder used.
 * Includes a "Go To" menu bar for navigating to other frames.
 */
public class CalculatorFrame extends JFrame {

    private JTextField txtDisplay;

    // ---- calc state logic ----
    private double firstOperand = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    public CalculatorFrame() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // ---- menu bar ----
        setJMenuBar(buildMenuBar());

        // ---- display ----
        txtDisplay = new JTextField("0");
        txtDisplay.setEditable(false);
        txtDisplay.setHorizontalAlignment(JTextField.RIGHT);
        txtDisplay.setFont(new Font("Segoe UI", Font.PLAIN, 32));
        txtDisplay.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ---- 5x4 buttons ----
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 4, 4));

        String[] labels = {
            "C", "⌫", "%", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "±", "0", ".", "="
        };

        ActionListener handler = this::onButtonClick;

        for (String label : labels) {
            JButton btn = new JButton(label);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 20));
            btn.setFocusPainted(false);
            btn.addActionListener(handler);
            buttonPanel.add(btn);
        }

        // ---- layout ----
        setLayout(new BorderLayout(8, 8));
        add(txtDisplay, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        setSize(320, 420);
        setLocationRelativeTo(null); // center on screen
    }

    // ---- menu bar ----
    private JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu navigateMenu = new JMenu("Go To");

        JMenuItem mainMenuItem = new JMenuItem("Main Menu");
        JMenuItem messageItem = new JMenuItem("Message");
        JMenuItem closeItem = new JMenuItem("Close This Window");

        mainMenuItem.addActionListener(evt -> {
            new MainMenu1().setVisible(true);
            dispose();
        });

        messageItem.addActionListener(evt -> {
            new MessageFrame().setVisible(true);
            dispose();
        });

        closeItem.addActionListener(evt -> dispose());

        navigateMenu.add(mainMenuItem);
        navigateMenu.add(messageItem);
        navigateMenu.addSeparator();
        navigateMenu.add(closeItem);

        menuBar.add(navigateMenu);
        return menuBar;
    }

    // ---- single dispatcher, routes to the helper methods below ----
    private void onButtonClick(ActionEvent e) {
        String cmd = ((JButton) e.getSource()).getText();

        switch (cmd) {
            case "0": case "1": case "2": case "3": case "4":
            case "5": case "6": case "7": case "8": case "9":
                appendDigit(cmd);
                break;
            case "+": setOperator("+"); break;
            case "-": setOperator("-"); break;
            case "×": setOperator("×"); break;
            case "÷": setOperator("÷"); break;
            case "=": calculate(); break;
            case ".": appendDot(); break;
            case "C": clear(); break;
            case "⌫": backspace(); break;
            case "%": percent(); break;
            case "±": toggleSign(); break;
        }
    }

    // ---- helper methods ----

    private void appendDigit(String digit) {
        if (startNewNumber) {
            txtDisplay.setText(digit);
            startNewNumber = false;
        } else {
            if (txtDisplay.getText().equals("0")) {
                txtDisplay.setText(digit);
            } else {
                txtDisplay.setText(txtDisplay.getText() + digit);
            }
        }
    }

    private void appendDot() {
        if (startNewNumber) {
            txtDisplay.setText("0.");
            startNewNumber = false;
        } else if (!txtDisplay.getText().contains(".")) {
            txtDisplay.setText(txtDisplay.getText() + ".");
        }
    }

    private void setOperator(String op) {
        if (!operator.isEmpty() && !startNewNumber) {
            calculate(); // chain: 2+3+4 style
        }
        firstOperand = Double.parseDouble(txtDisplay.getText());
        operator = op;
        startNewNumber = true;
    }

    private void calculate() {
        if (operator.isEmpty()) return;

        double secondOperand = Double.parseDouble(txtDisplay.getText());
        double result = 0;

        switch (operator) {
            case "+": result = firstOperand + secondOperand; break;
            case "-": result = firstOperand - secondOperand; break;
            case "×": result = firstOperand * secondOperand; break;
            case "÷":
                if (secondOperand == 0) {
                    txtDisplay.setText("Error");
                    operator = "";
                    startNewNumber = true;
                    return;
                }
                result = firstOperand / secondOperand;
                break;
        }

        txtDisplay.setText(format(result));
        firstOperand = result;
        operator = "";
        startNewNumber = true;
    }

    private void clear() {
        txtDisplay.setText("0");
        firstOperand = 0;
        operator = "";
        startNewNumber = true;
    }

    private void backspace() {
        String current = txtDisplay.getText();
        if (current.length() > 1) {
            txtDisplay.setText(current.substring(0, current.length() - 1));
        } else {
            txtDisplay.setText("0");
            startNewNumber = true;
        }
    }

    private void percent() {
        double value = Double.parseDouble(txtDisplay.getText());
        txtDisplay.setText(format(value / 100));
        startNewNumber = true;
    }

    private void toggleSign() {
        double value = Double.parseDouble(txtDisplay.getText());
        txtDisplay.setText(format(-value));
    }

    private String format(double value) {
        if (value == (long) value) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }

    // ---- entry point ----
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorFrame frame = new CalculatorFrame();
            frame.setVisible(true);
        });
    }
}