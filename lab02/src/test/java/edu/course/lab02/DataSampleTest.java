package edu.course.lab02;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DataSampleTest {

    @Test
    void createsCorrectDataSample() {
        DataSample sample = new DataSample("s1", "cat", SampleStatus.RAW, new double[]{1.0, 3.0});
        assertEquals("s1", sample.getId());
        assertEquals("cat", sample.getLabel());
        assertEquals(SampleStatus.RAW, sample.getStatus());
        assertArrayEquals(new double[]{1.0, 3.0}, sample.getFeatures());
    }
    @Test
    void changeStatusMakesReady() {
        DataSample sample = new DataSample("s2", "dog", SampleStatus.RAW, new double[]{1.0, 3.0, 5.0});
        assertFalse(sample.isReady());
        sample.changeStatus(SampleStatus.READY);
        assertTrue(sample.isReady());
        assertEquals(SampleStatus.READY, sample.getStatus());
    }
    @Test
    void averageFeaturesComputesMean() {
        DataSample sample = new DataSample("s2", "dog", SampleStatus.RAW, new double[]{1.0, 3.0, 5.0});
        assertEquals(3.0, sample.averageFeatures());
    }
    @Test
    void constructorCopiesArray() {
        double[] original = {1.0, 2.0};
        DataSample sample = new DataSample("s1", "cat", SampleStatus.RAW, original);
        original[0] = 999.0;
        assertEquals(1.0, sample.getFeatures()[0]);
    }
    @Test
    void getterReturnsCopy() {
        DataSample sample = new DataSample("s1", "cat", SampleStatus.RAW, new double[]{1.0, 2.0});
        double[] leaked = sample.getFeatures();
        leaked[0] = 888.0;
        assertEquals(1.0, sample.getFeatures()[0]);
    }
    @Test
    void rejectsNullId() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample(null, "dog", SampleStatus.RAW, new double[]{1.0}));
    }
    @Test
    void rejectsBlankId() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample(" ", "dog", SampleStatus.RAW, new double[]{1.0}));
    }
    @Test
    void rejectsNullLabel() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("s1", null, SampleStatus.RAW, new double[]{1.0}));
    }
    @Test
    void rejectsBlankLabel() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("s1", " ", SampleStatus.RAW, new double[]{1.0}));
    }
    @Test
    void rejectsNullStatus() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("s1", "dog", null, new double[]{1.0}));
    }
    @Test
    void rejectsNullFeatures() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("s1", "dog", SampleStatus.RAW, null));
    }
    @Test
    void rejectsEmptyFeatures() {
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("s1", "dog", SampleStatus.RAW, new double[]{}));
    }
    @Test
    void rejectsNullChangeStatus() {
        DataSample sample = new DataSample("s1", "dog", SampleStatus.RAW, new double[]{1.0});
        assertThrows(IllegalArgumentException.class, () -> sample.changeStatus(null));
    }
}
