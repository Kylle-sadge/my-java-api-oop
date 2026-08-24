package myapi.math;

public class Arithmetic {

    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is undefined.");
        }
        return a / b;
    }

    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public static double squareRoot(double number) {
        if (number < 0) {
            throw new ArithmeticException("Cannot calculate real square root of a negative number.");
        }
        return Math.sqrt(number);
    }

    public static double modulo(double a, double b) {
        return a % b;
    }

    public static double abs(double number) {
        return Math.abs(number);
    }
    public static double nthRoot(double base, int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Root degree 'n' must be a positive integer greater than zero.");
        }
        
        // Check your rule: Even root of a negative number is not real
        if (n % 2 == 0 && base < 0) {
            throw new ArithmeticException("Even root of a negative number is not a real number.");
        }
        
        // Math.pow with fractional exponent calculates roots (e.g., base^(1/n))
        return Math.pow(base, 1.0 / n);
    }
}