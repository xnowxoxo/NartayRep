package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalculatorView extends JFrame {
    private final JTextArea displayArea;
    private final JButton[] buttons;
    private final JPanel buttonPanel;
    private final JPanel mainPanel;

    public CalculatorView() {
        setTitle("MyCalculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 1000);
        setLocationRelativeTo(null);

        // Main panel with background color
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY);

        // The calculator's "screen"
        displayArea = new JTextArea();
        displayArea.setFont(new Font("Arial", Font.PLAIN, 24));
        displayArea.setLineWrap(true);
        displayArea.setWrapStyleWord(true);
        displayArea.setBackground(Color.WHITE);
        displayArea.setForeground(Color.BLACK);
        displayArea.setRows(3);
        displayArea.setEditable(false);  // Prevent direct editing
        mainPanel.add(displayArea, BorderLayout.NORTH);

        // Panel with buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        // Buttons created and modified using for loop
        buttons = new JButton[buttonLabels.length];
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 20));
            buttons[i].setForeground(Color.BLACK);
            buttons[i].setBackground(Color.LIGHT_GRAY);
            buttons[i].setOpaque(true);
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            buttonPanel.add(buttons[i]);
        }
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
    }

    public void addOperationListener(ActionListener listener) {
        for (JButton button : buttons) {
            button.addActionListener(listener); // Link each button to the action listener
        }
    }

    public void appendToDisplay(String text) {
        displayArea.append(text);  // Add text to the display area without a newline
    }

    public void clearDisplay() {
        displayArea.setText("");  // Clear the display area
    }

    public String getDisplayText() {
        return displayArea.getText();  // Get the current text in the display area
    }

    public void setResult(String result) {
        displayArea.setText(result);  // Set the result in the display area
    }
}
