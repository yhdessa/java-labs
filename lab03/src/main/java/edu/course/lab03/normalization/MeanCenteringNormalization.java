package edu.course.lab03.normalization;

public final class MeanCenteringNormalization implements NormalizationStrategy {

    private static double average(double[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException();
        }
        double sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }
        return sum / values.length;
    }
    @Override
    public double[] normalize(double[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException();
        }
        double avgValue = average(values);
        double[] result = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = values[i] - avgValue;
        }
        return result;
    }
}