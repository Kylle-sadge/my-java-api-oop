package myapi.math;

public class Quadratic {

    /**
     * Solves ax^2 + bx + c = 0 and returns an array of real roots.
     * Returns 2 values for distinct real roots, 1 value for equal roots, 
     * or an empty array if roots are complex.
     */
    public static double[] solve(double a, double b, double c) {
        if (a == 0) {
            throw new IllegalArgumentException("Coefficient 'a' cannot be zero in a quadratic equation.");
        }

        double discriminant = (b * b) - (4 * a * c);

        if (discriminant > 0) {
            double r1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double r2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            return new double[]{r1, r2};
        } else if (discriminant == 0) {
            double r1 = -b / (2 * a);
            return new double[]{r1};
        } else {
            return new double[0]; // No real roots
        }
    }

    /**
     * Solves ax^2 + bx + c = 0 and returns a formatted result String,
     * including complex number representations if discriminant < 0.
     */
    public static String solveToString(double a, double b, double c) {
        if (a == 0) {
            return "Not a quadratic equation (a = 0).";
        }

        double discriminant = (b * b) - (4 * a * c);

        if (discriminant > 0) {
            double r1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double r2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            return "Two real roots: x1 = " + r1 + ", x2 = " + r2;
        } else if (discriminant == 0) {
            double r1 = -b / (2 * a);
            return "One real root: x = " + r1;
        } else {
            double realPart = -b / (2 * a);
            double imagPart = Math.sqrt(-discriminant) / (2 * a);
            return "Complex roots: x1 = " + realPart + " + " + imagPart + "i, x2 = " + realPart + " - " + imagPart + "i";
        }
    }
}