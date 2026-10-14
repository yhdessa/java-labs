package edu.course.lab03.normalization;

public final class FeatureProcessor {

    private final NormalizationStrategy strategy;
    
    public FeatureProcessor(NormalizationStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException();
        }
        this.strategy = strategy;
    }

    public double[] process(double[] vector) {
        if (vector == null || vector.length == 0) {
            throw new IllegalArgumentException();
        }
        return strategy.normalize(vector);
    }
}
