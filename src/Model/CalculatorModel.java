package Model;

public class CalculatorModel {
    public String calculate(double num1, double num2, String operator) {
        switch (operator) {
            case "-":
                return Double.toString(subtract(num1, num2));
            case "+":
                return Double.toString(add(num1, num2));
            case "/":
                try {
                    double result = divide(num1, num2);
                    return Double.toString(result);
                } catch (ArithmeticException e) {
                    return "Error: " + e.getMessage();
                }
            case "*":
                return Double.toString(multiply(num1, num2));
            default:
                return "Error: operator not found";
        }
    }

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }
}
