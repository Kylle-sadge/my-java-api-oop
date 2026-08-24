package myapi.math;

import java.util.Arrays;

public class Statistics {

    public static double mean(double[] numbers) {
        if (numbers == null || numbers.length == 0) return 0;
        double sum = 0;
        for (double num : numbers) sum += num;
        return sum / numbers.length;
    }

    public static double median(double[] numbers) {
        if (numbers == null || numbers.length == 0) return 0;
        double[] sorted = numbers.clone();
        Arrays.sort(sorted);
        int middle = sorted.length / 2;
        if (sorted.length % 2 == 0) {
            return (sorted[middle - 1] + sorted[middle]) / 2.0;
        } else {
            return sorted[middle];
        }
    }
}