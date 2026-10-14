package edu.course.lab03.normalization;

public final class MinMaxNormalization implements NormalizationStrategy {

    private double findMin(double[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException();
        }
        double min = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] < min) {
                min = values[i];
            }
        }
        return min;
    }
    private double findMax(double[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException();
        }
        double max = values[0];
        for (int i = 1; i < values.length; i++) {
            if (values[i] > max) {
                max = values[i];
            }
        }
        return max;
    }

    @Override
    public double[] normalize(double[] values) {
        if (values == null || values.length == 0) {
            throw new IllegalArgumentException();
        } double max = findMax(values); double min = findMin(values);
        if (max == min) {
            throw new IllegalArgumentException();
        }
        double[] result = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            result[i] = (values[i] - min) / (max - min);
        } return result;
    }
}
