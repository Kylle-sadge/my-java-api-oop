package problemsolver;

import java.util.Scanner;

public class ProblemSolver4 {

    // Custom checked exception: thrown when input is not a valid whole number
    static class NotAnIntegerException extends Exception {
        public NotAnIntegerException(String message) {
            super(message);
        }
    }

    // Validates that the input is a whole number (no decimal point allowed)
    static int parseStrictInt(String input) throws NotAnIntegerException {
        String trimmed = input.trim();

        if (trimmed.contains(".")) {
            throw new NotAnIntegerException("[" + trimmed + "] is a decimal, not an integer. "
                    + "Enter a whole number, e.g. 2");
        }

        try {
            return Integer.parseInt(trimmed);
        } catch (NumberFormatException nfe) {
            throw new NotAnIntegerException("[" + trimmed + "] is not a valid integer.");
        }
    }

    // Keeps asking until a valid integer is entered - an error never exits the program
    static int askForInt(Scanner sc, int position) {
        while (true) {
            System.out.print("Enter integer #" + position + " (e.g. 2): ");
            String input = sc.nextLine();

            try {
                return parseStrictInt(input);
            } catch (NotAnIntegerException e) {
                System.err.println("Error: " + e.getMessage() + ". Please try again.");
            }
        }
    }

    public static void main(String args[]) {
        int sum = 0;
        int counted = 0;

        // Part 1: command-line arguments (original behavior, now with custom exception)
        for (String arg : args) {
            try {
                sum += parseStrictInt(arg);
                counted++;
            } catch (NotAnIntegerException e) {
                System.err.println(e.getMessage() + " It will not be included in the sum.");
            }
        }

        // Part 2: interactive input with retry until valid
        Scanner sc = new Scanner(System.in);
        int count = 0;

        while (true) {
            System.out.print("How many additional integers do you want to add? ");
            try {
                count = parseStrictInt(sc.nextLine());
                if (count < 0) {
                    System.err.println("Error: please enter 0 or greater.");
                    continue;
                }
                break;
            } catch (NotAnIntegerException e) {
                System.err.println("Error: " + e.getMessage() + ". Please try again.");
            }
        }

        for (int i = 1; i <= count; i++) {
            sum += askForInt(sc, i);
            counted++;
        }

        System.out.println("Numbers included: " + counted);
        System.out.println("Sum = " + sum);
    }
}