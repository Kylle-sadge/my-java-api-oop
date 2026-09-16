package myapi.math;

/**
 * Single-file API container holding numerical solver contracts, 
 * abstract sequence structures, and static pre-computed lookup utilities.
 * 
 * @author Kylle
 */
public class staticJava {

    /**
     * Contract for single-variable mathematical operations.
     */
    public interface NumericSolver {
        /**
         * Evaluates a mathematical function for a given input.
         *
         * @param input the input numerical value
         * @return the calculated numerical result
         * @throws IllegalArgumentException if the input is outside valid domains
         */
        double solve(double input);
    }

    /**
     * Abstract base enforcing common metadata and operational standards.
     */
    public static abstract class AbstractSequenceSolver implements NumericSolver {
        private final String solverName;

        /**
         * Constructs a sequence solver instance with a non-null identifier.
         *
         * @param solverName descriptive identifier for the solver implementation
         * @throws IllegalArgumentException if solverName is null or blank
         */
        public AbstractSequenceSolver(String solverName) {
            if (solverName == null || solverName.isBlank()) {
                throw new IllegalArgumentException("Solver name cannot be null or blank.");
            }
            this.solverName = solverName;
        }

        /**
         * Retrieves the solver name identifier.
         *
         * @return the configured solver name
         */
        public String getSolverName() {
            return solverName;
        }

        @Override
        public abstract double solve(double input);
    }

    /**
     * Concrete solver utilizing pre-computed lookup tables for O(1) factorial retrieval.
     */
    public static class FactorialSolver extends AbstractSequenceSolver {
        private static final int MAX_CACHE_SIZE = 171;
        private static final double[] FACTORIAL_CACHE = new double[MAX_CACHE_SIZE];

        // Static Initialization Block: Pre-computes factorial array when loaded into memory
        static {
            FACTORIAL_CACHE[0] = 1.0;
            for (int i = 1; i < MAX_CACHE_SIZE; i++) {
                FACTORIAL_CACHE[i] = FACTORIAL_CACHE[i - 1] * i;
            }
        }

        /**
         * Instantiates the pre-computed factorial solver.
         */
        public FactorialSolver() {
            super("Static Pre-computed Factorial Solver");
        }

        /**
         * Fetches pre-computed factorials directly in O(1) time complexity.
         *
         * @param n non-negative integer parameter (0 <= n < 171)
         * @return pre-computed factorial double value
         * @throws IllegalArgumentException if n is negative or exceeds 170
         */
        public static double getFactorial(int n) {
            if (n < 0 || n >= MAX_CACHE_SIZE) {
                throw new IllegalArgumentException("Input 'n' must be within bounds [0, " + (MAX_CACHE_SIZE - 1) + "].");
            }
            return FACTORIAL_CACHE[n];
        }

        @Override
        public double solve(double input) {
            return getFactorial((int) input);
        }
    }

    /**
     * Global static helper method for direct access without instantiating sub-classes.
     *
     * @param n integer value
     * @return evaluated factorial
     */
    public static double getFactorial(int n) {
        return FactorialSolver.getFactorial(n);
    }
}