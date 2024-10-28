import View.CalculatorView;
import Model.CalculatorModel;
import Controller.CalculatorController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create model, view, and controller
        CalculatorModel model = new CalculatorModel();
        CalculatorView view = new CalculatorView();
        new CalculatorController(model, view);

        view.setVisible(true);  // Show the calculator
    }
}
