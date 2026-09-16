package problemsolver;

import myapi.math.staticJava;

/**
 * Application driver verifying static utility calls, polymorphism, and abstract state.
 * 
 * @author Kylle
 */
public class ProblemSolverStatic {

    public static void main(String[] args) {
        // 1. Direct Static Method Call (No object creation required)
        int testInput = 5;
        double staticOutput = staticJava.getFactorial(testInput);
        System.out.println("Static Direct Call (" + testInput + "!): " + staticOutput);

        // 2. Interface Polymorphism using public static nested class
        staticJava.NumericSolver solver = new staticJava.FactorialSolver();
        double polymorphicOutput = solver.solve(10);
        System.out.println("Interface Polymorphic Call (10!): " + polymorphicOutput);

        // 3. Abstract Base State Verification via Type Casting
        if (solver instanceof staticJava.AbstractSequenceSolver) {
            staticJava.AbstractSequenceSolver seqSolver = (staticJava.AbstractSequenceSolver) solver;
            System.out.println("Active Solver Name: " + seqSolver.getSolverName());
        }
    }
}