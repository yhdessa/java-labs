package edu.course.lab04;

public final class DataSample {

    private final String id;
    private final String label;
    private SampleStatus status;
    private final double[] features;

    public DataSample(String id, String label, SampleStatus status, double[] features) {
        // TODO: перенести проверки из ЛР2 (id/label null/blank, status null, features null/empty)
        // TODO: защитное копирование массива
        throw new UnsupportedOperationException("TODO");
    }

    public String getId() {
        throw new UnsupportedOperationException("TODO");
    }

    public String getLabel() {
        throw new UnsupportedOperationException("TODO");
    }

    public SampleStatus getStatus() {
        throw new UnsupportedOperationException("TODO");
    }

    public double[] getFeatures() {
        // TODO: вернуть копию
        throw new UnsupportedOperationException("TODO");
    }

    public void changeStatus(SampleStatus newStatus) {
        throw new UnsupportedOperationException("TODO");
    }

    public boolean isReady() {
        throw new UnsupportedOperationException("TODO");
    }

    public double averageFeatures() {
        throw new UnsupportedOperationException("TODO");
    }
}
