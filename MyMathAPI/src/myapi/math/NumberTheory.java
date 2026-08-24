package myapi.math;

public class NumberTheory {

    public static boolean isPrime(long n) {
        if (n <= 1) return false;
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static long gcd(long a, long b) {
        return b == 0 ? Math.abs(a) : gcd(b, a % b);
    }

    public static long lcm(long a, long b) {
        if (a == 0 || b == 0) return 0;
        return Math.abs(a * b) / gcd(a, b);
    }

    public static long factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Factorial undefined for negative numbers.");
        long result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }
}