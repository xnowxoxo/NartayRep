package Controller;

import Model.CalculatorModel;
import View.CalculatorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController implements ActionListener {
    private final CalculatorModel model;
    private final CalculatorView view;

    private double firstNumber = 0;
    private String operator = "";

    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
        this.view.addOperationListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        String buttonText = clickedButton.getText();

        switch (buttonText) {
            case "C":
                view.clearDisplay();
                firstNumber = 0;
                operator = "";
                break;
            case "=":
                computeResult();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                if (!operator.isEmpty()) {
                    view.setResult("Error: Invalid input");
                    break;
                }
                firstNumber = Double.parseDouble(view.getDisplayText().trim());
                operator = buttonText;
                view.appendToDisplay(" " + operator + " ");
                break;
            default: // Numbers
                view.appendToDisplay(buttonText);
                break;
        }
    }

    private void computeResult() {
        String[] parts = view.getDisplayText().split(" ");
        if (parts.length < 3) {
            view.setResult("Error: Invalid input");
            return;
        }

        try {
            double secondNumber = Double.parseDouble(parts[2]);
            String result = model.calculate(firstNumber, secondNumber, operator);
            // Format result
            String formattedResult = formatResult(firstNumber, operator, secondNumber, result);
            view.setResult(formattedResult);
            firstNumber = Double.parseDouble(result);  // Update firstNumber for continued operations
            operator = "";  // Reset operator for new calculation
        } catch (NumberFormatException ex) {
            view.setResult("Ошибка: деление на ноль");
        }
    }

    // Method to format result for display
    private String formatResult(double firstNumber, String operator, double secondNumber, String result) {
        String firstNumStr = isWholeNumber(firstNumber) ? String.valueOf((int) firstNumber) : String.valueOf(firstNumber);
        String secondNumStr = isWholeNumber(secondNumber) ? String.valueOf((int) secondNumber) : String.valueOf(secondNumber);
        String resultStr = isWholeNumber(Double.parseDouble(result)) ? String.valueOf((int) Double.parseDouble(result)) : result;

        return firstNumStr + " " + operator + " " + secondNumStr + " = " + resultStr;
    }

    // Helper method to check if a number is whole
    private boolean isWholeNumber(double num) {
        return num % 1 == 0;
    }
}
