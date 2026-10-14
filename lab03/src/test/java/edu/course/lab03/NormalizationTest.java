package edu.course.lab03;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.course.lab03.normalization.FeatureProcessor;
import edu.course.lab03.normalization.MeanCenteringNormalization;
import edu.course.lab03.normalization.MinMaxNormalization;
import edu.course.lab03.normalization.NormalizationStrategy;

class NormalizationTest {

    @Test
    void normalizesTypicalVector() {
        NormalizationStrategy strategy = new MinMaxNormalization();
        double[] result = strategy.normalize(new double[]{10.9, 30.0, 11.0, -4.0, 4.0});
        assertArrayEquals(new double[]{0.4382352941, 1.0, 0.4411764706, 0.0, 0.2352941176}, result, 1e-9);
    }
    @Test
    void rejectsConstantVector() {
        NormalizationStrategy strategy = new MinMaxNormalization();
        assertThrows(IllegalArgumentException.class, () -> {strategy.normalize(new double[]{5.0, 5.0});});
    }
    @Test 
    void normalizesIdealVector() {
        NormalizationStrategy strategy = new MinMaxNormalization();
        double[] result = strategy.normalize(new double[]{0.0, 5.0, 10.0});
        assertArrayEquals(new double[]{0.0, 0.5, 1.0}, result, 1e-9);
    }
    @Test 
    void rejectsNullVector() {
        NormalizationStrategy strategy = new MinMaxNormalization();
        assertThrows(IllegalArgumentException.class, () -> strategy.normalize(null));
    }
    @Test 
    void rejectsEmptyVector() {
        NormalizationStrategy strategy = new MinMaxNormalization();
        assertThrows(IllegalArgumentException.class, () -> strategy.normalize(new double[]{}));
    }
    @Test
    void centersTypicalVector() {
        NormalizationStrategy strategy = new MeanCenteringNormalization();
        double[] result = strategy.normalize(new double[]{1.0, 3.0, 5.0});
        assertArrayEquals(new double[]{-2.0, 0.0, 2.0}, result, 1e-9);
    }
    @Test
    void centersVector() {
        NormalizationStrategy strategy = new MeanCenteringNormalization();
        double[] result = strategy.normalize(new double[]{2.0, 4.0});
        assertArrayEquals(new double[]{-1.0, 1.0}, result, 1e-9);
    }
    @Test
    void rejectsNullForMeanCentering() {
        NormalizationStrategy strategy = new MeanCenteringNormalization();
        assertThrows(IllegalArgumentException.class, () -> strategy.normalize(null));
    }
    @Test
    void rejectsEmptyForMeanCentering() {
        NormalizationStrategy strategy = new MeanCenteringNormalization();
        assertThrows(IllegalArgumentException.class, () -> strategy.normalize(new double[]{}));
    }
    @Test
    void doesNotModifyInputForMinMax() {
        NormalizationStrategy strategy = new MinMaxNormalization();
        double[] input = new double[]{10.9, 30.0, 11.0, -4.0, 4.0};
        double[] snapshot = input.clone();
        strategy.normalize(input);
        assertArrayEquals(snapshot, input, 1e-9);
    }
    @Test
    void doesNotModifyInputForMeanCentering() {
        NormalizationStrategy strategy = new MeanCenteringNormalization();
        double[] input = new double[]{1.0, 3.0, 5.0};
        double[] snapshot = input.clone();
        strategy.normalize(input);
        assertArrayEquals(snapshot, input, 1e-9);
    }
    @Test
    void processesVectorThroughStrategy() {
        FeatureProcessor processor = new FeatureProcessor(new MinMaxNormalization());
        double[] result = processor.process(new double[]{10.9, 30.0, 11.0, -4.0, 4.0});
        assertArrayEquals(new double[]{0.4382352941, 1.0, 0.4411764706, 0.0, 0.2352941176}, result, 1e-9);
    }
    @Test
    void swapsStrategiesWithoutChangingProcessor() {
        double[] input = new double[]{10.9, 30.0, 11.0, -4.0, 4.0};
        double[] viaMinMax = new FeatureProcessor(new MinMaxNormalization()).process(input.clone());
        double[] viaMean = new FeatureProcessor(new MeanCenteringNormalization()).process(input.clone());
        assertArrayEquals(new double[]{0.4382352941, 1.0, 0.4411764706, 0.0, 0.2352941176}, viaMinMax, 1e-9);
        assertArrayEquals(new double[]{0.52, 19.62, 0.62, -14.38, -6.38}, viaMean, 1e-9);
    }
    @Test
    void rejectsNullStrategy() {
        assertThrows(IllegalArgumentException.class, () -> new FeatureProcessor(null));
    }
    @Test
    void rejectsNullVectorInProcessor() {
        FeatureProcessor processor = new FeatureProcessor(new MinMaxNormalization());
        assertThrows(IllegalArgumentException.class, () -> processor.process(null));
    }
}
