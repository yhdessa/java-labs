package edu.course.lab05;

public final class DataSample {

    private final String id;
    private final String label;
    private final String source;
    private SampleStatus status;
    private final double[] features;

    public DataSample(String id, String label, String source, SampleStatus status, double[] features) {
        // TODO: перенести проверки из ЛР2 + source null/blank
        // TODO: защитное копирование массива
        throw new UnsupportedOperationException("TODO");
    }

    public String getId() {
        throw new UnsupportedOperationException("TODO");
    }

    public String getLabel() {
        throw new UnsupportedOperationException("TODO");
    }

    public String getSource() {
        throw new UnsupportedOperationException("TODO");
    }

    public SampleStatus getStatus() {
        throw new UnsupportedOperationException("TODO");
    }

    public double[] getFeatures() {
        // TODO: вернуть копию
        throw new UnsupportedOperationException("TODO");
    }
}
