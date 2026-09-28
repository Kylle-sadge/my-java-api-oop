package problemsolver;

import java.util.Scanner;

public class ProblemSolver4 {

    // Custom checked exception: thrown when input is valid text but not a float format
    static class NotAFloatException extends Exception {
        public NotAFloatException(String message) {
            super(message);
        }
    }

    // Validates that the input is written as a float (must contain a decimal point)
    static double parseStrictFloat(String input) throws NotAFloatException {
        String trimmed = input.trim();

        if (!trimmed.contains(".")) {
            throw new NotAFloatException("[" + trimmed + "] is not in float format. Use a decimal point, e.g. "
                    + trimmed + ".0");
        }

        try {
            return Double.parseDouble(trimmed);
        } catch (NumberFormatException nfe) {
            throw new NotAFloatException("[" + trimmed + "] is not a valid number.");
        }
    }

    // Keeps asking until a valid float is entered - an error never exits the program
    static double askForFloat(Scanner sc, int position) {
        while (true) {
            System.out.print("Enter float #" + position + " (e.g. 2.0): ");
            String input = sc.nextLine();

            try {
                return parseStrictFloat(input);
            } catch (NotAFloatException e) {
                System.err.println("Error: " + e.getMessage() + " Please try again.");
            }
        }
    }

    public static void main(String args[]) {
        double sum = 0;
        int counted = 0;

        // Part 1: command-line arguments (original behavior, now float-strict)
        for (String arg : args) {
            try {
                sum += parseStrictFloat(arg);
                counted++;
            } catch (NotAFloatException e) {
                System.err.println(e.getMessage() + " It will not be included in the sum.");
            }
        }

        // Part 2: interactive input with retry until valid
        Scanner sc = new Scanner(System.in);
        int count = 0;

        while (true) {
            System.out.print("How many additional floats do you want to add? ");
            try {
                count = Integer.parseInt(sc.nextLine().trim());
                if (count < 0) {
                    throw new NumberFormatException("negative");
                }
                break;
            } catch (NumberFormatException nfe) {
                System.err.println("Error: please enter a whole number (0 or greater).");
            }
        }

        for (int i = 1; i <= count; i++) {
            sum += askForFloat(sc, i);
            counted++;
        }

        System.out.println("Numbers included: " + counted);
        System.out.println("Sum = " + sum);
    }
}