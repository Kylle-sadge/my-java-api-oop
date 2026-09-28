/* This is a code that demonstrates how EXCEPTIONS, try-and-catch work.
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package problemsolver;

/**
 *
 * @author kylle
 */
public class ProblemSolver5 {

    // THROW: this method detects a problem and throws an exception
    static int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero!");
        }
        return a / b;
    }

    public static void main(String[] args) {
        System.out.println("Program started.");

        // TRY: code that might cause an exception goes here
        try {
            System.out.println("Trying 10 / 2...");
            System.out.println("Result: " + divide(10, 2));

            System.out.println("Trying 10 / 0...");
            System.out.println("Result: "   + divide(10, 0)); // exception is thrown here

            System.out.println("This line is skipped."); // never reached
        }
        // CATCH: handles the exception thrown inside the try block
        catch (ArithmeticException e) {
            System.out.println("Caught an error: " + e.getMessage());
        }
        // FINALLY: always runs, whether an exception happened or not
        finally {
            System.out.println("Finally block always runs.");
        }

        System.out.println("Program continues after the error.");
    }
}
