package edu.course.lab03;

public final class FeatureProcessor {

    private final NormalizationStrategy strategy;

    public FeatureProcessor(NormalizationStrategy strategy) {
        // TODO: проверить strategy на null
        this.strategy = strategy;
    }

    public double[] process(double[] vector) {
        throw new UnsupportedOperationException("TODO");
    }
}
