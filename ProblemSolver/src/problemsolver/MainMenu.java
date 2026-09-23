package problemsolver;

import javax.swing.*;
import java.awt.*;

/**
 * Main menu with two buttons.
 * Left button -> opens CalculatorFrame (the hand-coded calculator).
 * Right button -> opens MessageFrame (shows "RENE BATERBONIA").
 *
 * @author kylle
 */
public class MainMenu extends JFrame {

    public MainMenu() {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JButton leftButton = new JButton("Calculator");
        JButton rightButton = new JButton("Message");

        leftButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        rightButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        leftButton.addActionListener(evt -> openCalculator());
        rightButton.addActionListener(evt -> openMessage());

        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(leftButton);
        panel.add(rightButton);

        add(panel);

        setSize(320, 150);
        setLocationRelativeTo(null); // center on screen
    }

    private void openCalculator() {
        CalculatorFrame calculatorFrame = new CalculatorFrame();
        calculatorFrame.setVisible(true);
    }

    private void openMessage() {
        MessageFrame messageFrame = new MessageFrame();
        messageFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu menu = new MainMenu();
            menu.setVisible(true);
        });
    }
}