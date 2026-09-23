package problemsolver;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Notepad-style frame: a text area with Load, Save, and Close buttons,
 * plus a top menu bar for navigating to other frames.
 *
 * @author kylle
 */
public class MessageFrame extends JFrame {

    private JTextArea txtNotepad;

    public MessageFrame() {
        setTitle("Notepad");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(true);

        // ---- menu bar ----
        setJMenuBar(buildMenuBar());

        // ---- text area with scroll support ----
        txtNotepad = new JTextArea();
        txtNotepad.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtNotepad.setLineWrap(true);
        txtNotepad.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(txtNotepad);

        // ---- buttons ----
        JButton btnLoad = new JButton("Load");
        JButton btnSave = new JButton("Save");
        JButton btnClose = new JButton("Close");

        btnLoad.addActionListener(evt -> onLoad());
        btnSave.addActionListener(evt -> onSave());
        btnClose.addActionListener(evt -> dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(btnLoad);
        buttonPanel.add(btnSave);
        buttonPanel.add(btnClose);

        // ---- layout ----
        setLayout(new BorderLayout(8, 8));
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        setSize(420, 320);
        setLocationRelativeTo(null); // center on screen
    }

    private JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu navigateMenu = new JMenu("Go To");

        JMenuItem mainMenuItem = new JMenuItem("Main Menu");
        JMenuItem calculatorItem = new JMenuItem("Calculator");
        JMenuItem closeItem = new JMenuItem("Close This Window");

        mainMenuItem.addActionListener(evt -> {
            new MainMenu1().setVisible(true);
            dispose(); // close this window when navigating away
        });

        calculatorItem.addActionListener(evt -> {
            new CalcFrame().setVisible(true);
            dispose();
        });

        closeItem.addActionListener(evt -> dispose());

        navigateMenu.add(mainMenuItem);
        navigateMenu.add(calculatorItem);
        navigateMenu.addSeparator();
        navigateMenu.add(closeItem);

        menuBar.add(navigateMenu);
        return menuBar;
    }

    private void onLoad() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a file to load");
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileChooser.getSelectedFile();
            StringBuilder content = new StringBuilder();

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                boolean firstLine = true;
                while ((line = reader.readLine()) != null) {
                    if (!firstLine) {
                        content.append("\n");
                    }
                    content.append(line);
                    firstLine = false;
                }
                txtNotepad.setText(content.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error loading file: " + e.getMessage(),
                        "Load Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void onSave() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save as");
        fileChooser.setSelectedFile(new java.io.File("notes.txt"));
        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileChooser.getSelectedFile();
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(txtNotepad.getText());
                JOptionPane.showMessageDialog(this, "Saved to " + file.getName());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage(),
                        "Save Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}